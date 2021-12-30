package com.company.presentation;

public interface UIManager {
    MenuOptions showRoles();
    MenuOptions opcionesCompositor();
    MenuOptions menuTrials();
    int showTrialsTypes();
    String askTrialName();
    String askJournalName();
    String askQuartile();
    int askAccessProb();
    int askRevProb();
    int askRejProb();
    int showTrialsName(String[] names);
    void showTrialData(String[] data);
    void listTrialsText();
    void deleteTrialsText();
    boolean deleteConfirmation(int numPrueba, String[] names);
    void deleteOK();
    MenuOptions menuEditions();
    int askEditionYear();
    int askEditionPlayers();
    int askEditionNumberTrials();
    int[] pickEditionTrials(String[] nameTrial, int numPruebas);
    int showEditionYears(int[] years);
    void showEditionData(String[] info);
    }

