package com.company.business;

/**
 *Gestionar todas las operaciones para manejar las pruebas y ediciones
 */
public interface BusinessFacade {
    void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej);
    void createTrial(String trialName, String masterName, int credits, int prob);
    void createTrial(String trialName, String b, int num, boolean check);
    String[] trialsNames();
    String[] trialInfo(int numberTrial);
    boolean trialExit(int numPrueba);
    void deleteTrial(int numPrueba);
    void createEdition(int year, int numPlayers, int numTrials, int[] nums);
    int[] editionYear();
    Edition addEditionTrial(int numEdition);
    String[] editionInfo(int numEdition);
    boolean exitEdition(int numEdition);
    int[] editionTrials(int numEdition);
    void deleteEdition(int numEdition);
    boolean checkCurrentEdition();
    int[] getCurrentEditionData();
    int getCurrentYear();
    String[] executeEdition();
    String[] startEdition(String[] players);
    Edition loadCurrentEdition();
    boolean checkEditionFinish();
    void clearCurrentEdition();
    void setPType(boolean pType);
}
