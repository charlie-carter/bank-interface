package model.assets;

public class Stock extends Asset {
    private String ticker;
    private int shares;

    private boolean votingStock;

    public Stock(String ticker, int shares, double value, double expectedReturn, boolean votingStock) {
        this.ticker = ticker;
        this.shares = shares;
        this.value = value;
        this.interest = expectedReturn;
        this.votingStock = votingStock;
    }

    public String getTicker() {
        return ticker;
    }

    public int getShares() {
        return shares;
    }

    public boolean isVotingStock() {
        return votingStock;
    }


    @Override
    public void getAssetInfo() {
        System.out.println("Stock:" + ticker);
        System.out.println("Value: " + value);
        System.out.println("No. of Shares: " + shares);
        System.out.println("Expected annual return: " + interest);
        if (votingStock) {
            System.out.println("Voting Stock");
        } else {
            System.out.println("Preferred Stock");
        }
    }

    @Override
    public String getAsset() {
        return "Stock - " + ticker + " - Asset No: " + accountNumber;
    }
}
