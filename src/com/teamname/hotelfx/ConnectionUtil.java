package com.teamname.hotelfx;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    Connection conn = null;

    private static String DB_DATABASE = "logindb";
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_CONNECTION = "jdbc:mysql://localhost/" + DB_DATABASE + "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";

    public static Connection cdb() {
        try {
            Class.forName(DB_DRIVER);
            Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}