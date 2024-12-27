package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHandler {
    String url = "jdbc:mysql://localhost:3306/Movies";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int affected_rows = -1;
    String result = "";
    public String sendQuery(String query_String, String type, String column) {
        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, "root", "legia");
            System.out.println("Connected to the database!");

            // Create a statement
            statement = connection.createStatement();
            if(type.equals("Update")) {
                affected_rows = statement.executeUpdate(query_String);
            }else{
                resultSet = statement.executeQuery(query_String);
                while (resultSet.next())
                {
                    result = resultSet.getString(column);
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
