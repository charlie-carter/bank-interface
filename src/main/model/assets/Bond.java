package model.assets;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Random;

public class Bond extends Asset {
    private final int yearOfMaturity;
    private final String issuer;
    private final boolean secured;
    Random rnd = new Random();

    public Bond(double val, int yom, String issuer, double interest, boolean sec) {
        this.value = BigDecimal.valueOf(val);
        this.yearOfMaturity = yom;
        this.issuer = issuer;
        this.interest = interest;
        this.secured = sec;
        this.accountNumber = 1000000 + rnd.nextInt(9000000);

    }


    //getters:
    public int getYearOfMaturity() {
        return yearOfMaturity;
    }

    public String getIssuer() {
        return issuer;
    }


    public boolean isSecured() {
        return secured;
    }


    //Returns long-form bond information
    @Override
    public String getAssetInfo() {
        String securedString;
        String bondString = "Bond: \n";
        String valueString = "Value: " + this.value + "\n";
        String issuerString = "Issuer: " + this.issuer + "\n";
        String yomString = "Year of Maturity: " + this.yearOfMaturity + "\n";
        String yieldString = "Yield: " + this.interest + "%\n";
        if (secured) {
            securedString = "Secured asset\n";
        } else {
            securedString = "Unsecured asset\n";
        }
        return bondString + valueString + issuerString + yomString + yieldString + securedString + "q to quit";
    }

    //Returns short-form bond information.
    @Override
    public String getAsset() {
        return "Bond - Asset No: " + accountNumber;
    }

    //EFFECTS: returns a JSONObject with the same parameters as this Bond
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Bond");
        json.put("value", value.doubleValue());
        json.put("yom", yearOfMaturity);
        json.put("yield", interest);
        json.put("issuer", issuer);
        json.put("secured", secured);
        return json;
    }
}
