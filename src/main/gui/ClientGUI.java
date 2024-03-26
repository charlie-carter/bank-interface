package gui;

import model.Client;
import model.accounts.Account;
import model.assets.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private Client client;
    private ClientGUI thisPage;
    private FontBook fb = new FontBook();

    public ClientGUI(Client c) {
        super("Bank of UBC");
        this.client = c;
        initializeGraphics();
        thisPage = this;

    }

    private void initializeGraphics() {
        setLayout(new BorderLayout()); //TODO: Replace with box layout
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        addTopLabels();
        createAccountBar();
        createAssetBar();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTopLabels() {
        JPanel topLabels = new JPanel();
        topLabels.setLayout(new GridLayout(1,2));
        JLabel accountLabel = new JLabel("Accounts: ");
        accountLabel.setFont(fb.getSubFont());
        accountLabel.setMaximumSize(new Dimension(100, 50));
        topLabels.add(accountLabel);
        this.add(topLabels, BorderLayout.PAGE_START);
    }

    private void createAccountBar() {
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(0,1));
        accountPanel.setSize(new Dimension(0, 0));

        for (Account a : client.getAccounts()) {
            JButton accountButton = new JButton("Account: # " + a.getAccountNumber());
            accountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newAccountWindow(a);
                }
            });

            accountPanel.add(accountButton);

        }
        this.add(accountPanel, BorderLayout.LINE_START);
    }

    private void createAssetBar() {
        JPanel assetPanel = new JPanel();
        assetPanel.setLayout(new GridLayout(0,1));
        assetPanel.setSize(new Dimension(0, 0));

        for (Asset a : client.getAssets()) {
            JButton assetButton = new JButton("Asset: # " + a.getAccountNumber());
            assetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newAssetWindow(a);
                }
            });

            assetPanel.add(assetButton);

        }
        this.add(assetPanel, BorderLayout.LINE_START);
    }

    private void newAccountWindow(Account a) {
        new AccountGUI(a, thisPage);
    }

    private void newAssetWindow(Asset a) {
        new AssetGUI(a, thisPage);
    }





//    private class newAccountWindow extends AbstractAction {
//
//        newAccountWindow() {
//            super();
//        }
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//        }
//    }

}
