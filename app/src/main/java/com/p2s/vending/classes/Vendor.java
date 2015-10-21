package com.p2s.vending.classes;

import android.util.Log;

import com.p2s.vending.VendingApp;
import com.p2s.vending.interfaces.I_Vendor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class Vendor implements I_Vendor,Serializable{


    private static final int MINSTOCK = 10;
    private int balance = 0;
    public ArrayList<VendingProduct> products = new ArrayList<>();

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void sellProduct(VendingProduct product) {
        for (VendingProduct p:products) {
            if (product.id().equals(p.id())){
                p.sellProduct();
            }

        }
        if (product.stock()>MINSTOCK){
            notifyLowStock(product);
        }
    }
    @Override
    public void notifyLowStock(VendingProduct product) {
        //TODO: impliment notify the supplier

    }
    @Override
    public void notifyReStock(VendingProduct product) {
        //TODO: impliment notify the supplier

    }


    @Override
    public void addProduct(String name, int price, int startingInventory ) {
        products.add(new VendingProduct(name, price,startingInventory));
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void addInventory(VendingProduct product,int inventory){
        for (VendingProduct p:products) {
            if (product.id().equals(p.id())){
                p.addQuantity(inventory);
                notifyReStock(product);
                return;
            }
        }
    }


    public static ArrayList<Coin> getChange(double balance) {
        ArrayList<Coin> change = new ArrayList<>();

        for (int i = VendingApp.coins.size(); i > 0; i--) {
            Coin c = VendingApp.coins.get(i - 1);
            Log.d("Change", "coin= " + c.name());

            if (balance / c.value() > 1) {
                c.quantity = (long) ((double)balance / (double)c.value());
                change.add(c);
                balance -= c.quantity * c.value();

            }
        }
        return change;
    }

}
