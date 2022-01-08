package com.company.business;

public class Player {
    private String name;
    private int PI;
    private int counter;
    private boolean win;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Player(String name) {
        this.name = name;
        this.PI = 5;
    }

    public Player(String name, int PI) {
        this.name = name;
        this.PI = PI;
    }

    public void addPI(int PI) {
        this.PI = this.PI + PI;
    }

    public String getName() {
        return name;
    }

    public int getPI() {
        return PI;
    }
}
