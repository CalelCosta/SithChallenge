package com.sith.challenge.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sith?useSSL=false&useTimezone=true&serverTimezone=UTC&MODE=MySQL";

    public Connection getConnection(){

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Error on database, class not found: " + e);
        } catch (SQLException ex) {
            System.out.println("Error on database, Sql exception: " + ex);
        }
        return null;
    }

}
