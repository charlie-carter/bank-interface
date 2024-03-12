package model.assets;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Stock extends Asset {
    private final String ticker;
    private int shares;
    private final boolean votingStock;
    Random rnd = new Random();

    public Stock(String ticker, int shares, double sharePrice, double expectedReturn, boolean votingStock) {
        this.ticker = ticker;
        this.shares = shares;
        this.value = BigDecimal.valueOf(sharePrice * shares);
        this.interest = expectedReturn;
        this.votingStock = votingStock;
        this.accountNumber = 1000000 + rnd.nextInt(9000000);
    }

    //Getters
    public String getTicker() {
        return ticker;
    }

    public int getShares() {
        return shares;
    }

    public boolean isVotingStock() {
        return votingStock;
    }


    //Returns long-form stock information.
    @Override
    public String getAssetInfo() {
        String securedString;
        String stockString = "Stock: " + this.ticker + "\n";
        String valueString = "Value: " + this.value + "$\n";
        String sharesString = "No. of Shares: " + this.shares + "\n";
        String yieldString = "Expected annual return: " + this.interest + "%\n";
        if (votingStock) {
            securedString = "Voting stock\n";
        } else {
            securedString = "Preferred stock\n";
        }
        return stockString + valueString + sharesString + yieldString + securedString + "q to quit";
    }

    //Returns short-from stock information.
    @Override
    public String getAsset() {
        return "Stock - " + ticker + " - Asset No: " + accountNumber;
    }

    //EFFECTS: returns a JSONObject with the same parameters as this Stock
    @Override
    public JSONObject toJson() {
        BigDecimal writeValue = value.divide(BigDecimal.valueOf(shares), 2, RoundingMode.DOWN);
        JSONObject json = new JSONObject();
        json.put("type", "Stock");
        json.put("shareprice", writeValue.doubleValue());
        json.put("ticker", ticker);
        json.put("return", interest);
        json.put("shares", shares);
        json.put("secured", votingStock);
        return json;
    }
}
