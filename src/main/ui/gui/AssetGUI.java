package ui.gui;

import model.assets.Asset;
import model.assets.Bond;
import model.assets.GIC;
import model.assets.Stock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// This class presents a detailed view of any asset, based on its type
public class AssetGUI extends JFrame {

    private Asset asset;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 300;
    private FontBook fb = new FontBook();
    private JPanel detailsPanel = new JPanel();
    private JLabel secured;


    public AssetGUI(Asset a) {
        super("Asset: #" + a.getAccountNumber());
        this.asset = a;
        openAccountPage();
        detailsPanel.setLayout(new GridLayout(0,1));
        secured.setFont(fb.getHeaderOne());
    }

    //MODIFIES: this
    //EFFECTS: sets window size and characteristics
    private void openAccountPage() {
        setLayout(new BorderLayout(15, 15));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setVisible(true);

        printAssetInfo();

    }

    //MODIFIES: this
    //EFFECTS: generates asset info based on class type
    private void printAssetInfo() {
        JPanel assetInfo = new JPanel();
        assetInfo.setLayout(new GridLayout(0, 1, 15, 2));
        assetInfo.setSize(new Dimension(0, 0));
        assetInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        Bond emptyBond = new Bond(0.0, 0, "Boeing", 3, true);
        Stock emptyStock = new Stock("BA", 1, 1, 1, true);
        GIC emptyGIC = new GIC(30, false, 8, 5);

        if (asset.getClass() == emptyBond.getClass()) {
            Bond tempBond = (Bond) asset;
            assetInfo.add(printBondInfo(tempBond));
        } else if (asset.getClass() == emptyStock.getClass()) {
            Stock tempStock = (Stock) asset;
            assetInfo.add(printStockInfo(tempStock));
        } else if (asset.getClass() == emptyGIC.getClass()) {
            GIC tempGIC = (GIC) asset;
            assetInfo.add(printGicInfo(tempGIC));
        }

        this.add(assetInfo);
    }

    //EFFECTS: returns detailed info on a Bond
    private JPanel printBondInfo(Bond b) {

        JLabel titleLabel = new JLabel("Bond - Asset #" + b.getAccountNumber());
        JLabel bondValue = new JLabel("Value: $" + b.getValue());
        JLabel bondIssuer = new JLabel("Issuer: " + b.getIssuer());
        JLabel bondYOM = new JLabel("Year of Maturity: " + b.getYearOfMaturity());
        JLabel bondYield = new JLabel("Yield: " + b.getInterest() + "%");
        if (b.isSecured()) {
            secured = new JLabel("Secured Asset");
        } else {
            secured = new JLabel("Unsecured Asset");
        }
        titleLabel.setFont(fb.getSubFont());
        bondValue.setFont(fb.getHeaderOne());
        bondIssuer.setFont(fb.getHeaderOne());
        bondYOM.setFont(fb.getHeaderOne());
        bondYield.setFont(fb.getHeaderOne());
        secured.setFont(fb.getHeaderOne());

        detailsPanel.add(titleLabel);
        detailsPanel.add(bondValue);
        detailsPanel.add(bondIssuer);
        detailsPanel.add(bondYOM);
        detailsPanel.add(bondYield);

        return detailsPanel;

    }

    //EFFECTS: returns detailed info on a Stock
    private JPanel printStockInfo(Stock s) {
        JLabel titleLabel = new JLabel("Stock - " + s.getTicker() + " - Asset #" + s.getAccountNumber());
        JLabel stockValue = new JLabel("Value: " + s.getValue());
        JLabel stockShares = new JLabel("Number of Shares: " + s.getShares());
        JLabel stockYield = new JLabel("Expected annual return: " + s.getInterest() + "%");
        if (s.isVotingStock()) {
            secured = new JLabel("Voting Stock");
        } else {
            secured = new JLabel("Preferred Stock");
        }

        titleLabel.setFont(fb.getSubFont());
        stockValue.setFont(fb.getHeaderOne());
        stockShares.setFont(fb.getHeaderOne());
        stockYield.setFont(fb.getHeaderOne());


        detailsPanel.add(titleLabel);
        detailsPanel.add(stockValue);
        detailsPanel.add(stockShares);
        detailsPanel.add(stockYield);

        return detailsPanel;

    }

    //EFFECTS: returns detailed info on a GIC
    private JPanel printGicInfo(GIC g) {
        JLabel titleLabel = new JLabel("Guaranteed Investment Certificate - Asset #" + g.getAccountNumber());
        JLabel gicValue = new JLabel("Value: $" + g.getValue());
        JLabel gicTerm = new JLabel("Term: " + g.getTerm() + " years");
        JLabel gicYield = new JLabel("Rate: " + g.getInterest() + "%");
        if (g.isRedeemable()) {
            secured = new JLabel("Redeemable GIC");
        } else {
            secured = new JLabel("Non-redeemable GIC");
        }

        titleLabel.setFont(fb.getSubFont());
        gicValue.setFont(fb.getHeaderOne());
        gicTerm.setFont(fb.getHeaderOne());
        gicYield.setFont(fb.getHeaderOne());
        secured.setFont(fb.getHeaderOne());

        detailsPanel.add(titleLabel);
        detailsPanel.add(gicValue);
        detailsPanel.add(gicTerm);
        detailsPanel.add(gicYield);

        return detailsPanel;

    }
}
