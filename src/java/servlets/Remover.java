package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Remover extends HttpServlet {
    
    Statement state;
    String sql;
    String db_code;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Got IT");
        
        db_code = (String) request.getSession().getAttribute("db_code");
        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException e) {
        }
        
        classTimeUnMarker();
        cancelAllClasses();
    }
    
    
    private void classTimeUnMarker() {
        try {
            sql = "update teachers_"+db_code+" set SUN_8AM='0',MON_8AM='0',TUE_8AM='0',WED_8AM='0',THU_8AM='0',SUN_9AM='0',MON_9AM='0',TUE_9AM='0',WED_9AM='0',THU_9AM='0',SUN_10AM='0',MON_10AM='0',TUE_10AM='0',WED_10AM='0',THU_10AM='0',SUN_11AM='0',MON_11AM='0',TUE_11AM='0',WED_11AM='0',THU_11AM='0',SUN_12PM='0',MON_12PM='0',TUE_12PM='0',WED_12PM='0',THU_12PM='0',SUN_2PM='0',MON_2PM='0',TUE_2PM='0',WED_2PM='0',THU_2PM='0',SUN_3PM='0',MON_3PM='0',TUE_3PM='0',WED_3PM='0',THU_3PM='0',SUN_4PM='0',MON_4PM='0',TUE_4PM='0',WED_4PM='0',THU_4PM='0'";
            state.executeUpdate(sql);

            sql = "update classrooms_"+db_code+" set SUN_8AM='0',MON_8AM='0',TUE_8AM='0',WED_8AM='0',THU_8AM='0',SUN_9AM='0',MON_9AM='0',TUE_9AM='0',WED_9AM='0',THU_9AM='0',SUN_10AM='0',MON_10AM='0',TUE_10AM='0',WED_10AM='0',THU_10AM='0',SUN_11AM='0',MON_11AM='0',TUE_11AM='0',WED_11AM='0',THU_11AM='0',SUN_12PM='0',MON_12PM='0',TUE_12PM='0',WED_12PM='0',THU_12PM='0',SUN_2PM='0',MON_2PM='0',TUE_2PM='0',WED_2PM='0',THU_2PM='0',SUN_3PM='0',MON_3PM='0',TUE_3PM='0',WED_3PM='0',THU_3PM='0',SUN_4PM='0',MON_4PM='0',TUE_4PM='0',WED_4PM='0',THU_4PM='0'";            
            state.executeUpdate(sql);

            sql = "update students_"+db_code+" set SUN_8AM='0',MON_8AM='0',TUE_8AM='0',WED_8AM='0',THU_8AM='0',SUN_9AM='0',MON_9AM='0',TUE_9AM='0',WED_9AM='0',THU_9AM='0',SUN_10AM='0',MON_10AM='0',TUE_10AM='0',WED_10AM='0',THU_10AM='0',SUN_11AM='0',MON_11AM='0',TUE_11AM='0',WED_11AM='0',THU_11AM='0',SUN_12PM='0',MON_12PM='0',TUE_12PM='0',WED_12PM='0',THU_12PM='0',SUN_2PM='0',MON_2PM='0',TUE_2PM='0',WED_2PM='0',THU_2PM='0',SUN_3PM='0',MON_3PM='0',TUE_3PM='0',WED_3PM='0',THU_3PM='0',SUN_4PM='0',MON_4PM='0',TUE_4PM='0',WED_4PM='0',THU_4PM='0'";
            state.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    private void cancelAllClasses() {
        try {
            sql = "delete from current_routine_status_"+db_code+"";
            state.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}