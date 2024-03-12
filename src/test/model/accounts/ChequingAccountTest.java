package model.accounts;

import model.exceptions.InvalidAmountException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChequingAccountTest extends AccountTest {
    private ChequingAccount testAccount;

    @BeforeEach
    void setup() {
        testAccount = new ChequingAccount(2000.00);
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
            assertEquals(BigDecimal.valueOf(1500.00).setScale(2, RoundingMode.DOWN), testAccount.getBalance());
        } catch (InvalidAmountException e) {
            fail("Exception thrown");
        }
    }

    @Test
    void testWithdrawalInvalidAmount() {
        try {
            testAccount.withdraw(new BigDecimal("2001.00"));
            fail("Exception thrown");
        } catch (InvalidAmountException e) {
            // All good
        }
    }

    @Test
    void testGetAccountInfo() {
        assertEquals("Chequing - $2000.00", testAccount.getAccountInfo());
        try {
            testAccount.deposit(BigDecimal.valueOf(0.25));
        } catch (InvalidAmountException e) {
            fail("Exception thrown");
        }
        assertEquals("Chequing - $2000.25", testAccount.getAccountInfo());

    }

    @Test
    void testGetInterest() {
        assertEquals(0.0, testAccount.getInterest());
    }

    @Test
    void testGetAnnualFee() {
        BigDecimal annualFee = new BigDecimal("10.00");
        assertEquals(annualFee, testAccount.getAnnualFee());
    }

    @Test
    void testGetTransactionFee() {
        assertEquals(BigDecimal.ZERO, testAccount.getTransactionFee());
    }

    @Test
    void testAccountNumber() {
        testAccount.setAccountNumber(1234);
        assertEquals(1234, testAccount.getAccountNumber());
    }

    @Test
    void testToJson() {
        JSONObject testJson = testAccount.toJson();
        double balance = testJson.getDouble("balance");
        int accountNum = testJson.getInt("acctnum");

        ChequingAccount accountFromJson = new ChequingAccount(balance);
        accountFromJson.setAccountNumber(accountNum);

        assertEquals(testAccount.getBalance(), accountFromJson.getBalance());
        assertEquals(testAccount.getAccountNumber(), accountFromJson.getAccountNumber());
    }
}
