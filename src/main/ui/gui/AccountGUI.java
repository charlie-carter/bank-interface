package ui.gui;

import model.accounts.Account;
import model.exceptions.InvalidAmountException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AccountGUI extends JFrame {

    private Account account;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 300;
    private ClientGUI superPage;
    private String pageTitle;
    private JTextField amount = new JTextField();

    private FontBook fb = new FontBook();

    public AccountGUI(Account a, ClientGUI c) {
        super("Account: #" + a.getAccountNumber());
        this.account = a;
        openAccountPage();
        superPage = c;


    }

    private void openAccountPage() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(superPage);

        setVisible(true);

        printAccountInfo();
        printAccountOptions();

    }

    private void printAccountOptions() {
        JPanel accountOptions = new JPanel();
        accountOptions.setLayout(new GridLayout(0, 1, 15, 15));
        accountOptions.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton withdraw = getWithdrawButton();
        JButton deposit = getDepositButton();

        accountOptions.add(amount);
        accountOptions.add(withdraw);
        accountOptions.add(deposit);
        add(accountOptions);
    }

    private JButton getWithdrawButton() {
        JButton createButton = new JButton("Withdraw");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amt = Double.parseDouble(amount.getText());
                try {
                    account.withdraw(BigDecimal.valueOf(amt));
                    removePanel();
                } catch (InvalidAmountException ex) {
                    //TODO: Invalid amount page
                }

            }
        });
        return createButton;
    }

    private JButton getDepositButton() {
        JButton createButton = new JButton("Deposit");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amt = Double.parseDouble(amount.getText());
                try {
                    account.deposit(BigDecimal.valueOf(amt));
                    removePanel();
                } catch (InvalidAmountException ex) {
                    //TODO: Invalid amount page
                }

            }
        });
        return createButton;
    }


    private void printAccountInfo() {
        JPanel accountInfo = new JPanel();
        accountInfo.setLayout(new GridLayout(0, 1, 15, 15));
        accountInfo.setSize(new Dimension(0, 0));
        accountInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
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

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
