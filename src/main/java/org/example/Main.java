package org.example;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserActions userA = new UserActions();
        if(userA.ReqisterUser("Milosnik", "legii")) {
            System.out.println("User successfully registered");
        }
        if(userA.LoginUser("Milosnik", "legii")){
            System.out.println("User logged in");
        }

    }
}
