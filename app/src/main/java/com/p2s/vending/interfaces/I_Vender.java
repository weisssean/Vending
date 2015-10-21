package com.p2s.vending.interfaces;

import com.p2s.vending.classes.Coin;
import com.p2s.vending.classes.VendingProduct;

import java.util.ArrayList;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public interface I_Vender {
    void addProduct(String name, int price, int stock);

    void setBalance(int balance);

    int getBalance();

    void sellProduct(VendingProduct product);
}
