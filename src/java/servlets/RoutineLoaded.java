package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoutineLoaded extends HttpServlet {

    Statement state;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {
        }
        
        String s = "Jamy";
        Vector vec = new Vector<String>();
        
        vec.add(s);
        
        System.out.println(vec.elementAt(0));
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
