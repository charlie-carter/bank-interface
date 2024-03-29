package ui.gui;

import model.Client;
import model.assets.GIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGIC extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    private FontBook fb = new FontBook();
    private Client client;
    private JLabel value = new JLabel("Value ($)");
    private JLabel rate = new JLabel("Rate (%)");
    private JLabel term = new JLabel("Term (Years)");
    private JLabel filler = new JLabel(" ");
    private JRadioButton redeemable = new JRadioButton("Redeemable?");
    private JTextField valueField = new JTextField();
    private JTextField rateField = new JTextField();
    private JTextField termField = new JTextField();

    public CreateGIC(Client client) {
        super("New GIC");
        this.client = client;
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        makeGIC();
        setVisible(true);
    }

    private void makeGIC() {
        JPanel newGIC = new JPanel();
        newGIC.setLayout(new GridLayout(0,2));
        JButton createGIC = getButton();

        newGIC.add(value);
        newGIC.add(valueField);
        newGIC.add(rate);
        newGIC.add(rateField);
        newGIC.add(term);
        newGIC.add(termField);
        newGIC.add(filler);
        newGIC.add(redeemable);
        newGIC.add(filler);
        newGIC.add(createGIC);

        add(newGIC);

    }

    private JButton getButton() {
        JButton createGIC = new JButton("Submit");
        createGIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valueDouble = Double.parseDouble(valueField.getText());
                double rateDouble = Double.parseDouble(rateField.getText());
                int termInt = Integer.parseInt(termField.getText());

                GIC tempGIC = new GIC(termInt, redeemable.isSelected(), rateDouble, valueDouble);
                client.addAsset(tempGIC);
                removePanel();
            }
        });
        return createGIC;
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }


}
