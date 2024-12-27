package org.example;


public class UserActions {
    public boolean ReqisterUser(String userName, String userPassword)
    {
           DatabaseHandler dbHandler = new DatabaseHandler();
           if (dbHandler.sendQuery("Select Count(id) from users where userName = '"+userName+"'","Select", "count(id)").equals("0"))
           {
               String q_String = "Insert into users Values(null, '"+userName+"', '"+userPassword+"');";
               System.out.println(q_String);
               dbHandler.sendQuery(q_String,"Update", null);
           }
        return true;
    }
}
