package org.example;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserActions userA = new UserActions();
        userA.ReqisterUser("Milosnik", "legii");
    }
}
