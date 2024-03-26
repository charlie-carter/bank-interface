package ui;

import gui.ClientGUI;
import model.Client;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadClientInterface {
    Scanner input;
    private static final String JSON_STORE = "./data/client.json";
    private Client user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LoadClientInterface() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        welcomePage();
    }

    private void welcomePage() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("Welcome to the bank!");

        while (true) {
            System.out.println("\t 'l' to load saved information");
            System.out.println("\t 'n' to create new client");
            System.out.println("\t 'q' to close application");
            String userChoice = input.next();
            if (userChoice.equals("l")) {
                loadClient();
                break;
            } else if (userChoice.equals("n")) {
                newClient();
                break;
            } else if (userChoice.equals("q")) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    //Templated from phase 2 example
    //MODIFIES: this
    //EFFECTS: loads client data
    private void loadClient() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + " from " + JSON_STORE);
            //new BankUserInterface(user);
            new ClientGUI(user);
            exitPage();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new client if the user chooses to not load past data
    private void newClient() {
        user = new Client("John Lastname", "password123");
        new BankUserInterface(user);
        exitPage();
    }

    //EFFECTS: Asks to user if they'd like to save their information
    private void exitPage() {
        System.out.println("Would you like to save your information?");
        while (true) {
            System.out.println("\t 's' to save");
            System.out.println("\t 'd' to discard");
            String userChoice = input.next();
            if (userChoice.equals("s")) {
                saveClient();
                System.out.println("Goodbye");
                break;
            } else if (userChoice.equals("d")) {
                System.out.println("Goodbye");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    //Templated from phase 2 example
    // EFFECTS: saves the client to file
    private void saveClient() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved " + user.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
