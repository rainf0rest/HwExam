package com.example.rain.huaweiexam;

/**
 * Created by rain on 2016/10/13.
 */
public class Robot {
    private Item items[] = new Item[3];
    private Rmb rmbs[] = new Rmb[3];
    private int money;

    public Robot() {
        init();
        money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void init() {
        items[0] = new Item("A", 5, 3);
        items[1] = new Item("B", 6, 4);
        items[2] = new Item("C", 7, 5);
        rmbs[0] = new Rmb("1", 15, 1);
        rmbs[1] = new Rmb("2", 10, 2);
        rmbs[3] = new Rmb("3", 5, 5);
    }

    public boolean ItemIsEmpty(int i) {
        if(items[i].getNumber() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean RmbIsEmpty(int i) {
        if(rmbs[i].getNumber() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Rmb[] getRmbs() {
        return rmbs;
    }

    public void setRmbs(Rmb[] rmbs) {
        this.rmbs = rmbs;
    }
}
