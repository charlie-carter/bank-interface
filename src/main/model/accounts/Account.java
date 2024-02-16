package model.accounts;

import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public abstract class Account {
    protected BigDecimal balance = new BigDecimal(0.00);
    protected BigDecimal annualFee;
    protected BigDecimal transactionFee;
    protected int accountNumber;
    protected double interest;

    Account(BigDecimal amount) {
        this.balance = amount;
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

    public abstract void withdraw(BigDecimal amount);

    public abstract String getAccountInfo();

    public BigDecimal getBalance() {
        return balance;
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
