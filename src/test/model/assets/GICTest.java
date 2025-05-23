package model.assets;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class GICTest {

    private GIC testGIC;
    private GIC testGIC2;

    @BeforeEach
    void setup() {
        testGIC = new GIC(30, false, 8.6, 5000);
        testGIC2 = new GIC(30, true, 8.6, 5000);
    }

    @Test
    void testConstructor() {
        assertEquals(BigDecimal.valueOf(5000.00).setScale(2, RoundingMode.DOWN), testGIC.getValue());
        assertEquals(8.6, testGIC.getInterest());
        assertEquals(30, testGIC.getTerm());
        assertFalse(testGIC.isRedeemable());
    }

    @Test
    void testAddInterest() {
        assertEquals(BigDecimal.valueOf(5000.00).setScale(2, RoundingMode.DOWN), testGIC.getValue());
        testGIC.addInterest();
        assertEquals(BigDecimal.valueOf(5430.00).setScale(2, RoundingMode.DOWN), testGIC.getValue());
    }

    @Test
    void testGetAsset() {
        assertEquals("GIC - Asset No: " + testGIC.getAccountNumber(), testGIC.getAsset());
    }

    @Test
    void testGetAssetInfo() {
        String securedString;
        String gicString = "Guaranteed Investment Certificate: \n";
        String valueString = "Value: 5000.0$\n";
        String termString = "Term: 30 years\n";
        String rateString = "Rate: 8.6%\n";
        securedString = "Non-redeemable GIC \n";

        String expected = gicString + valueString + termString + rateString + securedString + "q to quit";
        assertEquals(expected, testGIC.getAssetInfo());
        securedString = "Redeemable GIC \n";
        expected = gicString + valueString + termString + rateString + securedString + "q to quit";

        assertEquals(expected, testGIC2.getAssetInfo());
    }

    @Test
    void testToJson() {
        JSONObject testJson = testGIC.toJson();

        double value = testJson.getDouble("value");
        int term = testJson.getInt("term");
        double interest = testJson.getDouble("rate");
        boolean secured = testJson.getBoolean("redeemable");

        GIC gicFromRead = new GIC(term, secured, interest, value);

        assertEquals(testGIC.getValue(), gicFromRead.getValue());
        assertEquals(testGIC.getTerm(), gicFromRead.getTerm());
        assertEquals(testGIC.getInterest(), gicFromRead.getInterest());
        assertEquals(testGIC.isRedeemable(), gicFromRead.isRedeemable());

    }
}
