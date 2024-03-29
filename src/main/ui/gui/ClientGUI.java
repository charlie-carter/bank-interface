package ui.gui;

import model.Client;
import model.accounts.Account;
import model.assets.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 400;
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
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createAccountBar();
        createAssetBar();
        createOptionsBar();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createOptionsBar() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 1));
        JButton changePassword = new JButton("Change Password");
        JButton refresh = new JButton("Refresh Page");
        JButton exit = new JButton("Exit");
        changePassword.addActionListener(new ChangePassword());
        refresh.addActionListener(new RefreshPage());
        exit.addActionListener(new Exit());
        optionsPanel.add(changePassword);
        optionsPanel.add(refresh);
        optionsPanel.add(exit);

        add(optionsPanel);
    }


    private void createAccountBar() {
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(0,1));
        accountPanel.setSize(new Dimension(0, 0));
        JLabel accountLabel = new JLabel("Accounts: ");
        accountLabel.setFont(fb.getSubFont());
        accountPanel.add(accountLabel);
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
        JButton newAccount = new JButton("New Account");
        newAccount.addActionListener(new NewAccount());
        accountPanel.add(newAccount);
        this.add(accountPanel, BorderLayout.LINE_START);
    }

    private void createAssetBar() {
        JPanel assetPanel = new JPanel();
        assetPanel.setLayout(new GridLayout(0,1));
        assetPanel.setSize(new Dimension(0, 0));
        JLabel assetLabel = new JLabel("Assets: ");
        assetLabel.setFont(fb.getSubFont());
        assetPanel.add(assetLabel);

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
        JButton newAsset = new JButton("New Asset");
        newAsset.addActionListener(new NewAsset());
        assetPanel.add(newAsset);
        this.add(assetPanel, BorderLayout.LINE_START);
    }

    private void newAccountWindow(Account a) {
        new AccountGUI(a, thisPage);
    }

    private void newAssetWindow(Asset a) {
        new AssetGUI(a, thisPage);
    }

    private class NewAccount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateAccount(client);
        }
    }

    private class NewAsset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateAsset(client);
        }
    }

    private class ChangePassword implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ChangeClientPassword(client);
        }
    }

    private class RefreshPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ClientGUI(client);
            removePanel();
        }
    }

    private class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ExitPage(client);
            removePanel();
        }
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }


}
