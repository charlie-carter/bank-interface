package ui.gui;

import model.Client;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// This is the login page of the banking application, it lets the user either enter their name and password,
// or create a new account.
public class LoginGUI extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 100;
    private static final String JSON_STORE = "./data/client.json";
    private FontBook fb = new FontBook();
    private JsonReader jsonReader;
    private Client client;

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();

    public LoginGUI() {
        super("Bank of UBC");
        jsonReader = new JsonReader(JSON_STORE);
        loadData();
        initializeGraphics();

        setVisible(true);

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: Loads any previous client data that was saved
    private void loadData() {
        try {
            client = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Generates username and passwords field, as well as login and new user buttons
    private void initializeGraphics() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        loginPanel.setLayout(new GridLayout(2, 2));


        JLabel usernameHint = new JLabel("Username: ");
        JLabel passwordHint = new JLabel("Password: ");
        usernameHint.setFont(fb.getHeaderTwo());
        passwordHint.setFont(fb.getHeaderTwo());
        JButton submit = new JButton("Login");
        JButton newClient = new JButton("Register");
        submit.addActionListener(new Login());
        newClient.addActionListener(new NewUser());

        loginPanel.add(usernameHint);
        loginPanel.add(username);
        loginPanel.add(submit);
        loginPanel.add(passwordHint);
        loginPanel.add(password);
        loginPanel.add(newClient);
        add(loginPanel);
    }

    //EFFECTS: listener for login button, if passwords is correct opens new ClientGUI page
    private class Login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameString = username.getText();
            String passString = password.getText();

            if (nameString.equals(client.getName()) && passString.equals(client.getPassword())) {
                new ClientGUI(client);
                removePanel();
            }
        }
    }

    //EFFECTS: Listener to create a user, opens a page for user to enter their information to open a new client account.
    private class NewUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateClient();
            removePanel();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes this panel from the screen
    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
