package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {
    final private String login;
    final private String password;
    DatabaseHandler(String login, String password){
        this.login = login;
        this.password = password;
    }
    String url = "jdbc:mysql://localhost:3306/Movies";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int affected_rows = -1;
    List<Map<String, String>> result = new ArrayList<>();
    public List<Map<String, String>> sendQuery(String query_String, String type, String[] columns) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connected to the database!");

            statement = connection.createStatement();
            if(type.equals("Update")) {
                affected_rows = statement.executeUpdate(query_String);
            }else{
                resultSet = statement.executeQuery(query_String);
                while (resultSet.next()) {
                    Map<String, String> map = new HashMap<>();;
                    for (String col : columns) {
                        map.put(col, resultSet.getString(col));
                    }
                    result.add(map);
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
