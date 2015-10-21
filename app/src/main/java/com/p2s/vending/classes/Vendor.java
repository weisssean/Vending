package com.p2s.vending.classes;

import android.util.Log;

import com.p2s.vending.VendingApp;
import com.p2s.vending.interfaces.I_Vendor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class Vendor implements Serializable{


    private static final int MINSTOCK = 10;
    private static int balance = 0;
    public static ArrayList<VendingProduct> products = new ArrayList<>();

     public static int getBalance() {
        return balance;
    }

     public static void sellProduct(VendingProduct product) {
        for (VendingProduct p:products) {
            if (product.id().equals(p.id())){
                p.sellProduct();
            }

        }
        if (product.stock()>MINSTOCK){
            notifyLowStock(product);
        }
    }
     public static void notifyLowStock(VendingProduct product) {
        //TODO: impliment notify the supplier

    }
     public static void notifyReStock(VendingProduct product) {
        //TODO: impliment notify the supplier

    }


     public static void addProduct(String name, int price, int startingInventory) {
        products.add(new VendingProduct(name, price,startingInventory));
    }

     public static void setBalance(int bal) {
        balance = bal;
    }

     public static void addInventory(VendingProduct product,int inventory){
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

            if (balance / c.value() >= 1) {
                c.quantity = (long) ((double)balance / (double)c.value());
                change.add(c);
                Log.d("Change", "balance-= " + c.quantity * c.value());

                balance -= c.quantity * c.value();
                Log.d("Change", "balance= " + balance);


            }
        }
        return change;
    }

}
