package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseAdmin extends HttpServlet {
    
    String name, fullname,credits,hour,classPerWeek,modality,type,department,sql;
    Statement state;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("code");
        fullname = request.getParameter("fullname");
        hour = request.getParameter("hour");
        credits = request.getParameter("credits");
        classPerWeek = request.getParameter("classPerWeek");
        modality = request.getParameter("modality");
        type = request.getParameter("type");
        department = request.getParameter("department");
        
        create();
    }

    private void create() {
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();

//            if (type.equals("Major")) {
            sql = "insert into courses (name,AssignedTeacher,hours,Type,Department,Modality,Credits,ClassPerWeek,ClassScheduled,Full_Name) values ('" + name + "','Not Set Yet'," + hour + ",'" + type + "','" + department + "','" + modality + "',"+credits+","+classPerWeek+",0,'"+fullname+"')";
//            } else {
//                sql = "insert into courses (name,AssignedTeacher,hours,Type,Department) values ('" + code + "','Not Set Yet'," + hour + ",'" + type + "','" + department + "')";
//            }
            state.executeUpdate(sql);
        } catch (SQLException ex) {
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
