/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DB_Classes.ConnectionMaster;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Jamy
 */
public class Test {
    
    public static void main(String args[]){
        
        String s = "Jamy";
        Vector vec = new Vector<String>();
        
        vec.add(s);
        
        System.out.println("Size: " +vec.size()+ " " + vec.elementAt(0));
    }

    
}
