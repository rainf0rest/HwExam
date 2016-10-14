package com.example.rain.huaweiexam;

/**
 * Created by rain on 2016/10/13.
 */
public class Rmb {
    private String name;
    private int number;
    private int value;

    public Rmb(String name, int num, int v) {
        this.name = name;
        this.number = num;
        this.value = v;
    }

    public void decNumber() {
        number--;
    }

    public void incNumber() {
        number++;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
