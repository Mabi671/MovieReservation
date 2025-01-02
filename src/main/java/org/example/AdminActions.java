package org.example;

public class AdminActions {
    public void addMovie(String[] movieInfo)
    {
        DatabaseHandler dbHandler = new DatabaseHandler("Admin", "adminzxc");
        String query = "Insert into titles VALUES(null, '"+movieInfo[0]+"', '"+movieInfo[1]+"', '"+movieInfo[2]+"', '"+movieInfo[3]+"');";
        dbHandler.sendQuery(query, "Update", null);
    }

}
