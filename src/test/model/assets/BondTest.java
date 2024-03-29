package model.assets;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BondTest {

    private Bond testBond;
    private Bond testBond2;
    private Bond testBond3;
    private Stock testStock;

    @BeforeEach
    void setup() {
        testBond = new Bond(500.75, 2040, "Boeing", 4.8, true);
        testBond2 = new Bond(500.75, 2040, "Boeing", 4.8, false);
        testBond3 = new Bond(500.60, 2040, "Boeing", 4.8, false);
        testStock = new Stock("BA", 2, 200, 6, false);


    }

    @Test
    void testConstructor() {
        assertEquals(BigDecimal.valueOf(500.75), testBond.getValue());
        assertEquals(4.8, testBond.getInterest());
        assertEquals(2040, testBond.getYearOfMaturity());
        assertEquals("Boeing", testBond.getIssuer());
        assertTrue(testBond.isSecured());
    }

    @Test
    void testAddInterest() {
        assertEquals(BigDecimal.valueOf(500.75), testBond.getValue());
        testBond.addInterest();
        assertEquals(BigDecimal.valueOf(524.78), testBond.getValue());
    }

    @Test
    void testGetAsset() {
        assertEquals("Bond - Asset No: " + testBond.getAccountNumber(), testBond.getAsset());
    }

    @Test
    void testGetAssetInfo() {
        String expected = "Bond: \nValue: 500.75\nIssuer: Boeing\nYear of Maturity: 2040\nYield: 4.8%\nSecured asset\nq to quit";
        assertEquals(expected, testBond.getAssetInfo());
        expected = "Bond: \nValue: 500.75\nIssuer: Boeing\nYear of Maturity: 2040\nYield: 4.8%\nUnsecured asset\nq to quit";
        assertEquals(expected, testBond2.getAssetInfo());

    }

    @Test
    void testToJson() {
        JSONObject testJson = testBond.toJson();

        double value = testJson.getDouble("value");
        int yom = testJson.getInt("yom");
        double yield = testJson.getDouble("yield");
        String issuer = testJson.getString("issuer");
        boolean secured = testJson.getBoolean("secured");

        Bond bondFromRead = new Bond(value, yom, issuer, yield, secured);

        assertEquals(testBond.getValue(), bondFromRead.getValue());
        assertEquals(testBond.getYearOfMaturity(), bondFromRead.getYearOfMaturity());
        assertEquals(testBond.getInterest(), bondFromRead.getInterest());
        assertEquals(testBond.getIssuer(), bondFromRead.getIssuer());
        assertEquals(testBond.isSecured(), bondFromRead.isSecured());
    }

    @Test
    void testEquals() {
        assertTrue(testBond.equals(testBond));
        assertFalse(testBond.equals(testBond3));
        assertFalse(testBond.equals(testStock));
    }


}
