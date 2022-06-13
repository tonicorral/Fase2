package com.company.presentation;

/**
 * Guardar funciones de otras clases y comunicar presentacion con business
 */
public interface UIManager {
    MenuOptions showRoles();
    MenuOptions opcionesCompositor();
    MenuOptions menuTrials();
    int askTrialsTypes();
    int showTrialsName(String[] names);
    void showTrialData(String[] data);
    void listTrialsText();
    void deleteTrialsText();
    boolean deleteConfirmation(int numPrueba, String[] names);
    void deleteOK();
    MenuOptions menuEditions();
    int[] pickEditionTrials(String[] nameTrial, int numPruebas);
    int showEditionYears(int[] years);
    void showEditionData(String[] info);
    void showEditionMessage(MenuOptions option);
    boolean askDeleteYear(int editionYear);
    String askString(String text);
    int askInt(String text);
    int askInt(String text, int min, int max);
    void noCurrentEdition(int year);
    String[] askPlayers(int year, int numPlayers);
    boolean askToContinue();
    void showResults(String[] results);
    void exitProgram();
    void showEditionResult(String[] results, int year);
    void showTrialERROR();
    String askQuartile();
    boolean askPersistence();
    boolean checkPercentage(int acc, int rev, int rej);
    }

