package model.assets;

public class Bond extends Asset {


    private int yearOfMaturity;
    private String issuer;

    private boolean secured;

    public Bond(double val, int yom, String issuer, double interest, boolean sec) {
        this.value = val;
        this.yearOfMaturity = yom;
        this.issuer = issuer;
        this.interest = interest;
        this.secured = sec;

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

    private void addInterest() {
        value += value * interest / 100;
    }
}
