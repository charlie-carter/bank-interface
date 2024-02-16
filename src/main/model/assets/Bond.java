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

    @Override
    public String getAsset() {
        return "Bond - Asset No: " + accountNumber;
    }

    @Override
    public void getAssetInfo() {
        System.out.println("Bond:");
        System.out.println("Value:" + this.value);
        System.out.println("Issuer:" + this.issuer);
        System.out.println("Year of Maturity:" + this.yearOfMaturity);
        System.out.println("Interest: " + this.interest);
        if (secured) {
            System.out.println("Secured asset");
        } else {
            System.out.println("Unsecured asset");
        }


    }
}
