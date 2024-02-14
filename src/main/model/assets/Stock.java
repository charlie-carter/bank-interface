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


}
