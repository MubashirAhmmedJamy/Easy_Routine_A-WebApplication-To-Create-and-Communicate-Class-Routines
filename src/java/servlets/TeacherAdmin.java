package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherAdmin extends HttpServlet {
    String name, designation, fullName, email,mobile,sql;
    Statement state;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        fullName = request.getParameter("fullName");
        designation = request.getParameter("designation");
        email = request.getParameter("email");
        mobile = request.getParameter("mobile");
        //System.out.println("Name: " + name);
//        System.out.println("db_code: "+request.getSession().getAttribute("db_code"));
        create();
    }
    
    private void create(){
        try {
//            System.out.println("Creating teacher");
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "insert into teachers (name,SUN_8AM,MON_8AM,TUE_8AM,WED_8AM,THU_8AM,SUN_9AM,MON_9AM,TUE_9AM,WED_9AM,THU_9AM,SUN_10AM,MON_10AM,TUE_10AM,WED_10AM,THU_10AM,SUN_11AM,MON_11AM,TUE_11AM,WED_11AM,THU_11AM,SUN_12PM,MON_12PM,TUE_12PM,WED_12PM,THU_12PM,SUN_2PM,MON_2PM,TUE_2PM,WED_2PM,THU_2PM,SUN_3PM,MON_3PM,TUE_3PM,WED_3PM,THU_3PM,SUN_4PM,MON_4PM,TUE_4PM,WED_4PM,THU_4PM,Designation,Email,Full_Name,Mobile) values('"+name+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'"+designation+"','"+email+"','"+fullName+"','"+mobile+"')";
            state.executeUpdate(sql);
        } catch (SQLException ex) {  
            System.out.println("Something went wrong while creating teacher");
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
