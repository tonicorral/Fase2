package com.company.business;

public class TrialMaster extends Trial{
    private String masterName;
    private int creditNumber;
    private int probCredit;

    public String getMasterName() {
        return masterName;
    }

    public int getProbCredit() {
        return probCredit;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public TrialMaster(String nombre, String masterName, int creditNumber, int probCredit) {
        super(nombre);
        this.masterName = masterName;
        this.creditNumber = creditNumber;
        this.probCredit = probCredit;

    }
}
