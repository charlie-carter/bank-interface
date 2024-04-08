package ui.gui;

import model.Client;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI to enter information to create a new client
public class CreateAccount extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 80;
    private Client client;
    private JLabel amount = new JLabel("Amount ($)");
    private JTextField amountField = new JTextField();
    private JButton savButton = new JButton("New Savings Account");
    private JButton chqButton = new JButton("New Chequing Account");
    private JPanel accountPanel = new JPanel();

    public CreateAccount(Client client) {
        this.client = client;
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeGraphics();
        setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: Generates fields for the user to input a username and password
    private void initializeGraphics() {
        accountPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        accountPanel.setLayout(new GridLayout(1, 4));
        accountPanel.add(amount);
        accountPanel.add(amountField);
        accountPanel.add(savButton);
        accountPanel.add(chqButton);

        savButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amt = Double.parseDouble(amountField.getText());
                client.addAccount(new SavingsAccount(amt), true);
                removePanel();
            }
        });

        chqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amt = Double.parseDouble(amountField.getText());
                client.addAccount(new ChequingAccount(amt), true);
                removePanel();
            }
        });

        add(accountPanel);
    }

    //MODIFIES: this
    //EFFECTS: removes this panel from the screen
    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
