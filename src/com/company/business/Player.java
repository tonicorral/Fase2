package com.company.business;

public class Player {
    private String name;
    private int PI;
    private int counter;
    private boolean win;
    private int type;
    private int ECTS;

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

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public int getECTS() {
        return ECTS;
    }

    public Player(String name) {
        this.name = name;
        this.PI = 5;
        this.type = 0;
        this.ECTS = 0;
    }

    public void setPI(int PI) {
        this.PI = PI;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public Player(String name, int PI, int type) {
        this.name = name;
        this.PI = PI;
        this.type = type;
        this.ECTS = 0;
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
