package model.accounts;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private final double interest; //annual percent earned

    public SavingsAccount(double amount) {
        super(BigDecimal.valueOf(amount));
        super.transactionFee = new BigDecimal("0.50");
        super.annualFee = new BigDecimal("5.00");
        this.interest = 2.50;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        super.balance = super.balance.subtract(amount.add(transactionFee));

    }

    private void addInterest() {
        BigDecimal interestMultiplier = BigDecimal.valueOf((interest / 100) + 1);
        super.balance = super.balance.multiply(interestMultiplier);

    }
}
