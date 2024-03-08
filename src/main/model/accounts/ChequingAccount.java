package model.accounts;

import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ChequingAccount extends Account {
    public ChequingAccount(double amount) {
        super(BigDecimal.valueOf(amount));
        super.transactionFee = new BigDecimal(BigInteger.ZERO);
        super.annualFee = new BigDecimal("10.00");
        super.interest = 0.00;

    }

    //MODIFIES: this
    //EFFECTS: subtracts the chosen amount from this account. Throws InvalidAmountException if the amount wished to be
    //          withdrawn is greater than the current account balance.
    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(balance) == 1) {
            throw new InvalidAmountException();
        } else {
            super.balance = super.balance.subtract(amount).setScale(2, RoundingMode.DOWN);;
        }
    }

    //Returns the account type and current balance.
    @Override
    public String getAccountInfo() {
        return "Chequing - $" + balance;
    }
}


// Less interest on balance
// No fee to remove money prematurely