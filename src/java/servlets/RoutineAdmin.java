package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoutineAdmin extends HttpServlet {

    String sql;
    String teachers, inteachers = "";
    String students, instudents = "";
    String classrooms, inclassrooms = "";
    String courses, incourses = "";
    String from;
    String to;
    String counts;
    String assignees;
    
    String name, code;
    
    Statement state;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        teachers = request.getParameter("teachers");
        students = request.getParameter("students");
        classrooms = request.getParameter("classrooms");
        courses = request.getParameter("courses");
        from = request.getParameter("from");
        to = request.getParameter("to");
        counts = request.getParameter("counts");
        assignees = request.getParameter("assignedTeachers");
        
        String T[] = teachers.split(",");
        String S[] = students.split(",");
        String C[] = classrooms.split(",");
        String CO[] = courses.split(",");
        String CNT[] = counts.split(",");
        String AT[] = assignees.split(",");
        
        for (int i = 0; i < T.length; i++) {
            inteachers += "'" + T[i] + "'";
            if (i != T.length - 1) {
                inteachers += ",";
            }
        }
        
        for (int i = 0; i < S.length; i++) {
            instudents += "'" + S[i] + "'";
            if (i != S.length - 1) {
                instudents += ",";
            }
        }
        
        for (int i = 0; i < C.length; i++) {
            inclassrooms += "'" + C[i] + "'";
            if (i != C.length - 1) {
                inclassrooms += ",";
            }
        }
        
        for (int i = 0; i < CO.length; i++) {
            incourses += "'" + CO[i] + "'";
            if (i != CO.length - 1) {
                incourses += ",";
            }
        }
        
        name = "From " + from + " To " + to;
        code = from + "To" + to;
        
        addRoutine();
        copy();
        update(S, CO, CNT, AT);
    }
    
    private void addRoutine() {
        try {
//            System.out.println("Creating teacher");
            state = ConnectionMaster.getInstance().connection.createStatement();
            sql = "insert into routines (name,db_code) values ('" + name + "','" + code + "')";
            state.executeUpdate(sql);
        } catch (SQLException ex) {            
            System.out.println("Something went wrong while creating routine");
        }        
    }
    
    private void copy() {
        try {
//            System.out.println("Creating teacher");
            state = ConnectionMaster.getInstance().connection.createStatement();
            
            try {
                sql = "create table teachers_" + code + " as (select * from teachers where name in(" + inteachers + "))";
                state.executeUpdate(sql);
            } catch (SQLException sQLException) {
                System.out.println("Problem with teachers");
            }
            
            try {
                sql = "create table students_" + code + " as (select * from students where name in(" + instudents + "))";
                state.executeUpdate(sql);
            } catch (SQLException sQLException) {
                System.out.println("Problem with students");
            }
            
            try {
            sql = "create table classrooms_" + code + " as (select * from classrooms where name in(" + inclassrooms + "))";
            state.executeUpdate(sql);
            } catch (SQLException sQLException) {
                System.out.println("Problem with classrooms");
            }
            
            
            try{
            sql = "create table courses_" + code + " as (select * from courses where name in(" + incourses + "))";
            state.executeUpdate(sql);
            } catch (SQLException sQLException) {
                System.out.println("Problem with courses");
            }
            
            try {
                sql = "create table current_routine_status_" + code + " (Time varchar(45) NOT NULL,Teacher varchar(45) NOT NULL,Classroom varchar(45) NOT NULL,Batch varchar(45) NOT NULL,Course varchar(45) NOT NULL, gridNames varchar(1500) NOT NULL, PRIMARY KEY (Time,Teacher,Classroom,Batch,Course))";
                state.executeUpdate(sql);                
            } catch (Exception e) {
                System.out.println("Problem with current routine status");
            }

            
        } catch (SQLException ex) {            
            System.out.println("Something went wrong during database copying");
        }        
    }
    
    private void update(String S[], String CO[], String CNT[], String AT[]) {
        try {
//            System.out.println("Creating teacher");
            state = ConnectionMaster.getInstance().connection.createStatement();
            
            for (int i = 0; i < S.length; i++) {
                sql = "update students_" + code + " set number_of_students=" + CNT[i] + " where name = '" + S[i] + "';";
                state.executeUpdate(sql);
            }
            
            for (int i = 0; i < CO.length; i++) {
                sql = "update courses_" + code + " set AssignedTeacher='" + AT[i] + "' where name = '" + CO[i] + "';";
                state.executeUpdate(sql);
            }
            
//            sql = "create table students_" + code + " as (select * from students where name in(" + instudents + "))";
//            state.executeUpdate(sql);
//            
//            sql = "create table classrooms_" + code + " as (select * from classrooms where name in(" + inclassrooms + "))";
//            state.executeUpdate(sql);
//            
//            sql = "create table courses_" + code + " as (select * from courses where name in(" + incourses + "))";
//            state.executeUpdate(sql);
//            
//            sql = "create table current_routine_status_" + code + " (Time varchar(45) NOT NULL,Teacher varchar(45) NOT NULL,Classroom varchar(45) NOT NULL,Batch varchar(45) NOT NULL,Course varchar(45) NOT NULL, gridNames varchar(1500) NOT NULL, PRIMARY KEY (Time,Teacher,Classroom,Batch,Course))";
//            state.executeUpdate(sql);
            
        } catch (SQLException ex) {            
            System.out.println("Something went wrong during database updating");
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
