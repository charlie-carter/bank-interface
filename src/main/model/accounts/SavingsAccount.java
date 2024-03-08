package model.accounts;

import model.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(double amount) {
        super(BigDecimal.valueOf(amount));
        super.transactionFee = new BigDecimal("0.50");
        super.annualFee = new BigDecimal("5.00");
        super.interest = 2.50; //annual percent earned
    }

    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException {
        super.balance = super.balance.subtract(amount.add(transactionFee));
        BigDecimal withdrawalLimit = new BigDecimal(String.valueOf(super.balance.subtract(transactionFee)));
        if (amount.compareTo(withdrawalLimit) == 1) {
            throw new InvalidAmountException();
        } else {
            super.balance = super.balance.subtract(amount.add(transactionFee));
        }
    }

    private void addInterest() {
        BigDecimal interestMultiplier = BigDecimal.valueOf((interest / 100) + 1);
        super.balance = super.balance.multiply(interestMultiplier);

    }

    @Override
    public String getAccountInfo() {
        return "Savings - $" + balance;
    }


}
