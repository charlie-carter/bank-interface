package model.assets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    private Stock testStock;

    @BeforeEach
    void setup() {
        testStock = new Stock("BA",28, 203.23, -8.2, false);
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


}
