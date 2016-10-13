package com.example.rain.huaweiexam;

/**
 * Created by rain on 2016/10/13.
 */
public class Item {
    private String name;
    private int number;
    private int price;

    public Item(String name, int num, int p) {
        this.name = name;
        this.number = num;
        this.price = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
