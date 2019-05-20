package com.epam;

public class Customer {
    private boolean lowNeed;
    private int talkTime;
    private int number;

    public Customer (boolean lowNeed) {
        this.lowNeed = lowNeed;
    }

    public int getTalkTime () {
        if (lowNeed) {
            return 5000;
        } else {
            return 10000;
        }
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public int getNumber () {
        return number;
    }
}
