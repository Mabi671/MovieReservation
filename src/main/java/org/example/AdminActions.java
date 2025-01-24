package org.example;

public class AdminActions {
    private final DatabaseHandler dbHandler = new DatabaseHandler("Admin", "adminzxc");
    public void addMovie(String[] movieInfo) {
        String check_Query = "Select id from titles where title = '"+movieInfo[0]+"';";
        if(!dbHandler.sendQuery(check_Query, "Select", new String[]{"id"}).isEmpty()) return;

        String query = "Insert into titles VALUES(null, '"+movieInfo[0]+"', '"+movieInfo[1]+"', '"+movieInfo[2]+"', '"+movieInfo[3]+"');";
        dbHandler.sendQuery(query, "Update", null);}
    public void deleteMovie(String movieName) {
        String check_Query = "Select id from titles where title = '"+movieName+"';";
        if(dbHandler.sendQuery(check_Query, "Select", new String[]{"id"}).isEmpty()) return;

        String query = "Delete from titles where title = '"+movieName+"'";
        dbHandler.sendQuery(query, "Update", null);
    }
    public void addShowtime(String movieTitle, String date, String time, String capacity) {
        String check_Query = "Select id from titles where title = '"+movieTitle+"';";
        if(dbHandler.sendQuery(check_Query, "Select", new String[]{"id"}).isEmpty()) return;

        String query = "Insert into Showtimes Values(null, '"+movieTitle+"', '"+date+"','"+time+"', "+capacity+");";
        dbHandler.sendQuery(query, "Update", null);
    }
}
