package com.p2s.vending.interfaces;

import com.p2s.vending.classes.VendingProduct;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public interface I_Vendor {
    void addProduct(String name, int price, int stock);

    void setBalance(int balance);

    int getBalance();

    void sellProduct(VendingProduct product);

    void notifyLowStock(VendingProduct product);

    void notifyReStock(VendingProduct product);

    void addInventory(VendingProduct product, int stock);
}
