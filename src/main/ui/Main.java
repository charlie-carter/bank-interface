package ui;

import model.Client;
import model.accounts.Account;
import model.accounts.SavingsAccount;

public class Main {


    public static void main(String[] args) {

        Client testUser;
        //Account testAccount = new SavingsAccount(232.12);
        testUser = new Client("John Lastname", "password123");
       // testUser.addAccount(testAccount);
        new BankUserInterface(testUser);
    }
}
