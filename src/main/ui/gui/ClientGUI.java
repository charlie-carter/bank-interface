package ui.gui;

import model.Client;
import model.accounts.Account;
import model.assets.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class generates the main menu for the banking interface.
// It has buttons to look further into any of the client's accounts or assets,
// it also has the options to change the client password, refresh the page, and leave the application
public class ClientGUI extends ExitingJFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;
    private Client client;
    private FontBook fb = new FontBook();
    private ImageIcon ubcLogo;
    private JLabel imageLabel;

    public ClientGUI(Client c) {
        super("Bank of UBC");
        this.client = c;
        initializeGraphics();
    }

    //MODIFIES: this
    //EFFECTS: Generates the four columns of the main landing screen
    private void initializeGraphics() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        loadImages();
        createAccountBar();
        createAssetBar();
        createOptionsBar();
        addImages();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: generates bar of management options for the client
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


    //MODIFIES: this
    //EFFECTS: generates list of account buttons, and new account button
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

    //MODIFIES: this
    //EFFECTS: generates list of asset buttons, and a button to add more assets
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

    //EFFECTS: Opens a new window to create a new account
    private void newAccountWindow(Account a) {
        new AccountGUI(a);
    }

    //EFFECTS: Opens a new window to create a new asset
    private void newAssetWindow(Asset a) {
        new AssetGUI(a);
    }


    //EFFECTS: Listens for new account button press
    private class NewAccount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateAccount(client);
        }
    }

    //EFFECTS: Listens for new asset button press
    private class NewAsset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateAsset(client);
        }
    }

    //EFFECTS: Listens for new password change button press
    private class ChangePassword implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ChangeClientPassword(client);
        }
    }

    //EFFECTS: Listens for new page refresh button press
    private class RefreshPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ClientGUI(client);
            removePanel();
        }
    }

    //EFFECTS: Listens for new exit button press
    private class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ExitPage(client);
            removePanel();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes this window from the screen
    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }

    //MODIFIES: this
    //EFFECTS: loads the ubc logo into a label
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        ubcLogo = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "logo.png");

        imageLabel = new JLabel(ubcLogo);
    }

    //MODIFIES: this
    //EFFECTS: adds ubc logo to main page
    private void addImages() {
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        add(imagePanel);
    }


}
