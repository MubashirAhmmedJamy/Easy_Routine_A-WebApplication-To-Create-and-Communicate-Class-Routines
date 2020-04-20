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

public class PelletsLoaderAdmin extends HttpServlet {
    String db_code;
    Statement state;
    ResultSet rs;
    String T, S, C, CO;

    String sql;
    
    int nt = 0;
    int ns = 0;
    int nc = 0;
    int nco = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        db_code = (String) request.getSession().getAttribute("db_code");
        
        try {
            state = ConnectionMaster.getInstance().connection.createStatement();
        } catch (SQLException e) {
        }
        
        getTeachers();
        getStudents();
        getClassrooms();
        getCourses();
        

//        System.out.println("T: " + T + " | nt: " + nt);
//        System.out.println("S: " + S + " | ns: " + ns);
//        System.out.println("C: " + C + " | nc: " + nc);
//        System.out.println("C: " + CO + " | nco: " + nco);
        
        response.addHeader("T", T);
        response.addHeader("S", S);
        response.addHeader("C", C);
        response.addHeader("CO", CO);

        response.addIntHeader("nt", nt);
        response.addIntHeader("ns", ns);
        response.addIntHeader("nc", nc);
        response.addIntHeader("nco", nco);
        
        T = "";
        S = "";
        C = "";
        CO = "";
        nt = 0;
        ns = 0;
        nc = 0;
        nco = 0;
    }

    private void getTeachers() {
        String sta = "";

        try {
            sql = "select name from teachers";
            rs = state.executeQuery(sql);

            while (rs.next()) {
                nt++;
                sta += rs.getString("name") + ",";
            }

            T = sta;

        } catch (Exception e) {
            System.out.println("Something went wrong while getting teachers");
        }
    }

    private void getStudents() {
        String sta = "";

        try {
            sql = "select name from students";
            rs = state.executeQuery(sql);

            while (rs.next()) {
                ns++;
                sta += rs.getString("name") + ",";
            }

            S = sta;

        } catch (Exception e) {
            System.out.println("Something went wrong while getting students");
        }
    }

    private void getClassrooms() {
        String sta = "";

        try {
            sql = "select name from classrooms";
            rs = state.executeQuery(sql);

            while (rs.next()) {
                nc++;
                sta += rs.getString("name") + ",";
            }

            C = sta;

        } catch (Exception e) {
            System.out.println("Something went wrong while getting classrooms");
        }
    }
    
    
        private void getCourses() {
        String sta = "";

        try {
            sql = "select name from courses";
            rs = state.executeQuery(sql);

            while (rs.next()) {
                nco++;
                sta += rs.getString("name") + ",";
            }

            CO = sta;

        } catch (Exception e) {
            System.out.println("Something went wrong while getting courses");
        }
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}