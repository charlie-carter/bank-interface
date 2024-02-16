package model.assets;

public abstract class Asset {

    protected double value;
    protected double interest;
    protected int accountNumber;

    public double getInterest() {
        return interest;
    }

    public double getValue() {
        return value;
    }

    private void addInterest() {
        value += value * interest / 100;
    }

    public abstract String getAsset();

    public abstract void getAssetInfo();
}
