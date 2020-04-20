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

public class Loader extends HttpServlet {
    String db_code;
    Statement state;
    ResultSet rs;
    String status;
    String routines,codes;
    String sql;
    int no_of_classes = 0, no_of_routines;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("db_code: " +request.getSession().getAttribute("db_code"));

        db_code = (String) request.getSession().getAttribute("db_code");

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException e) {
        }
        
//        request.getSession().setAttribute("db_code", "hellow");
        
        getStatus();
        
        //System.out.println("Status: " + status);
        response.addHeader("status", status);
        response.addIntHeader("noc", no_of_classes);
        status = "";
        no_of_classes = 0;
        
        no_of_routines =0;
        get_routines();
        
        response.addHeader("routines", routines);
        response.addHeader("codes", codes);
        response.addIntHeader("nor", no_of_routines);
        
        //System.out.println(routines +"|"+codes+"|"+no_of_routines);
    }
    
    private void get_routines(){
        String n = "";
        String c = "";
        
        
        try {
            sql = "select * from routines";
            rs = state.executeQuery(sql);
            
            while (rs.next()) {
                no_of_routines++;
                n += rs.getString("name") + ",";
                c += rs.getString("db_code") + ",";
            }
            
            routines = n;
            codes = c;
        } catch (SQLException e) {
            System.out.println("Something went wrong while getting routines");
        }
    }
    
    
    private void getStatus(){
        String sta = "";
        
        try {
            sql = "select Time,Teacher,Classroom,Batch,Course from current_routine_status_"+db_code+"";
            rs = state.executeQuery(sql);
            
            while (rs.next()) {
                no_of_classes++;
                sta += rs.getString("Time");
                sta += "," + rs.getString("Teacher");
                sta += "," + rs.getString("Classroom");
                sta += "," + rs.getString("Batch");
                sta += "," + rs.getString("Course") + ",";
            }
            
            status = sta;
            
        } catch (Exception e) {
            System.out.println("Something went wrong during loading");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
