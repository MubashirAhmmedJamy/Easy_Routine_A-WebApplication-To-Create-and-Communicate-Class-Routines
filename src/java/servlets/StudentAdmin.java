package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentAdmin extends HttpServlet {
    
    String name,session,semester,section,sql;
//    int count;
    Statement state;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Got the request hey");
        session = request.getParameter("session");
        semester = request.getParameter("semester");
        section = request.getParameter("section");
        name = semester;
        
        if(!section.equals("No")){
            name += "("+section+")";
        }

        response.addHeader("nam", name);

//        count = (int) Float.parseFloat(request.getParameter("count"));
//        
//        System.out.println("Name: "+name+"\ncount: "+count);
        System.out.println("Got the request");
        create();
        
    }
    
    private void create(){
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "insert into students (name,semester,session,number_of_students,SUN_8AM,MON_8AM,TUE_8AM,WED_8AM,THU_8AM,SUN_9AM,MON_9AM,TUE_9AM,WED_9AM,THU_9AM,SUN_10AM,MON_10AM,TUE_10AM,WED_10AM,THU_10AM,SUN_11AM,MON_11AM,TUE_11AM,WED_11AM,THU_11AM,SUN_12PM,MON_12PM,TUE_12PM,WED_12PM,THU_12PM,SUN_2PM,MON_2PM,TUE_2PM,WED_2PM,THU_2PM,SUN_3PM,MON_3PM,TUE_3PM,WED_3PM,THU_3PM,SUN_4PM,MON_4PM,TUE_4PM,WED_4PM,THU_4PM,Session_Semester,section) values('"+name+"','"+semester+"','"+session+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'"+session+"_"+semester+"','"+section+"')";
            state.executeUpdate(sql);
            System.out.println("Student created");
        } catch (SQLException ex) {
            System.out.println("Something went wrong while creating new batch");
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
