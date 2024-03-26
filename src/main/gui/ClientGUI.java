package gui;

import model.Client;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private Client client;

    public ClientGUI(Client c) {
        super("Bank of UBC");
        this.client = c;
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createAccountBar();
        //TODO: createAssetBar();
       // addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createAccountBar() {
        JPanel accountArea = new JPanel();
        accountArea.setLayout(new GridLayout(0,2));
        accountArea.setSize(new Dimension(0, 0));
        add(accountArea, BorderLayout.WEST);
    }

}
