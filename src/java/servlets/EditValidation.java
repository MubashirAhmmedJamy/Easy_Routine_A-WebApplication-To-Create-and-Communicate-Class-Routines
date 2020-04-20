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

public class EditValidation extends HttpServlet {
    
    String prev, now;
    String db_code;

    String classTime;
    String teachers;    
    String classrooms;
    String students;
    String courses;
    String departments;
    String gridNames;
    
    String classTimeFinal;
    String teachersFinal;
    String classroomsFinal;
    String studentsFinal;
    String coursesFinal;
    String departmentsFinal;
    String gridNamesFinal;
    
    String currentType;
    
    ResultSet rs;
    Statement state;
    
    

    String validationMessage;
    int possibility = 1;
    int hours = 1, possible_hours = 1, possible_hoursFinal;
    
    
    String ClassTimes[]= new String[10];
    
    int timeDependencyIndex;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db_code = (String) request.getSession().getAttribute("db_code");
        prev = request.getParameter("previous");
        now = request.getParameter("now");
        
        
        String P[] = prev.split(",");
        String N[] = now.split(",");
        
        classTime = N[0];
        teachers = N[1];
        classrooms = N[2];
        students = N[3];
        courses = N[4];
        gridNames = classTime + ",";
        
        timeDependencyIndex = 1;
        
        
        classTimeFinal = P[0];
        teachersFinal = P[1];
        classroomsFinal = P[2];
        studentsFinal = P[3];
        coursesFinal = P[4];
        
        String MSG = "";
        
        
//        System.out.println("\n\n*************************************************************************\n\n");
        
//        System.out.println(classTime + " - " + teachers + " - " + classrooms + " - " + students + " - " + courses);


        
        ClassTimes[1] = classTime;

        MSG += classHoursChecker();
        classHoursCheckerFinal();
        
        classTimeMarker("0", gridNamesFinal);
        
        MSG += courseDeptChecker();
//        departmentsFinal = courseDeptChecker(coursesFinal);
        
        MSG +=  classTimeChecker() + teacherCourseChecker() + studentClassroomChecker();
        
        
        if(MSG.equals("")){
            validationMessage = "This move is possible";
            cancelPrevClass();
            classTimeMarkerNew("1", gridNames);            
        }else{
            validationMessage = MSG;
            classTimeMarker("1", gridNamesFinal);
        }
        
        System.out.println("Validation Message:" +validationMessage);
        System.out.println("Possibility: " + possibility);
               
        //System.out.println("Validation Message: " + validationMessage);
        //System.out.println("Possibility: " + possibility);
    }

    private String courseDeptChecker() {
        
        String dMsg = "";
        int tC,cC;

        String sql;

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            
            sql = "select Department from courses_"+db_code+" where name='" + courses + "'";
            rs = state.executeQuery(sql);
            rs.next();
            departments = rs.getString("Department");
            
            if(departments.equals("CSE")){
                sql = "select count(name) from teachers_"+db_code+" where name='" + teachers + "'";
                rs = state.executeQuery(sql);
                rs.next();
                tC = rs.getInt(1);

                sql = "select count(name) from classrooms_"+db_code+" where name='" + classrooms + "'";
                rs = state.executeQuery(sql);
                rs.next();                
                cC = rs.getInt(1);
                
                if(tC == 0){
                    dMsg += " - Teacher should be assigned from CSE department";
                    possibility = 0;
                    timeDependencyIndex = 0;
                }
                
                if(cC == 0){
                    dMsg += " - Classroom should be provided from CSE department";
                    possibility = 0;
                    timeDependencyIndex = 0;
                }
                
            }else{
                timeDependencyIndex = 0;
                if(!teachers.equals(departments) || !classrooms.equals(departments)){
                    dMsg += " - Teacher & classroom both must be from department " + departments;
                    possibility = 0;
                }
            }

        } catch (SQLException ex) {
        }

        return dMsg;
    }
    
    private String classHoursChecker() {
        String sql, hMsg = "";
        int h, m, i, j, rem;

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

            i = m + 1;
            j = 2;

            while ((h > 0) && (rem > 1)) {
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
    
    private void classHoursCheckerFinal() {
        String sql;        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "select gridNames from current_routine_status_"+db_code+" where Time = '"+classTimeFinal+"' and Teacher='"+teachersFinal+"' and Batch='"+studentsFinal+"' and Classroom='"+classroomsFinal+"'";
            rs = state.executeQuery(sql);
            rs.next();            
            gridNamesFinal = rs.getString("gridNames");        
        } catch (SQLException ex) {
            //System.out.println("Something went wrong while getting hours");
        }
    }

    public void classTimeMarker(String s, String gridNames) {
        String sql;
        String A[] = gridNames.split(",");
        
        System.out.println("TimeMarker: " + s + " names: " +gridNames);
        System.out.println("For: "+teachersFinal + "," + classroomsFinal + "," +studentsFinal);

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            for (String A1 : A) {
                sql = "UPDATE teachers_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + teachersFinal + "'";
                state.executeUpdate(sql);
                sql = "UPDATE classrooms_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + classroomsFinal + "'";
                state.executeUpdate(sql);
                sql = "UPDATE students_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + studentsFinal + "'";
                state.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Couldn't update the final classtimes");
        }
    }
    
    private void cancelPrevClass() {
        String sql;
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "delete from current_routine_status_"+db_code+" where Time = '"+classTimeFinal+"' and Teacher='"+teachersFinal+"' and Batch='"+studentsFinal+"' and Classroom='"+classroomsFinal+"'";
            state.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }
    
    public void classTimeMarkerNew(String s, String gridNames) {
        String sql;
        String A[] = gridNames.split(",");
        
        System.out.println("TimeMarker NEW: " + s + " names: " +gridNames);
        System.out.println("For: "+teachers + "," + classrooms + "," +students);

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            for (String A1 : A) {
                sql = "UPDATE teachers_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + teachers + "'";
                state.executeUpdate(sql);
                sql = "UPDATE classrooms_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + classrooms + "'";
                state.executeUpdate(sql);
                sql = "UPDATE students_"+db_code+" SET " + A1 + " = '" + s + "' WHERE name = '" + students + "'";
                state.executeUpdate(sql);
                
                
            }
            
            insert(A[0]);
            
        } catch (SQLException e) {
            System.out.println("Couldn't update the final classtimes");
        }
    }
    
    public void insert(String ct) {
        String sql;
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "insert into current_routine_status_"+db_code+" (Time,Teacher,Classroom,Batch,Course,gridNames) values ('" + ct + "','" + teachers + "','" + classrooms + "','" + students + "','" + courses + "','" +gridNames+ "')";
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problem faced with edited class schedule insertion");
        }
    }

    
/*************************************************************************/
    private String classTimeChecker() {
        String tTime;
        String sTime;
        String cTime;
        
        String classTimeMSG = "";
        
        String sql;
        
        //System.out.println("Classtime checking");

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {

        }
        

        if (timeDependencyIndex == 1) {
            
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

        if (timeDependencyIndex == 1) {

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
        
        if (timeDependencyIndex == 1) {
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
        


        if (timeDependencyIndex == 1) {
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
//        response.addIntHeader("possible_hours", possible_hours);
        response.addHeader("msg", validationMessage);
//        response.addHeader("gridNames", gridNames);
//        response.addHeader("dept", departments);
        
        possibility = 1;
        possible_hours = 1;
        hours = 1;
        timeDependencyIndex = 1;
    }
}
