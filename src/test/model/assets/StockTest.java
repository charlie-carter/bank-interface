package model.assets;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    private Stock testStock;
    private Stock testStock2;

    @BeforeEach
    void setup() {
        testStock = new Stock("BA",28, 203.23, -8.2, false);
        testStock2 = new Stock("BA",28, 203.23, -8.2, true);
    }


    @Test
    void testConstructor() {
        assertEquals(BigDecimal.valueOf(5690.44), testStock.getValue());
        assertEquals(-8.2, testStock.getInterest());
        assertEquals("BA", testStock.getTicker());
        assertEquals(28, testStock.getShares());
        assertFalse(testStock.isVotingStock());

    }

    @Test
    void testAddInterest() {
        assertEquals(BigDecimal.valueOf(5690.44), testStock.getValue());
        testStock.addInterest();
        assertEquals(BigDecimal.valueOf(5223.83), testStock.getValue());
    }

    @Test
    void testGetAsset() {
        assertEquals("Stock - BA - Asset No: " + testStock.getAccountNumber(), testStock.getAsset());
    }

    @Test
    void testGetAssetInfo() {
        String securedString;
        String stockString = "Stock: BA\n";
        String valueString = "Value: 5690.44$\n";
        String sharesString = "No. of Shares: 28\n";
        String yieldString = "Expected annual return: -8.2%\n";
        securedString = "Preferred stock\n";

        String expected = stockString + valueString + sharesString + yieldString + securedString + "q to quit";
        assertEquals(expected, testStock.getAssetInfo());

        securedString = "Voting stock\n";
        expected = stockString + valueString + sharesString + yieldString + securedString + "q to quit";
        assertEquals(expected, testStock2.getAssetInfo());
    }

    @Test
    void testToJson() {
        JSONObject testJson = testStock.toJson();
        double value = testJson.getDouble("shareprice");
        int shares = testJson.getInt("shares");
        double interest = testJson.getDouble("return");
        String ticker = testJson.getString("ticker");
        boolean secured = testJson.getBoolean("secured");

        Stock stockFromRead = new Stock(ticker, shares, value, interest, secured);

        assertEquals(testStock.getValue(), stockFromRead.getValue());
        assertEquals(testStock.getShares(), stockFromRead.getShares());
        assertEquals(testStock.getInterest(), stockFromRead.getInterest());
        assertEquals(testStock.getTicker(), stockFromRead.getTicker());
        assertEquals(testStock.isVotingStock(), stockFromRead.isVotingStock());
    }




}
