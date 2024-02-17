package model.assets;

import java.util.Random;

public class GIC extends Asset {
    private int term;
    private boolean redeemable;
    Random rnd = new Random();


    public GIC(int term, boolean redeemable, double rate, double value) {
        this.term = term;
        this.redeemable = redeemable;
        this.interest = rate;
        this.value = value;
        this.accountNumber = 100000 + rnd.nextInt(900000);
    }

    public int getTerm() {
        return term;
    }

    public boolean isRedeemable() {
        return redeemable;
    }

    @Override
    public void getAssetInfo() {
        System.out.println("Guaranteed Investment Certificate:");
        System.out.println("Value: " + this.value);
        System.out.println("Term: " + this.term);
        System.out.println("Rate: " + this.interest);
        if (redeemable) {
            System.out.println("Redeemable GIC");
        } else {
            System.out.println("Non-redeemable GIC");
        }
        System.out.println("q to exit");
    }

    @Override
    public String getAsset() {
        return "GIC - Asset No: " + accountNumber;
    }
}
