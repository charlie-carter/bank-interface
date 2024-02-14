package model.assets;

public class Asset {

    protected double value;
    protected double interest;

    public double getInterest() {
        return interest;
    }

    public double getValue() {
        return value;
    }

    private void addInterest() {
        value += value * interest / 100;
    }
}
