package com.company.presentation;

public interface UIManager {
    //TRIALS
    public static final String askTrialName = "Enter the trial’s name:";
    public static final String askTrialJournal = "Enter the journal's name:";
    public static final String askTrialQuartile = "Enter the journal’s quartile:";
    public static final String askAccessProb = "Enter the acceptance probability:";
    public static final String askRevProb = "Enter the revision probability:";
    public static final String askRejProb = "Enter the rejection probability:";

    //EDITIONS
    public static final String askEditionYear = "Enter the edition’s year:";
    public static final String askEditionPlayers = "Enter the initial number of players:";
    public static final String askEditionNumberTrials = "Enter the number of trials:";
    public static final String askDuplicateYear = "Enter the new edition’s year:";
    public static final String askDuplicateNumPlayers = "Enter the new edition’s initial number of players:";

    MenuOptions showRoles();
    MenuOptions opcionesCompositor();
    MenuOptions menuTrials();
    int showTrialsTypes();
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
    int askInt();
    int askInt(String text, int min, int max);
    void noCurrentEdition(int year);
    String[] askPlayers(int year, int numPlayers);
    }

