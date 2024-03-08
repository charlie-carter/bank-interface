package model.assets;

import java.math.BigDecimal;
import java.math.RoundingMode;


public abstract class Asset {

    protected BigDecimal value;
    protected double interest;
    protected int accountNumber;

    public double getInterest() {
        return interest;
    }

    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.DOWN);
    }

    public void addInterest() {
        value = value.add(BigDecimal.valueOf(value.doubleValue() * interest / 100).setScale(2, RoundingMode.DOWN));
    }

    public abstract String getAsset();

    public abstract void getAssetInfo();

    public int getAccountNumber() {
        return accountNumber;
    }
}
