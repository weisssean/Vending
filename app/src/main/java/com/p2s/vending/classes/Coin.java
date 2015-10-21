package com.p2s.vending.classes;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class Coin {
    private final String name;
    private final int value;
    public long quantity;

    public Coin(String name, int value){
        this.name =name;
        this.value = value;
        this.quantity = 0;
    }

    public String name() {
        return name;
    }

    public double value() {
        return value;
    }
}
