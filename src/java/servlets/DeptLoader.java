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

public class DeptLoader extends HttpServlet {
    
    String sql;
    ResultSet rs;
    Statement state;
    int nod;
    String D;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nod = 0;
        getDepartments();
//        System.out.println(D);
    }

    private void getDepartments() {
        String sta = "";
        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException e) {
        }

        try {
            sql = "select name from departments";
            rs = state.executeQuery(sql);

            while (rs.next()) {
                nod++;
                sta += rs.getString("name") + ",";
            }

            D = sta;

        } catch (Exception e) {
            System.out.println("Something went wrong while getting departments");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        response.addHeader("D", D);
        response.addIntHeader("nod", nod);
    }
}
