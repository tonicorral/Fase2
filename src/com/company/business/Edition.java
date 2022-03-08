package com.company.business;

import java.util.ArrayList;

public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList trials;
    private int[] num;
    private Player[] players;
    private  int currentTrial;

    public Player[] getPlayers() {
        return players;
    }

    public int getCurrentTrial() {
        return currentTrial;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setCurrentTrial(int currentTrial) {
        this.currentTrial = currentTrial;
    }

    public void setTrials(ArrayList trials) {
        this.trials = trials;
    }

    public Edition(int year, int numPlayers, int numTrials, int[] num) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.num = num;
        this.trials = new ArrayList();
    }
    public ArrayList getTrials() {
        return trials;
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


}