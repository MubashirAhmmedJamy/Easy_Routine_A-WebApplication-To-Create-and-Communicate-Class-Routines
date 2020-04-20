package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingMaster extends HttpServlet {
    
    String classTime;
    String teachers;
    String classrooms;
    String students;
    String courses;
    String db_code;
    //String hours;
    Statement state;
    String sql;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        classTime = request.getParameter("classTime");
        teachers = request.getParameter("teachers");
        classrooms = request.getParameter("classrooms");
        students = request.getParameter("students");
        courses = request.getParameter("courses");
        //hours = request.getParameter("possible_hours");
        
//        System.out.println("Booking Master called!");
        db_code = (String) request.getSession().getAttribute("db_code");
        
//        System.out.println(classTime + " - " + teachers + " - " + classrooms + " - " + students + " - " + courses);

        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {            
        }
        
        classTimeMarker();
    }

    public void classTimeMarker() {
        
        String A[] = classTime.split(",");

        try {
            for (String A1 : A) {
                sql = "UPDATE teachers_"+db_code+" SET " + A1 + " = '1' WHERE name = '" + teachers + "'";
                state.executeUpdate(sql);
                sql = "UPDATE classrooms_"+db_code+" SET " + A1 + " = '1' WHERE name = '" + classrooms + "'";
                state.executeUpdate(sql);
                sql = "UPDATE students_"+db_code+" SET " + A1 + " = '1' WHERE name = '" + students + "'";
                state.executeUpdate(sql);
            }
            
            insert(A[0]);

        } catch (SQLException e) {
            System.out.println("Couldn't update the classtimes / status table");
        }
    }
    
    public void insert(String ct) {
        try {
            sql = "insert into current_routine_status_"+db_code+" (Time,Teacher,Classroom,Batch,Course,gridNames) values ('" + ct + "','" + teachers + "','" + classrooms + "','" + students + "','" + courses + "','" +classTime+ "')";
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problem faced during insert operation");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
