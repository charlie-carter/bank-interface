package model.accounts;

import model.exceptions.InvalidAmountException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Extension of Account Abstract class. Specifies behaviour for a savings account:
// (Small transation fee, but has annual compound interest)

public class SavingsAccount extends Account {

    public SavingsAccount(double amount) {
        super(BigDecimal.valueOf(amount));
        super.transactionFee = new BigDecimal("0.50");
        super.annualFee = new BigDecimal("5.00");
        super.interest = 2.50; //annual percent earned
    }

    //MODIFIES: this
    //EFFECTS: subtracts the chosen amount and transaction fee from this account.
    // Throws InvalidAmountException if the amount wished to be withdrawn + the transaction fee
    // is greater than the current account balance.
    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException {

        BigDecimal withdrawalLimit = new BigDecimal(String.valueOf(super.balance.subtract(transactionFee)));
        if (amount.compareTo(withdrawalLimit) == 1) {
            throw new InvalidAmountException();
        } else {
            super.balance = super.balance.subtract(amount.add(transactionFee)).setScale(2, RoundingMode.DOWN);;
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds the account's interest rate to the balance when called
    public void addInterest() {
        BigDecimal interestMultiplier = BigDecimal.valueOf((interest / 100) + 1);
        super.balance = super.balance.multiply(interestMultiplier).setScale(2, RoundingMode.DOWN);;

    }

    //Returns the account type and balance
    @Override
    public String getAccountInfo() {
        return "Savings - $" + balance;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Savings");
        json.put("balance", balance);
        json.put("acctnum", accountNumber);

        return json;
    }

}
