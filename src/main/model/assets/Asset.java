package model.assets;

import persistence.Writable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public abstract class Asset implements Writable {

    protected BigDecimal value;
    protected double interest;
    protected int accountNumber;

    //MODIFIES: this
    //EFFECTS: Increases the value of the current asset by the specified interest rate
    public void addInterest() {
        value = value.add(BigDecimal.valueOf(value.doubleValue() * interest / 100).setScale(2, RoundingMode.DOWN));
    }

    //Getters:
    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.DOWN);
    }

    public double getInterest() {
        return interest;
    }

    public abstract String getAsset();

    public abstract String getAssetInfo();

    public int getAccountNumber() {
        return accountNumber;
    }

    // Compares two assets
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asset asset = (Asset) o;
        return Double.compare(interest, asset.interest) == 0 && Objects.equals(value, asset.value);
    }
}
