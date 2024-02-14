package model.assets;

public class GIC extends Asset {
    private int term;
    private boolean redeemable;


    public GIC(int term, boolean redeemable, double rate, double value) {
        this.term = term;
        this.redeemable = redeemable;
        this.interest = rate;
        this.value = value;
    }

    public int getTerm() {
        return term;
    }

    public boolean isRedeemable() {
        return redeemable;
    }

}
