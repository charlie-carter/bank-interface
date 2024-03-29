package ui.gui;

import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAsset extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 100;
    private FontBook fb = new FontBook();
    private Client client;
    private JPanel assetOptions = new JPanel();
    private JButton newBond = new JButton("Add Bond");
    private JButton newStock = new JButton("Add Stock");
    private JButton newGIC = new JButton("Add GIC");

    public CreateAsset(Client client) {
        super("New Asset");
        this.client = client;
        initializeGraphics();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

    private void initializeGraphics() {

        assetOptions.setLayout(new FlowLayout(FlowLayout.CENTER));

        newBond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBond(client);


            }
        });

        newStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateStock(client);

            }
        });

        newGIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGIC(client);

            }
        });
        assetOptions.add(newBond);
        assetOptions.add(newGIC);
        assetOptions.add(newStock);
        add(assetOptions);

    }



}
