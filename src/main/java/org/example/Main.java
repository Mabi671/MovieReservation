package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*
        UserActions userA = new UserActions();
        if(userA.RegisterUser("Milosnik", "legii")) {
            System.out.println("User successfully registered");
        }

         */


        UserActions userA = new UserActions();
        if(userA.LoginUser("Milosnik", "kogostam")){
            System.out.println("User logged in");
            userA.seeReservations();
        }


         /*
        AdminActions adminA = new AdminActions();
        String[] movieData = {"Szybcy i spokojni", " Fajny film xd", "kulimidz.png", "romance"};
        adminA.addMovie(movieData);


        AdminActions adminA = new AdminActions();
        adminA.deleteMovie("Szybcy i spokojni");


        AdminActions adminA = new AdminActions();
        adminA.addShowtime("Szybcy i spokojni","2025-01-03", "15:30:45", "100");

          */
    }
}
