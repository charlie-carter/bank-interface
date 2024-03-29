package ui.gui;

import model.Client;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 80;
    private FontBook fb = new FontBook();
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
                client.addAccount(new SavingsAccount(amt));
                removePanel();
            }
        });

        chqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amt = Double.parseDouble(amountField.getText());
                client.addAccount(new ChequingAccount(amt));
                removePanel();
            }
        });

        add(accountPanel);
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
