package model.assets;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BondTest {

    private Bond testBond;

    @BeforeEach
    void setup() {
        testBond = new Bond(500.75, 2040, "Boeing", 4.8, true);
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

    }


}
