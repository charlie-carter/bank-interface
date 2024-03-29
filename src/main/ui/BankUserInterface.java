package ui;

import model.Client;
import model.accounts.Account;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;
import model.assets.Asset;
import model.assets.Bond;
import model.assets.GIC;
import model.assets.Stock;
import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

// Console UI for the client to manage their assets and accounts
public class BankUserInterface {

    Scanner input;
    private String password;
    private Client client;

    public BankUserInterface(Client c) {
        this.client = c;
        this.password = client.getPassword();
        runInterface();

    }

    //Main bank interface
    private void runInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        boolean running = login();

        while (running) {
            String menuPage;
            printMainMenu();
            menuPage = input.next();

            if (menuPage.equals("1")) {
                accountMenu();
            } else if (menuPage.equals("2")) {
                assetMenu();
            } else if (menuPage.equals("3")) { //trycatch, InputMismatchException
                changePassword();
            } else if (menuPage.equals("q")) {
                running = false;
            } else {
                System.out.println("Invalid selection, please choose from the following:");
            }
        }
    }

    //Login menu UI
    private boolean login() {
        String selection = "";
        Scanner passwordInput = new Scanner(System.in);
        passwordInput.useDelimiter("\n");

        while (!(selection.equals(password) || (selection.equals("q")))) {
            System.out.println("Enter password (q to quit): PASSWORD IS 'password123'");
            selection = passwordInput.next();
        }

        return !selection.equals("q");
    }

    //Main menu options
    private void printMainMenu() {
        System.out.println("1 to view accounts");
        System.out.println("2 to view assets");
        System.out.println("3 to change password");
        System.out.println("q to quit");

    }

    //Prints out accounts under current client, prompts user to enter an account to see more detail
    private void accountMenu() {
        System.out.println("Accounts:");

        for (Account a : client.getAccounts()) {
            System.out.println((client.getAccounts().indexOf(a) + 1) + " <-- " + a.getAccountInfo());
        }

        while (true) {
            System.out.println("Choose account (n to create a new account, q to exit): ");
            String accountOption = input.next();
            if (accountOption.equals("n")) {
                newAccount();
                break;
            } else if (accountOption.equals("q")) {
                break;
            } else {
                try {
                    int acctNum = Integer.parseInt(accountOption);
                    accountOptions(client.getAccounts().get(acctNum - 1));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("Not a valid option");
                }
            }
        }
    }

    //Choose options to manage assets
    private void assetMenu() {
        System.out.println("Assets:");

        for (Asset a : client.getAssets()) {
            System.out.println((client.getAssets().indexOf(a) + 1) + " <-- " + a.getAsset());
        }
        System.out.println("Choose asset (n to add a new asset, q to exit): ");


        while (true) {
            String assetOption = input.next();
            if (assetOption.equals("n")) {
                newAsset();
                break;
            } else if (assetOption.equals("q")) {
                break;
            } else {
                try {
                    int acctNum = Integer.parseInt(assetOption);
                    System.out.println(client.getAssets().get(acctNum - 1).getAssetInfo());
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("Not a valid option");
                }
            }
        }
    }

    //Print out deposit and withdraw options
    private void accountOptions(Account a) {
        System.out.println("Account number: " + a.getAccountNumber());
        System.out.println("Account balance: " + a.getBalance());
        while (true) {
            System.out.println("d to deposit, w to withdraw, i to see account info, q to exit");
            String selection = input.next();
            if (selection.equals("q")) {
                break;
            } else if (selection.equals("d")) {
                depositMoney(a);
            } else if (selection.equals("w")) {
                withdrawMoney(a);
            } else if (selection.equals("i")) {
                seeAccountInfo(a);
            }
        }
    }

    //UI to deposit money into account
    private void depositMoney(Account a) {
        System.out.println("Enter amount: ");
        double depositAmount = input.nextDouble();
        try {
            a.deposit(BigDecimal.valueOf(depositAmount));
        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount");
        }
        System.out.println("New account balance: $" + a.getBalance());
    }

    //UI to withdraw money from account
    private void withdrawMoney(Account a) {
        System.out.println("Enter amount: ");
        double withdrawAmount = input.nextDouble();
        try {
            a.withdraw(BigDecimal.valueOf(withdrawAmount));
        } catch (InvalidAmountException e) {
            System.out.println("Invalid Amount");
        }

        System.out.println("New account balance: $" + a.getBalance());
    }

    //Print out account information
    private void seeAccountInfo(Account a) {
        System.out.println("Interest: " + a.getInterest());
        System.out.println("Transaction fee: " + a.getTransactionFee());
        System.out.println("Annual fee: " + a.getAnnualFee());
    }

    //UI to create a new account under the current client
    private void newAccount() {
        while (true) {
            System.out.println("Enter s for savings account, c for chequing account, q to quit");
            String accountOption = input.next();
            if (accountOption.equals("q")) {
                break;
            } else if (accountOption.equals("s") | accountOption.equals("c")) {
                System.out.println("Enter balance: ");

                try {
                    double newBalance = input.nextDouble();
                    if (accountOption.equals("s")) {
                        client.addAccount(new SavingsAccount(newBalance));

                    } else {
                        client.addAccount(new ChequingAccount(newBalance));

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid amount");
                }

            }
        }
    }

    //UI to add a new asset under the current client
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

    //UI to specify Bond information
    private void newBond() {
        try {
            System.out.println("Enter issuer: ");
            String issuer = input.next();
            System.out.println("Enter value: ");
            double value = input.nextDouble();
            System.out.println("Enter yield: ");
            double yld = input.nextDouble();
            while (true) {
                System.out.println("Secured (y/n): ");
                String sec = input.next();
                if (sec.equals("y")) {
                    client.addAsset(new Bond(value, 2040, issuer, yld, true));
                    break;
                } else if (sec.equals("n")) {
                    client.addAsset(new Bond(value, 2040, issuer, yld, false));
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Bond Invalid");
        }

    }

    //UI to specify GIC information
    private void newGIC() {
        try {
            System.out.println("Enter value: ");
            double value = input.nextDouble();
            System.out.println("Enter Term: ");
            int term = input.nextInt();
            System.out.println("Enter rate: ");
            double rate = input.nextDouble();
            while (true) {
                System.out.println("Redeemable (y/n): ");
                String redeemable = input.next();
                if (redeemable.equals("y")) {
                    client.addAsset(new GIC(term, true, rate, value));
                    break;
                } else if (redeemable.equals("n")) {
                    client.addAsset(new GIC(term, false, rate, value));
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("GIC Invalid");
        }
    }

    //UI to specify stock information
    private void newStock() {
        try {
            System.out.println("Enter ticker: ");
            String ticker = input.next();
            System.out.println("Enter value: ");
            double value = input.nextDouble();
            System.out.println("Enter Number of Shares: ");
            int shares = input.nextInt();
            System.out.println("Enter expected annual return: ");
            double expectedReturn = input.nextDouble();
            while (true) {
                System.out.println("Voting Stock? (y/n): ");
                String voting = input.next();
                if (voting.equals("y")) {
                    client.addAsset(new Stock(ticker, shares, value, expectedReturn, true));
                    break;
                } else if (voting.equals("n")) {
                    client.addAsset(new Stock(ticker, shares, value, expectedReturn, false));
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Stock");
        }
    }

    //UI to change password
    private void changePassword() {
        while (true) {
            System.out.println("Enter current password (q to quit): ");
            String currentPassword = input.next();
            if (currentPassword.equals(client.getPassword())) {
                System.out.println("Enter new password: ");
                String newPassword = input.next();
                client.setPassword(newPassword);
                System.out.println("Password changed successfully!");
                break;
            } else if (currentPassword.equals("q")) {
                break;
            } else {
                System.out.println("Password incorrect");
            }
        }

    }
}
