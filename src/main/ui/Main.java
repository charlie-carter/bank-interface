package ui;

import model.Client;

public class Main {


    public static void main(String[] args) {

        Client testUser;
        testUser = new Client("John Lastname", 1234, "password123");
        new BankUserInterface(testUser);
    }
}
