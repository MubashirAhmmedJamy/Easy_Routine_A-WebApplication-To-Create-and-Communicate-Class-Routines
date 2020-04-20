package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeptAdmin extends HttpServlet {
    String name, sql;
    Statement state;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        //System.out.println("Name: " + name);
        create();
    }
    
    private void create(){
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "insert into departments (name) values('"+name+"')";
            state.executeUpdate(sql);
        } catch (SQLException ex) {            
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
