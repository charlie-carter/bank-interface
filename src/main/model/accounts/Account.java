package model.accounts;

import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public abstract class Account {
    protected BigDecimal balance;
    protected BigDecimal annualFee;
    protected BigDecimal transactionFee;
    protected int accountNumber;
    protected double interest;
    Random rnd = new Random();

    Account(BigDecimal amount) {
        this.balance = amount;
        balance.setScale(2, RoundingMode.DOWN);
        this.accountNumber = 100000 + rnd.nextInt(900000);
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to balance
    public void deposit(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException();
        } else {
            balance = balance.add(amount);
        }
    }

    public abstract void withdraw(BigDecimal amount) throws InvalidAmountException;

    public abstract String getAccountInfo();

    public double getBalance() {
        return balance.doubleValue();
    }

    public double getInterest() {
        return interest;
    }

    public BigDecimal getAnnualFee() {
        return annualFee;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
