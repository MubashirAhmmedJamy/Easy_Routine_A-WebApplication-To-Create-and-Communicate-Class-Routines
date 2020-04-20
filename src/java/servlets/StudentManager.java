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

public class StudentManager extends HttpServlet {

    String sql;
    Statement state;
    ResultSet rs;
    String students;
    int nos;
    String db_code;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db_code = (String) request.getSession().getAttribute("db_code");
        
        students = "";
        nos = 0;
        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException ex) {
        }
        
        getNames();
    }

    private void getNames() {
        try {
            System.out.println("Getting batches:");
            sql = "select name from students_"+db_code+"";
            rs = state.executeQuery(sql);
            while(rs.next()){
                nos++;
                students += rs.getString("name") + ",";
            }
        } catch (SQLException e) {
            System.out.println("Something bad happend when getting batches");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        response.addIntHeader("ns", nos);
        response.addHeader("stu", students);
    }
}
