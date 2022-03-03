package com.company.business;

public class TrialMaster extends Trial{
    private String masterName;
    private int creditNumber;
    private int probCredit;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public int getProbCredit() {
        return probCredit;
    }

    public void setProbCredit(int probCredit) {
        this.probCredit = probCredit;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }

    public TrialMaster(String nombre, String masterName, int creditNumber, int probCredit) {
        super(nombre);
        this.masterName = masterName;
        this.creditNumber = creditNumber;
        this.probCredit = probCredit;

    }

}
