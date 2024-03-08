package model.accounts;

import model.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SavingsAccountTest {
    private SavingsAccount testAccount;

    @BeforeEach
    void setup() {
        testAccount = new SavingsAccount(2000.00);
    }

    @Test
    void testDeposit() {
        try {
            testAccount.deposit(new BigDecimal("150.00"));
            assertEquals(2150.00, testAccount.getBalance());
        } catch (InvalidAmountException e) {
            fail("Exception thrown");
        }
    }

    @Test
    void testDepositInvalidAmount() {
        try {
            testAccount.deposit(new BigDecimal("-150.00"));
            fail("No exception thrown");
        } catch (InvalidAmountException e) {
            // All good
        }
    }

    @Test
    void testWithdrawal() {
        try {
            testAccount.withdraw(new BigDecimal("500.00"));
            assertEquals(1500.00, testAccount.getBalance());
        } catch (InvalidAmountException e) {
            fail("Exception thrown");
        }
    }

    @Test
    void testWithdrawalInvalidAmount() {
        try {
            testAccount.withdraw(new BigDecimal("2000.00"));
            fail("Exception not thrown");
        } catch (InvalidAmountException e) {
            // All good
        }

        try {
            testAccount.withdraw(new BigDecimal("2500.00"));
            fail("Exception not thrown");
        } catch (InvalidAmountException e) {
            // All good
        }
    }
}
