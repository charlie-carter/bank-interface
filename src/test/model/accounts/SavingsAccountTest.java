package model.accounts;

import model.exceptions.InvalidAmountException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
            assertEquals(BigDecimal.valueOf(2150.00).setScale(2, RoundingMode.DOWN), testAccount.getBalance());
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
            assertEquals(BigDecimal.valueOf(1499.50).setScale(2, RoundingMode.DOWN), testAccount.getBalance());
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

    @Test
    void testAddInterest() {
        assertEquals(BigDecimal.valueOf(2000.00).setScale(2, RoundingMode.DOWN), testAccount.getBalance());
        testAccount.addInterest();
        assertEquals(BigDecimal.valueOf(2050.00).setScale(2, RoundingMode.DOWN), testAccount.getBalance());
    }

    @Test
    void testGetAccountInfo() {
        assertEquals("Savings - $2000.00", testAccount.getAccountInfo());
    }

    @Test
    void testToJson() {
        JSONObject testJson = testAccount.toJson();
        double balance = testJson.getDouble("balance");
        int accountNum = testJson.getInt("acctnum");

        SavingsAccount accountFromJson = new SavingsAccount(balance);
        accountFromJson.setAccountNumber(accountNum);

        assertEquals(testAccount.getBalance(), accountFromJson.getBalance());
        assertEquals(testAccount.getAccountNumber(), accountFromJson.getAccountNumber());
    }
}
