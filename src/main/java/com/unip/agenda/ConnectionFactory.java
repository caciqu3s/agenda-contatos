package com.unip.agenda;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String USERNAME = "admin";

    private static final String PASSWORD = "admin";

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";

    public static Connection createConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =  DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return con;
    }
}
