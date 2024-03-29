package ui.gui;

import model.Client;
import model.assets.Bond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBond extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    private FontBook fb = new FontBook();
    private Client client;
    private JLabel issuer = new JLabel("Bond Issuer");
    private JLabel value = new JLabel("Value ($)");
    private JLabel yom = new JLabel("Year of Maturity");
    private JLabel rate = new JLabel("Yield (%)");
    private JLabel filler = new JLabel(" ");
    private JRadioButton secured = new JRadioButton("Secured?");
    private JTextField valueField = new JTextField();
    private JTextField issuerField = new JTextField();
    private JTextField rateField = new JTextField();
    private JTextField yomField = new JTextField();

    public CreateBond(Client client) {
        super("New Bond");
        this.client = client;
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        makeBond();
        setVisible(true);
    }

    private void makeBond() {
        JPanel newBond = new JPanel();
        newBond.setLayout(new GridLayout(0,2));
        JButton createBond = getButton();

        newBond.add(issuer);
        newBond.add(issuerField);
        newBond.add(value);
        newBond.add(valueField);
        newBond.add(yom);
        newBond.add(yomField);
        newBond.add(rate);
        newBond.add(rateField);
        newBond.add(filler);
        newBond.add(secured);
        newBond.add(filler);
        newBond.add(createBond);

        add(newBond);

    }

    private JButton getButton() {
        JButton createBond = new JButton("Submit");
        createBond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valueDouble = Double.parseDouble(valueField.getText());
                double rateDouble = Double.parseDouble(rateField.getText());
                int yomInt = Integer.parseInt(yomField.getText());
                String issuerString = issuerField.getText();

                Bond tempBond = new Bond(valueDouble, yomInt, issuerString, rateDouble, secured.isSelected());
                client.addAsset(tempBond);
                removePanel();
            }
        });
        return createBond;
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
