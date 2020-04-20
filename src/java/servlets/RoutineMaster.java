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

public class RoutineMaster extends HttpServlet {
    
    String students ;
    String classtime;
    String courses = "",teachers = "",classrooms = "";
    int hours = 0, count = 0;
    String sql;
    Statement state;
    ResultSet rs;
    String db_code;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db_code = (String) request.getSession().getAttribute("db_code");
        
        students = request.getParameter("students");
        classtime = request.getParameter("time");

        //System.out.print((count++) + ": ");
                
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            
            sql = "select Course,Teacher,Classroom from current_routine_status_"+db_code+" where Batch='" + students + "' and time='" + classtime + "'";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                courses = rs.getString("Course");
                teachers = rs.getString("Teacher");
                classrooms = rs.getString("Classroom");
            }

            
            sql = "select hours from courses_"+db_code+" where name='"+courses+"'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                hours = rs.getInt("hours");
            }
            
            
            //System.out.println(students+"|"+classtime+": "+courses+","+teachers+","+classrooms+","+hours);
            

        } catch (SQLException ex) {
            System.out.println("Error happened!");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        response.addIntHeader("hours", hours);
        response.addHeader("courses", courses);
        response.addHeader("teachers", teachers);
        response.addHeader("classrooms", classrooms);
        
        courses = "";
        teachers = "";
        classrooms = "";
        hours = 0;
//        count = 0;
    }

}
