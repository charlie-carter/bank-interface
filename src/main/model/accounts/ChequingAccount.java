package model.accounts;

import model.Event;
import model.EventLog;
import model.exceptions.InvalidAmountException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

// Extension of Account Abstract class. Specifies behaviour for a chequing account (No transaction fee, no interest)

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
            super.balance = super.balance.subtract(amount).setScale(2, RoundingMode.DOWN);
            EventLog.getInstance().logEvent(new Event("$" + amount.toPlainString()
                    + " withdrawn from account #" + accountNumber));
        }
    }

    //Returns the account type and current balance.
    @Override
    public String getAccountInfo() {
        return "Chequing - $" + balance;
    }




    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Chequing");
        json.put("balance", balance);
        json.put("acctnum", accountNumber);


        return json;
    }



}


// Less interest on balance
// No fee to remove money prematurely