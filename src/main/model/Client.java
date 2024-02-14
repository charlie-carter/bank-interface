package model;

import model.accounts.Account;
import model.assets.Asset;

import java.util.ArrayList;

public class Client {
    ArrayList<Account> accounts;
    ArrayList<Asset> assets;

    private String name;
    private int accountNumber;
    private String password;

    public Client(String name, int accountNumber, String password) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
