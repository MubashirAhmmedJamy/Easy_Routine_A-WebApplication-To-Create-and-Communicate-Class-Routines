package DB_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaster {
    private static ConnectionMaster cm = null;
    
    public Connection connection;
    
    private ConnectionMaster(){
        try {
                Class.forName(DB_Config.DRIVER_REGISTER);
                
                System.out.println("DB location updated: " + DB_Config.DB_LOCATION);
                
                connection = (Connection) DriverManager.getConnection(DB_Config.DB_LOCATION, DB_Config.USERNAME, DB_Config.PASSWORD);
                //System.out.println("Database connected successfully");
            } catch (ClassNotFoundException | SQLException ex) {
                
                //ex.printStackTrace();
                
                System.out.println("Problem found during database connection");
            }
    }
    
    public static ConnectionMaster getInstance() {
        if(cm != null){
            //System.out.println("Gettnig connection");
            return cm;
        } else{
            cm = new ConnectionMaster();
            //System.out.println("Gettnig new connection");
            return cm;
        }
    }
}
