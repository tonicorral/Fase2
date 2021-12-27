package com.company.business;

public interface BusinessFacade {
    void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej);
    String[] trialsNames();
    String[] trialInfo(int numberTrial);
    boolean trialExit(int numPrueba);
    void deleteTrial(int numPrueba);
}
