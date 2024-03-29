package ui.gui;

import model.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeClientPassword extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 80;
    private FontBook fb = new FontBook();
    private Client client;

    public ChangeClientPassword(Client client) {
        super("Change Password");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.client = client;
        initializeGraphics();
        setVisible(true);
    }

    private void initializeGraphics() {
        JPanel passwordChange = new JPanel();
        passwordChange.setBorder(new EmptyBorder(5, 5, 5, 5));
        passwordChange.setLayout(new GridLayout(0,3));
        JLabel newPassword = new JLabel("New Password: ");
        JTextField newPasswordField = new JTextField();
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setPassword(newPasswordField.getText());
                removePanel();
            }
        });

        passwordChange.add(newPassword);
        passwordChange.add(newPasswordField);
        passwordChange.add(submit);

        add(passwordChange);

    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
