package ui.gui;

import model.Client;
import model.assets.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for the user to input information to create a new Stock
public class CreateStock extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private Client client;
    private JLabel ticker = new JLabel("Stock (Ticker)");
    private JLabel value = new JLabel("Value ($)");
    private JLabel shares = new JLabel("Number of Shares");
    private JLabel rate = new JLabel("Expected Annual Return (%)");
    private JLabel filler = new JLabel(" ");
    private JRadioButton secured = new JRadioButton("Voting Stock?");
    private JTextField valueField = new JTextField();
    private JTextField tickerField = new JTextField();
    private JTextField rateField = new JTextField();
    private JTextField sharesField = new JTextField();

    public CreateStock(Client client) {
        super("New Stock");
        this.client = client;
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        makeStock();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Generates fields to input Stock information
    private void makeStock() {
        JPanel newStock = new JPanel();
        newStock.setLayout(new GridLayout(0,2));
        JButton createStock = getButton();

        newStock.add(ticker);
        newStock.add(tickerField);
        newStock.add(value);
        newStock.add(valueField);
        newStock.add(shares);
        newStock.add(sharesField);
        newStock.add(rate);
        newStock.add(rateField);
        newStock.add(filler);
        newStock.add(secured);
        newStock.add(filler);
        newStock.add(createStock);

        add(newStock);

    }

    //EFFECTS: generates a button and behaviour to create a new stock
    private JButton getButton() {
        JButton createStock = new JButton("Submit");
        createStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valueDouble = Double.parseDouble(valueField.getText());
                double rateDouble = Double.parseDouble(rateField.getText());
                int sharesInt = Integer.parseInt(sharesField.getText());
                String tickerString = tickerField.getText();

                Stock tempStock = new Stock(tickerString, sharesInt, valueDouble, rateDouble, secured.isSelected());
                client.addAsset(tempStock, true);
                removePanel();

            }
        });
        return createStock;

    }

    //MODIFIES: this
    //EFFECTS: removes this panel from the screen
    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
