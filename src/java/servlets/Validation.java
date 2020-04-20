package servlets;

import Beans.ClassTimeMapper;
import Beans.RemainingHoursMapper;
import Beans.SerialMapper;
import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validation extends HttpServlet {

    String classTime;
    String teachers;    
    String classrooms;
    String students;
    String courses;
    String departments;
    String departmentsFinal;
    String db_code;
    
    String currentType;
    
    ResultSet rs;
    Statement state;
    
    

    String validationMessage;
    int possibility = 1;
    int hours = 1, possible_hours = 1;
    String gridNames;
    
    String ClassTimes[]= new String[10];
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        classTime = request.getParameter("classTime");
        teachers = request.getParameter("teachers");
        classrooms = request.getParameter("classrooms");
        students = request.getParameter("students");
        courses = request.getParameter("courses");
        currentType = request.getParameter("type");
        departmentsFinal = request.getParameter("departmentsFinal");
        
        gridNames = classTime + ",";
        
        db_code = (String) request.getSession().getAttribute("db_code");
        
//        System.out.println("Session attribute db_code now: " + db_code);
        
        String MSG = "";
        
        
//        System.out.println("\n\n*************************************************************************\n\n");
        
//        System.out.println(classTime + " - " + teachers + " - " + classrooms + " - " + students + " - " + courses);

        if (teachers.equals("")) {
            teachers = "NotSet";
        }
        
        if (classrooms.equals("")) {
            classrooms = "NotSet";
        }
        
        if (students.equals("")) {
            students = "NotSet";
        }
        
        ClassTimes[1] = classTime;
        
        if (courses.equals("")) {
            courses = "NotSet";
            departments = "CSE";
        }else{
//            System.out.println("\nCourse found calling classHoursChecker.");
           MSG += classHoursChecker();
           departments = courseDeptChecker();
        }
        
        MSG +=  classTimeChecker() + teacherCourseChecker() + studentClassroomChecker();
        
        if(MSG.equals("")){
            validationMessage = "This move is possible";
        }else{
            validationMessage = MSG;
        }
               
        //System.out.println("Validation Message: " + validationMessage);
        //System.out.println("Possibility: " + possibility);
    }
    
    private String courseDeptChecker(){
            String sql = "select Department from courses_"+db_code+" where name='"+courses+"'";
            String dept = "CSE";
            try {
                state = ConnectionMaster.getInstance().connection.createStatement();
                rs = state.executeQuery(sql);
                rs.next();
                dept = rs.getString("Department");

            } catch (SQLException ex) {
            }
            
            return dept;
    }
    
    private String classHoursChecker() {
        String sql, hMsg = "";
        int h,m,i, j, rem;
        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "select hours from courses_"+db_code+" where name = '" + courses + "'";
            
            rs = state.executeQuery(sql);
            rs.next();
            
            h = hours = rs.getInt("hours");
            rem = RemainingHoursMapper.getRem(classTime);
            m = ClassTimeMapper.getMap(classTime);
            
//            System.out.println("Classtime Mapped to: " + m);
//            System.out.println("h: " + h +" hours: " +hours);
            
            h--;
            
            if (rem < hours) {
                hMsg = "- Not enough time left today to take a " + hours + " hours long class";
                possibility = 0;
            }

            i = m+1;
            j = 2;
                    
            while((h > 0) && (rem > 1)){
//                System.out.println("m/8: " + Math.ceil(m/8) + " ---- i/8: " + Math.ceil(i/8));
                
//                if(Math.ceil(m/8) != Math.ceil(i/8)){
//                    hMsg = "- Not enough time left today to take a " + hours +" hours long class";
//                    System.out.println("Breaking while since condition has matched");
//                    possibility = 0;
//                    break;
//                }
                ClassTimes[j] = SerialMapper.getNext(i);
                gridNames += ClassTimes[j] + ",";
                h--;
                j++;
                i++;
                rem--;
            }
            
        possible_hours = j - 1;
        
        } catch (SQLException ex) {
            //System.out.println("Something went wrong while getting hours");
        }        
        
//        System.out.println("hMsg" + hMsg);
//        System.out.println("possible hours: " + possible_hours);
//        System.out.println("grids: " + gridNames);
        
        return hMsg;
    }
    
/*************************************************************************/
    private String classTimeChecker() {
        String tTime = "0";
        String sTime = "0";
        String cTime = "0";
        
        String classTimeMSG = "";
        
        String sql;
        
        //System.out.println("Classtime checking");

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {

        }
        

        if ((!teachers.equals("NotSet") && departments.equals("CSE") && departmentsFinal.equals("CSE")) || currentType.equals("teachers")) {
            
            for (int i = 1; i <= possible_hours; i++) {
                
                sql = "select " + ClassTimes[i] + " from teachers_"+db_code+" where name = '" + teachers + "'";

                try {
                    rs = state.executeQuery(sql);

                    rs.next();

                    //System.out.println("tTime: " + rs.getString(classTime));
                    tTime = rs.getString(ClassTimes[i]);
             
                    if (Integer.parseInt(tTime) > 0) {
                        classTimeMSG += "- Teacher " + teachers + " is not free at " + ClassTimes[i];
                        possibility = 0;                        
                    }

                } catch (SQLException ex) {
                }
            }

        }

        if ((!classrooms.equals("NotSet") && departments.equals("CSE") && departmentsFinal.equals("CSE")) || currentType.equals("classrooms")) {

            for (int i = 1; i <= possible_hours; i++) {
                sql = "select " + ClassTimes[i] + " from classrooms_"+db_code+" where name = '" + classrooms + "'";

                try {

                    rs = state.executeQuery(sql);
                    rs.next();
                    //System.out.println("cTime: " + rs.getString(classTime));
                    cTime = rs.getString(ClassTimes[i]);
             
                    if (Integer.parseInt(cTime) > 0) {
                        classTimeMSG += " - Classroom " + classrooms + " is not free at " + ClassTimes[i];
                        possibility = 0;                        
                    }

                } catch (SQLException ex) {
                }
            }

        }

        if (!students.equals("NotSet")) {
            for (int i = 1; i <= possible_hours; i++) {
                sql = "select " + ClassTimes[i] + " from students_"+db_code+" where name = '" + students + "'";

                try {

                    rs = state.executeQuery(sql);
                    rs.next();
                    //System.out.println("sTime: " + rs.getString(classTime));
                    sTime = rs.getString(ClassTimes[i]);

                    if (Integer.parseInt(sTime) > 0) {
                        classTimeMSG += " - Batch " + students + " is not free at " + ClassTimes[i];
                        possibility = 0;
                    }

                } catch (SQLException ex) {
                }
            }
        }
        
//        int sum = Integer.parseInt(tTime) + Integer.parseInt(cTime) + Integer.parseInt(sTime);
//        
//        //System.out.println("sum: " + sum);
//        
//        if (sum > 0) {
//            //classTimeMSG += "- Classtime mismatch found!\n";            
//            
//            if(Integer.parseInt(tTime) > 0){
//                classTimeMSG += "\n        - Teacher "+teachers+" is not free at " + classTime+"\n";
//            }
//            
//            if(Integer.parseInt(cTime) > 0){
//                classTimeMSG += "        - Classroom "+classrooms+" is not free at " + classTime+"\n";
//            }
//            
//            if(Integer.parseInt(sTime) > 0){
//                classTimeMSG += "        - Batch "+students+" is not free at " + classTime+"\n";
//            }
//            
//            possibility = 0;
//        }
        
        return classTimeMSG;
    }

/*************************************************************************/

    private String teacherCourseChecker() {
        String AssignedTeacher;
        String teacherCourseMSG = "";
        
        //System.out.println("teacherCourseChecker working:");

        String sql;

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {

        }
        
        if (!teachers.equals("NotSet") && !courses.equals("NotSet") && departments.equals("CSE") && departmentsFinal.equals("CSE")) {
            sql = "select AssignedTeacher from courses_"+db_code+" where name = '"+courses+"'";

            try {
                rs = state.executeQuery(sql);
                rs.next();
                AssignedTeacher = rs.getString("AssignedTeacher");
                
                if (!AssignedTeacher.equals(teachers)) {
                    teacherCourseMSG += "\n- Teacher " + teachers + " is not assigned to take course " + courses + ".\n";
                    possibility = 0;
                }

            } catch (SQLException ex) {
            }
        }
        
        //System.out.println("tw: " + tw + "\ncw: " + cw);
        
        
        
        return teacherCourseMSG;
    }

/*************************************************************************/
    
    private String studentClassroomChecker() {
        int nos = 0;
        int ccp = 100;
        
        String studentClassroomMSG = "";

        String sql;

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {

        }
        
        //System.out.println("studentClassroomChecker working:");
        
        boolean basic = (!students.equals("NotSet")) && (!classrooms.equals("NotSet"));

        if ((basic && departments.equals("CSE") && departmentsFinal.equals("CSE")) || ((!students.equals("NotSet")) && currentType.equals("classrooms"))) {
            try {
                sql = "select number_of_students from students_"+db_code+" where name = '" + students + "'";
                rs = state.executeQuery(sql);
                rs.next();
                nos = rs.getInt("number_of_students");

                sql = "select capacity from classrooms_"+db_code+" where name = '" + classrooms + "'";
                rs = state.executeQuery(sql);
                rs.next();
                ccp = rs.getInt("capacity");

            } catch (SQLException ex) {
            }
        }
        
        //System.out.println("\nNumber of Students: " +nos+"\nClassroom Capacity: " +ccp);
        
        if(ccp < nos){
            studentClassroomMSG += "\n- Batch " +students + " has more students than the capacity of classroom "+classrooms+".\n";
            possibility = 0;
        }
        
        return studentClassroomMSG;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("AGAIN");
        processRequest(request, response);
        
        //System.out.println("pos in doPost: " +possibility);
        
        response.addIntHeader("pos", possibility);
        response.addIntHeader("possible_hours", possible_hours);
        response.addHeader("msg", validationMessage);
        response.addHeader("gridNames", gridNames);
        response.addHeader("dept", departments);
        
        possibility = 1;
        possible_hours = 1;
        hours = 1;
    }
}
