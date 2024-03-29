package ui.gui;

import model.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClient extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 100;
    private FontBook fb = new FontBook();

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();

    public CreateClient() {
        super("Bank of UBC");
        initializeGraphics();
        setVisible(true);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeGraphics() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        loginPanel.setLayout(new GridLayout(2, 2));


        JLabel usernameHint = new JLabel("Username: ");
        JLabel passwordHint = new JLabel("Password: ");
        usernameHint.setFont(fb.getHeaderTwo());
        passwordHint.setFont(fb.getHeaderTwo());
        JButton submit = new JButton("Create Account");
        submit.addActionListener(new Login());

        loginPanel.add(usernameHint);
        loginPanel.add(username);
        loginPanel.add(submit);
        loginPanel.add(passwordHint);
        loginPanel.add(password);

        add(loginPanel);
    }

    private class Login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameString = username.getText();
            String passString = password.getText();

            new ClientGUI(new Client(nameString, passString));
            removePanel();
        }
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
