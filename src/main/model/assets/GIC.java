package model.assets;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Random;

public class GIC extends Asset {
    private final int term;
    private final boolean redeemable;
    Random rnd = new Random();


    public GIC(int term, boolean redeemable, double rate, double value) {
        this.term = term;
        this.redeemable = redeemable;
        this.interest = rate;
        this.value = BigDecimal.valueOf(value);
        this.accountNumber = 1000000 + rnd.nextInt(9000000);
    }

    //Getters
    public int getTerm() {
        return term;
    }

    public boolean isRedeemable() {
        return redeemable;
    }

    //Returns long-form GIC information.
    @Override
    public String getAssetInfo() {
        String securedString;
        String gicString = "Guaranteed Investment Certificate: \n";
        String valueString = "Value: " + this.value + "$\n";
        String termString = "Term: " + this.term + " years\n";
        String rateString = "Rate: " + this.interest + "%\n";
        if (redeemable) {
            securedString = "Redeemable GIC \n";
        } else {
            securedString = "Non-redeemable GIC \n";
        }

        return gicString + valueString + termString + rateString + securedString + "q to quit";
    }

    //Returns short-form GIC information.
    @Override
    public String getAsset() {
        return "GIC - Asset No: " + accountNumber;
    }

    //EFFECTS: returns a JSONObject with the same parameters as this GIC
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "GIC");
        json.put("value", value.doubleValue());
        json.put("term", term);
        json.put("rate", interest);
        json.put("redeemable", redeemable);
        return json;
    }
}
