package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminActions {
    public void addMovie(String[] movieInfo) {
        DatabaseHandler dbHandler = new DatabaseHandler("Admin", "adminzxc");
        String query = "Insert into titles VALUES(null, '"+movieInfo[0]+"', '"+movieInfo[1]+"', '"+movieInfo[2]+"', '"+movieInfo[3]+"');";
        dbHandler.sendQuery(query, "Update", null);}
    public void deleteMovie(String movieName) {
        DatabaseHandler dbHandler = new DatabaseHandler("Admin", "adminzxc");
        String query = "Delete from titles where title = '"+movieName+"'";
        dbHandler.sendQuery(query, "Update", null);
    }
    public void addShowtime(String movieTitle, String date, String time, String capacity) {
        DatabaseHandler dbHandler = new DatabaseHandler("Admin", "adminzxc");
        String query = "Insert into Showtimes Values(null, '"+movieTitle+"', '"+date+"','"+time+"', "+capacity+");";
        System.out.println(query);
        dbHandler.sendQuery(query, "Update", null);
    }
}
