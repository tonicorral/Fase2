package com.company.business;

public class Player {
    String name;
    int PI;

    public Player(String name) {
        this.name = name;
        this.PI = 5;
    }

    public void addPI(int PI) {
        this.PI += PI;
    }

    public String getName() {
        return name;
    }

    public int getPI() {
        return PI;
    }
}
