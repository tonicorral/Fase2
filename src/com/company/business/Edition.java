package com.company.business;

public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private TrialPublicacionArticulo[] trials;
    private int[] num;

    public Edition(int year, int numPlayers, int numTrials, TrialPublicacionArticulo[] trials) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.trials = trials;
    }

    public Edition(int year, int numPlayers, int numTrials, int[] num) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.num = num;
    }

    public void setTrials(TrialPublicacionArticulo[] trials) {
        this.trials = trials;
    }

    public int[] getNum() {
        return num;
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

    public TrialPublicacionArticulo[] getTrials() {
        return trials;
    }
}
