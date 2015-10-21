package com.p2s.vending;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.p2s.vending.classes.Coin;

import java.util.ArrayList;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class VendingApp extends Application {
    public static ArrayList<Coin> coins;
    @Override
    public void onCreate() {
        super.onCreate();
        populateCoins();
    }

    private void populateCoins() {
        coins = new ArrayList<>();
        coins.add(new Coin("CENT", 1));
        coins.add(new Coin("NICKEL",5));
        coins.add(new Coin("DIME",10));
        coins.add(new Coin("QUARTER",25));
        coins.add(new Coin("DOLLAR",100));
        coins.add(new Coin("FIVER",500));
        coins.add(new Coin("TENNER",1000));
        coins.add(new Coin("TWENTY",2000));
    }
}
