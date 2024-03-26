package model;

import model.accounts.Account;
import model.assets.Asset;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

// Client class, loaded by BankUserInterface, holds information on Client name, password, accounts and assets.

public class Client {
    ArrayList<Account> accounts;
    ArrayList<Asset> assets;

    private String name;
    //private int clientNumber;
    private String password;
    Random rnd = new Random();

    public Client(String name, String password) {
        this.name = name;
        //this.clientNumber = 1000000 + rnd.nextInt(9000000);
        this.password = password;
        this.accounts = new ArrayList<>();
        this.assets = new ArrayList<>();
    }

    //Getters and setters
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //EFFECTS: Convert client to json format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        //json.put("num", clientNumber);
        json.put("pass", password);
        json.put("accounts", accountsToJson());
        json.put("assets", assetsToJson());

        return json;
    }

    //EFFECTS: iterates through list of accounts to convert them to json format
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Account a : accounts) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: iterates through list of assets to convert them to json format
    private JSONArray assetsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Asset a : assets) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
