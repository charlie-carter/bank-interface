package gui;

import model.assets.Asset;

import javax.swing.*;

public class AssetGUI extends JFrame {

    private Asset asset;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 300;
    private ClientGUI superPage;
    private String pageTitle;

    private FontBook fb = new FontBook();

    public AssetGUI(Asset a, ClientGUI c) {
        super("Asset: #" + a.getAccountNumber());
        this.asset = a;
        openAccountPage();
        superPage = c;


    }

    private void openAccountPage() {
    }
}
