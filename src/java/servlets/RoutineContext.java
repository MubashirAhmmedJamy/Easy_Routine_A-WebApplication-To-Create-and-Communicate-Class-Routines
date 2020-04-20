package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoutineContext extends HttpServlet {
    
    String param ;
    String classtime;

    String courses = "";
    
    String one = "",two = "",three = "";
    
    int hours = 0, count = 0;
    
    String index;
    
    String sql;
    Statement state;
    ResultSet rs;
    String db_code;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db_code = (String) request.getSession().getAttribute("db_code");

        one = "";
        two = "";
        three = "";

        hours = 0;
        count = 0;

//        System.out.println("DB: " + db_code);
        
        
        param = request.getParameter("param");
        classtime = request.getParameter("time");
        index = request.getParameter("index");

        
        
        //System.out.print((count++) + ": ");
                
        try {
//            System.out.println("Trying");
            
            state = ConnectionMaster.getInstance().connection.createStatement();
            
            hours = 0;
            
            if(index.equals("student")){
//                System.out.println("found student");
                sql = "select Course,Teacher,Classroom from current_routine_status_"+db_code+" where Batch='" + param + "' and time='" + classtime + "'";
                rs = state.executeQuery(sql);
//                System.out.println("SQL");

                if (rs.next()) {
                    one = rs.getString("Course");
                    two = rs.getString("Teacher");
                    three = rs.getString("Classroom");
                    courses = one;
                }
                
            }else if (index.equals("teacher")){
                sql = "select Course,Batch,Classroom from current_routine_status_"+db_code+" where Teacher='" + param + "' and time='" + classtime + "'";
                rs = state.executeQuery(sql);
                
                if (rs.next()) {
                    one = rs.getString("Course");
                    two = rs.getString("Batch");
                    three = rs.getString("Classroom");
                    courses = one;
                }
            }else if(index.equals("classroom")){
                sql = "select Course,Batch,Teacher from current_routine_status_"+db_code+" where Classroom='" + param + "' and time='" + classtime + "'";
                rs = state.executeQuery(sql);
                if (rs.next()) {
                    one = rs.getString("Course");
                    two = rs.getString("Batch");
                    three = rs.getString("Teacher");
                    courses = one;
                }
            }
            

            if (!courses.equals("")) {
                sql = "select hours from courses_" + db_code + " where name='" + courses + "'";

                rs = state.executeQuery(sql);
                rs.next();
                hours = rs.getInt("hours");
            }
            
//            System.out.println(param + "|" + classtime + "|" + index + "   " + one + "   " + two + "   " + three + "   " + hours);

        } catch (SQLException ex) {
            System.out.println("Error happened!");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        response.addIntHeader("hours", hours);
        response.addHeader("one", one);
        response.addHeader("two", two);
        response.addHeader("three", three);
        
        one = "";
        two = "";
        three = "";
        hours = 0;
        courses = "";
    }

}
