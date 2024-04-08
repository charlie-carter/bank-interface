package model;

import model.accounts.Account;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;
import model.assets.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        testClient.addAccount(testAccount1, true);
        testAccounts.add(testAccount1);
        assertEquals(testAccounts, testClient.getAccounts());

    }

    @Test
    void testAddMultipleAccounts() {
        assertEquals(testAccounts, testClient.getAccounts());
        testClient.addAccount(testAccount1, true);
        testAccounts.add(testAccount1);
        assertEquals(testAccounts, testClient.getAccounts());
        testClient.addAccount(testAccount2, true);
        testClient.addAccount(testAccount3, true);
        testAccounts.add(testAccount2);
        testAccounts.add(testAccount3);
        assertEquals(testAccounts, testClient.getAccounts());
    }
    @Test
    void testAddAsset() {
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testBond, true);
        testAssets.add(testBond);
        assertEquals(testAssets, testClient.getAssets());

    }

    @Test
    void testAddMultipleAssets() {
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testBond, true);
        testAssets.add(testBond);
        assertEquals(testAssets, testClient.getAssets());
        testClient.addAsset(testStock, true);
        testClient.addAsset(testGIC, true);
        testAssets.add(testStock);
        testAssets.add(testGIC);
        assertEquals(testAssets, testClient.getAssets());
    }

    @Test
    void testPassword() {
        assertEquals("password123", testClient.getPassword());
        testClient.setPassword("testpassword");
        assertEquals("testpassword", testClient.getPassword());
    }

    @Test
    void testGetName() {
        assertEquals("Jake Lawrence", testClient.getName());
    }

    @Test
    void testToJson() {
        testClient.addAsset(testStock, true);
        testClient.addAsset(testBond, true);
        testClient.addAccount(testAccount1, true);
        testClient.addAccount(testAccount2, true);

        JSONObject testObject = testClient.toJson();

        String name = testObject.getString("name");
        String password = testObject.getString("pass");
        ArrayList<Account> accounts = addAccounts(testObject);
        ArrayList<Asset> assets = addAssets(testObject);

        Client clientFromJson = new Client(name, password);

        assertEquals("Jake Lawrence", clientFromJson.getName());
        assertEquals("password123", clientFromJson.getPassword());
        for (Account a : accounts) {
            int index = accounts.indexOf(a);
            assertEquals(a, testClient.getAccounts().get(index));
        }
        for (Asset a : assets) {
            int index = assets.indexOf(a);
            assertEquals(a, testClient.getAssets().get(index));
        }
    }

    @Test
    void testEquals() {
        assertTrue(testAccount1.equals(testAccount1));
        assertFalse(testAccount1.equals(testAccount2));
        assertFalse(testAccount1.equals(testAccount3));
        assertFalse(testAccount1.equals(testGIC));
        assertFalse(testStock.equals(testGIC));
    }
//
//    @Test
//    void testAssetsToJson() {
//        testClient.addAsset(testStock);
//        testClient.addAsset(testBond);
//        JSONArray testArray = testClient.assetsToJson();
//    }

    // MODIFIES: client
    // EFFECTS: parses accounts from JSON object and adds them to client
    private ArrayList<Account> addAccounts(JSONObject jsonObject) {
        ArrayList<Account> accountList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            accountList.add(addAccount(nextAccount));
        }
        return accountList;
    }

    // MODIFIES: client
    // EFFECTS: parses assets from JSON object and adds them to client
    private ArrayList<Asset> addAssets(JSONObject jsonObject) {
        ArrayList<Asset> assetList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("assets");
        for (Object json : jsonArray) {
            JSONObject nextAsset = (JSONObject) json;
            assetList.add(addAsset(nextAsset));
        }
        return assetList;
    }

    private Account addAccount(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        double balance = jsonObject.getDouble("balance");
        int accountNum = jsonObject.getInt("acctnum");

        if (type.equals("Savings")) {
            SavingsAccount account = new SavingsAccount(balance);
            account.setAccountNumber(accountNum);
            return account;
        } else {
            ChequingAccount account = new ChequingAccount(balance);
            account.setAccountNumber(accountNum);
            return account;
        }
    }

    // EFFECTS: chooses different function based on type of asset to add
    private Asset addAsset(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        if (type.equals("Bond")) {
            return addBond(jsonObject);
        } else if (type.equals("Stock")) {
            return addStock(jsonObject);
        } else {
            return addGIC(jsonObject);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses bond from JSON object and adds it to client's assets
    private Bond addBond(JSONObject jsonObject) {
        double value = jsonObject.getDouble("value");
        int yom = jsonObject.getInt("yom");
        double yield = jsonObject.getDouble("yield");
        String issuer = jsonObject.getString("issuer");
        boolean secured = jsonObject.getBoolean("secured");

        return new Bond(value, yom, issuer, yield, secured);
    }

    // MODIFIES: c
    // EFFECTS: parses stock from JSON object and adds it to client's assets
    private Stock addStock(JSONObject jsonObject) {
        double value = jsonObject.getDouble("shareprice");
        int shares = jsonObject.getInt("shares");
        double interest = jsonObject.getDouble("return");
        String ticker = jsonObject.getString("ticker");
        boolean secured = jsonObject.getBoolean("secured");

        return new Stock(ticker, shares, value, interest, secured);

    }

    // MODIFIES: c
    // EFFECTS: parses gic from JSON object and adds it to client's assets
    private GIC addGIC(JSONObject jsonObject) {
        double value = jsonObject.getDouble("value");
        int term = jsonObject.getInt("term");
        double interest = jsonObject.getDouble("rate");
        boolean secured = jsonObject.getBoolean("redeemable");

        return new GIC(term, secured, interest, value);

    }
}