package model.assets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Stock extends Asset {
    private String ticker;
    private int shares;

    private boolean votingStock;
    Random rnd = new Random();

    public Stock(String ticker, int shares, double sharePrice, double expectedReturn, boolean votingStock) {
        this.ticker = ticker;
        this.shares = shares;
        this.value = BigDecimal.valueOf(sharePrice * shares);
        this.interest = expectedReturn;
        this.votingStock = votingStock;
        this.accountNumber = 1000000 + rnd.nextInt(9000000);
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
        System.out.println("q to exit");
    }

    @Override
    public String getAsset() {
        return "Stock - " + ticker + " - Asset No: " + accountNumber;
    }
}
