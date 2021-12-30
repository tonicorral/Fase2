package com.company.business;

public interface BusinessFacade {
    void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej);
    String[] trialsNames();
    String[] trialInfo(int numberTrial);
    boolean trialExit(int numPrueba);
    void deleteTrial(int numPrueba);
    void createEdition(int year, int numPlayers, int numTrials, int[] nums);
    int[] editionYear();
    Edition editionTrials(int numEdition);      //metemos todas las pruebas dentro de la edicion
    String[] editionInfo(int numEdition);       //sacamos la informacion de la edicion
}
