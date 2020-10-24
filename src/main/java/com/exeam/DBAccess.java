package com.exeam;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private static Connection conn = null;
    
    public static Connection getConn() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        if (conn == null) {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Primface_from", "root", "");
        }
        return conn;    
    }
}
