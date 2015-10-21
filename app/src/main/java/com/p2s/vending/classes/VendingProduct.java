package com.p2s.vending.classes;

import com.p2s.vending.interfaces.product;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class VendingProduct implements product, Serializable {
    private String name;
    private int price;
    private int stock = 0;
    private String id;

    public VendingProduct(String name, int price, int startingInventory) {
        this.name = name;
        this.price = price;
        this.stock =startingInventory;
        id = UUID.randomUUID().toString();
    }

    @Override
    public void addQuantity(int quantity) {
        stock += quantity;

    }

    @Override
    public void sellProduct() {
        stock--;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }

    public int stock() {
        return stock;
    }

    public String id() {
        return this.id;
    }
}
