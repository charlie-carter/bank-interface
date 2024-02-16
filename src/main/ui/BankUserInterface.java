package ui;

import model.Client;
import model.accounts.Account;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;
import model.assets.Asset;

import java.util.ArrayList;
import java.util.Scanner;

public class BankUserInterface {

    Scanner input;
    private String password;
    private Client client;

    public BankUserInterface(Client c) {
        this.client = c;
        this.password = client.getPassword();
        runInterface();

    }

    private void runInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        boolean running = login();

        while (running) {
            int menuPage;
            printMainMenu();
            menuPage = input.nextInt();

            if (menuPage == 1) {
                accountMenu();
            } else if (menuPage == 2) {
                assetMenu();
            } else if (menuPage == 3) {
                changePassword();
            } else if (menuPage == 4) {
                running = false;
            } else {
                System.out.println("Invalid selection, please choose from the following:");
            }
        }
    }

    private boolean login() {
        String selection = "";
        Scanner passwordInput = new Scanner(System.in);
        passwordInput.useDelimiter("\n");

        while (!(selection.equals(password) || (selection.equals("q")))) {
            System.out.println("Enter password (q to quit): ");
            selection = passwordInput.next();
        }

        return !selection.equals("q");
    }

    private void printMainMenu() {
        System.out.println("1 to view accounts");
        System.out.println("2 to view assets");
        System.out.println("3 to change password");
        System.out.println("4 to quit");

    }

    private void accountMenu() {
        ArrayList<Account> accounts = client.getAccounts();
        System.out.println("Accounts:");

        for (Account a : accounts) {
            System.out.println((accounts.indexOf(a) + 1) + " <-- " + a.getAccountInfo());
        }
        System.out.println("Choose account (n to create a new account, q to exit): ");


        while (true) {
            String accountOption = input.next();
            if (accountOption.equals("n")) {
                newAccount();
            } else if (accountOption.equals("q")) {
                break;
            } else {
                try {
                    int acctNum = Integer.parseInt(accountOption);
                    accountOptions(accounts.get(acctNum - 1));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("Not a valid option");
                }
            }
        }
    }

    private void assetMenu() {
        ArrayList<Asset> assets = client.getAssets();
        System.out.println("Assets:");

        for (Asset a : assets) {
            System.out.println((assets.indexOf(a) + 1) + " <-- " + a.getAsset());
        }
        System.out.println("Choose asset (n to add a new asset, q to exit): ");


        while (true) {
            String assetOption = input.next();
            if (assetOption.equals("n")) {
                newAsset();
            } else if (assetOption.equals("q")) {
                break;
            } else {
                try {
                    int acctNum = Integer.parseInt(assetOption);
                    assets.get(acctNum - 1).getAssetInfo();
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("Not a valid option");
                }
            }
        }
    }

    private void accountOptions(Account a) {
        System.out.println("Account number: " + a.getAccountNumber());
        System.out.println("Account balance: " + a.getBalance());
        while (true) {
            System.out.println("d to deposit, w to withdraw, i to see account info, q to exit");
            String selection = input.next();
            if (selection.equals("q")) {
                break;
            } else if (selection.equals("d")) {
                //TODO deposit
            } else if (selection.equals("w")) {
                //TODO withdraw
            } else if (selection.equals("i")) {
                seeAccountInfo(a);
            }
        }
    }

    private void seeAccountInfo(Account a) {
        System.out.println("Interest: " + a.getInterest());
        System.out.println("Transaction fee: " + a.getTransactionFee());
        System.out.println("Annual fee: " + a.getAnnualFee());
    }

    private void newAccount() {
        while (true) {
            System.out.println("Enter s for savings account");
            System.out.println("Enter c for chequing account");
            System.out.println("q to quit");
            String accountOption = input.next();
            if (accountOption.equals("q")) {
                break;
            } else if (accountOption.equals("s") | accountOption.equals("c")) {
                System.out.println("Enter balance: ");
                double newBalance = input.nextDouble();
                if (accountOption.equals("s")) {
                    client.addAccount(new SavingsAccount(newBalance));  //This might not work idk
                } else {
                    client.addAccount(new ChequingAccount(newBalance));
                }
            }

        }

    }

    private void newAsset() {
        while (true) {
            System.out.println("Enter b for Bond");
            System.out.println("Enter g for GIC");
            System.out.println("Enter s for Stock");
            System.out.println("q to quit");
            String assetOption = input.next();
            if (assetOption.equals("q")) {
                break;
            } else if (assetOption.equals("b")) {
                newBond();
            } else if (assetOption.equals("g")) {
                newGIC();
            } else if (assetOption.equals("s")) {
                newStock();
            }

        }
    }

    private void newBond() {

    }

    private void newGIC() {

    }

    private void newStock() {

    }

    private void changePassword() {
        //TODO
    }
}
