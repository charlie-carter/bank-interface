package model.accounts;

import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public abstract class Account {
    protected BigDecimal balance = new BigDecimal(0.00);
    protected BigDecimal annualFee;
    protected BigDecimal transactionFee;

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

    public BigDecimal getBalance() {
        return balance;
    }

}
