package com.serigrafia;

import java.sql.*;

public class MySQLConnection {

    private final static String url = "jdbc:mysql://bb649b2a039426:a6fa9fc3@us-cdbr-east-04.cleardb.com/heroku_fe1314fdf977f6c?useSSL=false&serverTimezone=UTC&useLegacyDateTimeCode=false";
    private final static String username = "bb649b2a039426";
    private final static String password = "a6fa9fc3";

    public static void main(String[] args) {

        System.out.println("Connecting to server...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Server connected!");
            Statement stmt = null;
            ResultSet resultset = null;

            try {
                stmt = connection.createStatement();
                resultset = stmt.executeQuery("SHOW DATABASES;");

                if (stmt.execute("SHOW DATABASES;")) {
                    resultset = stmt.getResultSet();
                }

                while (resultset.next()) {
                    System.out.println(resultset.getString("Database"));
                }
            }
            catch (SQLException ex){
                // handle any errors
                ex.printStackTrace();
            }
            finally {
                // release resources
                if (resultset != null) {
                    try {
                        resultset.close();
                    } catch (SQLException sqlEx) { }
                    resultset = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { }
                    stmt = null;
                }

                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the server!", e);
        }
    }
}