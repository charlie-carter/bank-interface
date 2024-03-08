package model.assets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Bond extends Asset {


    private int yearOfMaturity;
    private String issuer;

    private boolean secured;
    Random rnd = new Random();

    public Bond(double val, int yom, String issuer, double interest, boolean sec) {
        this.value = BigDecimal.valueOf(val);
        this.yearOfMaturity = yom;
        this.issuer = issuer;
        this.interest = interest;
        this.secured = sec;
        this.accountNumber = 1000000 + rnd.nextInt(9000000);

    }



    public int getYearOfMaturity() {
        return yearOfMaturity;
    }

    public String getIssuer() {
        return issuer;
    }


    public boolean isSecured() {
        return secured;
    }

    //private void addInterest() {
      //  value += value * interest / 100;
    //}



    @Override
    public String getAsset() {
        return "Bond - Asset No: " + accountNumber;
    }

    @Override
    public void getAssetInfo() {
        System.out.println("Bond: ");
        System.out.println("Value: " + this.value);
        System.out.println("Issuer: " + this.issuer);
        System.out.println("Year of Maturity: " + this.yearOfMaturity);
        System.out.println("Yield: " + this.interest);
        if (secured) {
            System.out.println("Secured asset");
        } else {
            System.out.println("Unsecured asset");
        }
        System.out.println("q to exit");

    }
}
