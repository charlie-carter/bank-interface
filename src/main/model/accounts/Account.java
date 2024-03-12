package model.accounts;

import model.exceptions.InvalidAmountException;
import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Random;


public abstract class Account implements Writable {
    protected BigDecimal balance;
    protected BigDecimal annualFee;
    protected BigDecimal transactionFee;
    protected int accountNumber;
    protected double interest;
    Random rnd = new Random();

    Account(BigDecimal amount) {
        this.balance = amount.setScale(2, RoundingMode.DOWN);;
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
            balance = balance.add(amount).setScale(2, RoundingMode.DOWN);
        }
    }

    public abstract void withdraw(BigDecimal amount) throws InvalidAmountException;

    public abstract String getAccountInfo();


    //Getters
    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.DOWN);
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

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Compare two accounts by matching up account numbers
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }
}
