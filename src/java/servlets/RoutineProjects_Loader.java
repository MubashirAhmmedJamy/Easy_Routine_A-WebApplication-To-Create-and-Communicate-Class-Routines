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

public class RoutineProjects_Loader extends HttpServlet {
    Statement state;
    ResultSet rs;
    String routines,codes;
    String sql;
    int no_of_routines;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException e) {
        }
        
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
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
