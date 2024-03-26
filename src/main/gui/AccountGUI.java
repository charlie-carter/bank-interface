package gui;

import model.accounts.Account;

import javax.swing.*;
import java.awt.*;

public class AccountGUI extends JFrame {

    private Account account;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 300;
    private ClientGUI superPage;
    private String pageTitle;

    private FontBook fb = new FontBook();

    public AccountGUI(Account a, ClientGUI c) {
        super("Account: #" + a.getAccountNumber());
        this.account = a;
        openAccountPage();
        superPage = c;


    }

    private void openAccountPage() {
        setLayout(new BorderLayout(15, 15));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(superPage);
        setVisible(true);

        printAccountInfo();

    }

    private void printAccountInfo() {
        JPanel accountInfo = new JPanel();
        accountInfo.setLayout(new GridLayout(0, 1, 15, 2));
        accountInfo.setSize(new Dimension(0, 0));
        JLabel acctName = new JLabel(account.getAccountInfo());
        acctName.setFont(fb.getSubFont());
        JLabel acctNo = new JLabel("Account Number: " + account.getAccountNumber());
        acctNo.setFont(fb.getHeaderOne());
        JLabel interest = new JLabel("Interest: " + account.getInterest() + "%");
        interest.setFont(fb.getHeaderTwo());
        JLabel annualFee = new JLabel("Annual Fee: $" + account.getAnnualFee());
        annualFee.setFont(fb.getHeaderTwo());
        JLabel transationFee = new JLabel("Transaction Fee: $" + account.getTransactionFee());
        transationFee.setFont(fb.getHeaderTwo());

        accountInfo.add(acctName);
        accountInfo.add(acctNo);
        accountInfo.add(interest);
        accountInfo.add(annualFee);
        accountInfo.add(transationFee);

        this.add(accountInfo, BorderLayout.WEST);
    }
}
