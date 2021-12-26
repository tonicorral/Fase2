package com.company.business;

public interface BusinessFacade {
    void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej);
    String[] showTrialsName();
    String[] trialInfo(int numberTrial);

}
