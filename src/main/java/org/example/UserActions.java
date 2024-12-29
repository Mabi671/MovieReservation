package org.example;
import java.util.List;
import java.util.Map;

public class UserActions {
    public boolean ReqisterUser(String userName, String userPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String query = "Select Count(id) from users where " +
                "userName = '" + userName + "'";
        if (dbHandler.sendQuery(query, "Select",  new String[]{"count(id)"}).get(0).get("count(id)").equals("0")) {
           String q_String = "Insert into users Values(null, '"+userName+"', '"+userPassword+"');";
           System.out.println(q_String);
           dbHandler.sendQuery(q_String,"Update", null);
           return true;
       }
        return false;
    }
    public boolean LoginUser(String userName, String userPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String[] columns = {"userName", "userPassword"};
        String query = "Select userName, userPassword from users where " +
                "userName = '" + userName + "'";
        List<Map<String, String>> response = dbHandler.sendQuery(query, "Select", columns);
        if (!response.isEmpty()){
            String usrPass = response.get(0).get("userPassword");
            System.out.println(usrPass);
            if(usrPass.equals(userPassword))
            {
                System.out.println("Logged in as "+userName);
                return true;
            }else {
                System.out.println("Bad Password");
                return false;
            }
        }else{
            System.out.println("User does not exist in database");
            return false;
        }
    }
    /*
    Resereve, see reservations, cancel 'em
     */
}
