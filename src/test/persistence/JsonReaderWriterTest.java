package persistence;

import model.Client;
import model.accounts.*;
import model.assets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderWriterTest {
    private static final String JSON_STORE = "./data/testClient.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Client testClient;
    private SavingsAccount savAccount;
    private ChequingAccount chqAccount;
    private Stock testStock;
    private Bond testBond;
    private GIC testGIC1;
    private GIC testGIC2;

    @BeforeEach
    void setup() {
        testClient = new Client("David Soloman", "password");
        savAccount = new SavingsAccount(4232.95);
        chqAccount = new ChequingAccount(234.21);
        testStock = new Stock("BA", 30, 210.21, -20.7, false);
        testBond = new Bond(50000000, 2050, "Boeing", 12.5, true);
        testGIC1 = new GIC(30, true, 3.5, 10000);
        testGIC2 = new GIC(25, false, 5.8, 10000);

        testClient.addAccount(savAccount, false);
        testClient.addAccount(chqAccount, false);
        testClient.addAsset(testStock, false);
        testClient.addAsset(testBond, false);
        testClient.addAsset(testGIC1, false);
        testClient.addAsset(testGIC2, false);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Test
    void testReadClient() {
        try {
            jsonWriter.open();
            jsonWriter.write(testClient);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            fail("Unable to write to file: " + JSON_STORE);
        }

        try {
            Client clientFromRead = jsonReader.read();
            assertEquals(testClient.getName(), clientFromRead.getName());
            assertEquals(testClient.getPassword(), clientFromRead.getPassword());
            for (Account a : clientFromRead.getAccounts()) {
                int index = clientFromRead.getAccounts().indexOf(a);
                assertEquals(a, testClient.getAccounts().get(index));
            }
            for (Asset a : clientFromRead.getAssets()) {
                int index = clientFromRead.getAssets().indexOf(a);
                assertEquals(a, testClient.getAssets().get(index));
            }
        } catch (IOException e) {
            fail("Unable to find file: " + JSON_STORE);
        }
    }
}
