package com.company.business;

public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private Trial[] trials;

    public Edition(int year, int numPlayers, int numTrials, Trial[] trials) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.trials = trials;
    }

    public int getYear() {
        return year;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumTrials() {
        return numTrials;
    }

    public Trial[] getTrials() {
        return trials;
    }
}
