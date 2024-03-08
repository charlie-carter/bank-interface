package model;

import model.accounts.Account;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;
import model.assets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientTest {
    private Client testClient;
    private SavingsAccount testAccount1;
    private ChequingAccount testAccount2;
    private SavingsAccount testAccount3;
    private Bond testBond;
    private GIC testGIC;
    private Stock testStock;


    ArrayList<Account> testAccounts;
    ArrayList<Asset> testAssets;

    @BeforeEach
    void setup() {
        testClient = new Client("Jake Lawrence", "password123");
        testBond = new Bond(500.75, 2040, "Boeing", 4.8, true);
        testStock = new Stock("BA",28, 203.23, -8.2, false);
        testGIC = new GIC(30, false, 8.6, 5000);
        testAccount1 = new SavingsAccount(2000.00);
        testAccount2 = new ChequingAccount(2000.00);
        testAccount3 = new SavingsAccount(2000.00);


        testAccounts = new ArrayList<Account>();
        testAssets = new ArrayList<Asset>();

    }

    @Test
    void testAddAccount() {
        assertEquals(testAccounts, testClient.getAccounts());
        testClient.addAccount(testAccount1);
        testAccounts.add(testAccount1);
        assertEquals(testAccounts, testClient.getAccounts());

    }

    @Test
    void testAddMultipleAccounts() {
        assertEquals(testAccounts, testClient.getAccounts());
        testClient.addAccount(testAccount1);
        testAccounts.add(testAccount1);
        assertEquals(testAccounts, testClient.getAccounts());
        testClient.addAccount(testAccount2);
        testClient.addAccount(testAccount3);
        testAccounts.add(testAccount2);
        testAccounts.add(testAccount3);
        assertEquals(testAccounts, testClient.getAccounts());
    }
    @Test
    void testAddAsset() {
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testBond);
        testAssets.add(testBond);
        assertEquals(testAssets, testClient.getAssets());

    }

    @Test
    void testAddMultipleAssets() {
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testBond);
        testAssets.add(testBond);
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testStock);
        testClient.addAsset(testGIC);
        testAssets.add(testStock);
        testAssets.add(testGIC);
        assertEquals(testAssets, testClient.getAssets());
    }
}