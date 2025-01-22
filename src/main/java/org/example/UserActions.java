package org.example;
import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserActions {
    private int userId;
    public boolean RegisterUser(String userName, String userPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
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
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
        String[] columns = {"id", "userName", "userPassword"};
        String query = "Select id, userName, userPassword from users where " +
                "userName = '" + userName + "'";
        List<Map<String, String>> response = dbHandler.sendQuery(query, "Select", columns);
        if (!response.isEmpty()){
            String usrPass = response.get(0).get("userPassword");
            System.out.println(usrPass);
            if(usrPass.equals(userPassword)) {
                System.out.println("Logged in as "+userName);
                userId = Integer.parseInt(response.get(0).get("id"));
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
    public void seeShowtimes(String movieTitle) {
        DatabaseHandler dbHandler = new DatabaseHandler("mortal","");
        String query = "Select * from Showtimes where movie_title = '"+movieTitle+"';";
        String[] columns = {"date", "time"};
        List<Map<String, String>> response = dbHandler.sendQuery(query, "Select", columns);
        for(Map<String, String> res : response) {
            System.out.println(res.get("date")+ " " + res.get("time"));
        }
    }
    public void seeFreeSeats(String movie_title, String date, String time){
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
        String query = "Select id from Showtimes where movie_title ='"+movie_title+"' and date ='"+date+"'" +
                " and time ='"+time+"';";
        String[] columns = {"id"};
        int seats_id = Integer.parseInt(dbHandler.sendQuery(query, "Select", columns).get(0).get("id"));
        DatabaseHandler dbHandler2 = new DatabaseHandler("mortal", "");
        String nd_query = "Select A, B, C, D, E, F, G, H, I, J from seats where id = '"+seats_id+"';";
        String[] cols = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        List<Map<String, String>> res = dbHandler2.sendQuery(nd_query, "Select", cols);
        for (Map.Entry<String, String> entry : res.get(0).entrySet()) {

            if(entry.getValue().equals("0")){
                System.out.println(entry.getKey());
            }
        }
    }
    public void reserveSeat(int seats_id, String seat) {
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
        String query = "Update seats set "+seat+" = "+userId+" where id = "+seats_id+";";
        dbHandler.sendQuery(query, "Update", null);
    }
    public void seeReservations() {
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
        String query = "Select * from seats JOIN showtimes ON showtimes.id = seats.id where A = "+userId+" or B = "+userId+" or C = "+userId+" or D = "+userId+" or E = "+userId+" or " +
                "F = "+userId+" or G = "+userId+" or H = "+userId+" or I = "+userId+" or J = "+userId+";";
        System.out.println(query);
        String[] cols = {"id", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "movie_title", "date", "time"};
        List<Map<String, String>> res = dbHandler.sendQuery(query, "Select", cols);
        for(Map<String, String> re : res){
            List<String> seats = new ArrayList<>();
            for(Map.Entry<String, String> entry: re.entrySet()){
                if(entry.getKey().equals("id")) continue;
                if (entry.getValue().equals(String.valueOf(userId))) {
                    seats.add(entry.getKey());
                }
            }
            System.out.println(re.get("movie_title") +" "+ re.get("date") +" "+ re.get("time") +" "+ seats);
        }

    }
    public void cancelReservations(String seat, String movie_title, String date, String time){
        DatabaseHandler dbHandler = new DatabaseHandler("mortal", "");
        String query = "update seats set "+seat+" = 0 where id = (select id from showtimes where movie_title = " +
                "'"+movie_title+"' and date = '"+date+"' and time = '"+time+"' limit 1);";
        dbHandler.sendQuery(query, "Update", null);
    }

}


    /*
    Resereve, see reservations, cancel
     */

