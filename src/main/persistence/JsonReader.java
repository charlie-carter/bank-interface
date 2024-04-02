package persistence;


import model.Client;
import model.Event;
import model.EventLog;
import model.accounts.ChequingAccount;
import model.accounts.SavingsAccount;
import model.assets.Bond;
import model.assets.GIC;
import model.assets.Stock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// Some methods taken from phase 2 example file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Client read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Data Read From File"));
        return parseClient(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Client parseClient(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("pass");
        Client client = new Client(name, password);
        addAccounts(client, jsonObject);
        addAssets(client, jsonObject);
        return client;
    }

    // MODIFIES: client
    // EFFECTS: parses accounts from JSON object and adds them to client
    private void addAccounts(Client c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(c, nextAccount);
        }
    }

    // MODIFIES: client
    // EFFECTS: parses assets from JSON object and adds them to client
    private void addAssets(Client c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("assets");
        for (Object json : jsonArray) {
            JSONObject nextAsset = (JSONObject) json;
            addAsset(c, nextAsset);
        }
    }


    // EFFECTS: chooses different function based on type of asset to add
    private void addAsset(Client c, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        if (type.equals("Bond")) {
            addBond(c, jsonObject);
        } else if (type.equals("Stock")) {
            addStock(c, jsonObject);
        } else {
            addGIC(c, jsonObject);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses bond from JSON object and adds it to client's assets
    private void addBond(Client c, JSONObject jsonObject) {
        double value = jsonObject.getDouble("value");
        int yom = jsonObject.getInt("yom");
        double yield = jsonObject.getDouble("yield");
        String issuer = jsonObject.getString("issuer");
        boolean secured = jsonObject.getBoolean("secured");

        Bond bond = new Bond(value, yom, issuer, yield, secured);
        c.addAsset(bond);
    }

    // MODIFIES: c
    // EFFECTS: parses stock from JSON object and adds it to client's assets
    private void addStock(Client c, JSONObject jsonObject) {
        double value = jsonObject.getDouble("shareprice");
        int shares = jsonObject.getInt("shares");
        double interest = jsonObject.getDouble("return");
        String ticker = jsonObject.getString("ticker");
        boolean secured = jsonObject.getBoolean("secured");

        Stock stock = new Stock(ticker, shares, value, interest, secured);
        c.addAsset(stock);
    }

    // MODIFIES: c
    // EFFECTS: parses gic from JSON object and adds it to client's assets
    private void addGIC(Client c, JSONObject jsonObject) {
        double value = jsonObject.getDouble("value");
        int term = jsonObject.getInt("term");
        double interest = jsonObject.getDouble("rate");
        boolean secured = jsonObject.getBoolean("redeemable");

        GIC gic = new GIC(term, secured, interest, value);
        c.addAsset(gic);
    }



    // MODIFIES: c
    // EFFECTS: parses account from JSON object and adds it to client's accounts
    private void addAccount(Client c, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        double balance = jsonObject.getDouble("balance");
        int accountNum = jsonObject.getInt("acctnum");

        if (type.equals("Savings")) {
            SavingsAccount account = new SavingsAccount(balance);
            account.setAccountNumber(accountNum);
            c.addAccount(account);
        } else {
            ChequingAccount account = new ChequingAccount(balance);
            account.setAccountNumber(accountNum);
            c.addAccount(account);
        }
    }
}
