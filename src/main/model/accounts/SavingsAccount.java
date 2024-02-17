package model.accounts;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(double amount) {
        super(BigDecimal.valueOf(amount));
        super.transactionFee = new BigDecimal("0.50");
        super.annualFee = new BigDecimal("5.00");
        super.interest = 2.50; //annual percent earned
    }

    @Override
    public void withdraw(BigDecimal amount) {
        super.balance = super.balance.subtract(amount.add(transactionFee));

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
