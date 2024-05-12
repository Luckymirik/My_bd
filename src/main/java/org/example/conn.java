package org.example;

import java.sql.*;

public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    public static void Coon() throws ClassNotFoundException, SQLException{
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:My_cats.db");

        System.out.println("База подключена");
    }
    public static void CreateDB() throws ClassNotFoundException,SQLException{
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users'")
    }
}
