package com.company.presentation;

import com.company.business.BusinessFacade;

public class UIController {
    private final UIManager ui;
    private final BusinessFacade bf;

    public UIController(UIManager ui, BusinessFacade bf) {
        this.ui = ui;
        this.bf = bf;
    }

    public void run (){
        //TrialData
        String trialName;
        String journalNme;
        String quartile;
        int acc;
        int rev;
        int rej;

        //Trial 2
        String masterName;
        int creditNum;
        int probCredit;

        // Trial 3
        String study;
        int dificulty;

        // Trial 4
        String entity;
        int budget;

        //EditionData
        int año;
        int numJugadores;
        int numPruebas;
        int[] seleccionPruebas;


        while(true){
            switch (ui.showRoles()) {
                case SELECT_COMPOSITOR:
                    switch (ui.opcionesCompositor()) {

                        case MANAGE_TRIALS:
                            switch (ui.menuTrials()) {
                                case CREATE_TRIALS:
                                    //ConsoleUIManager pide los datos al usuario
                                    switch (ui.askTrialsTypes()) {
                                        case 1:
                                            trialName = ui.askString(ConsoleUIManager.askTrialName);
                                            journalNme = ui.askString(ConsoleUIManager.askTrialJournal);
                                            quartile = ui.askQuartile();
                                            acc = ui.askInt(ConsoleUIManager.askAccessProb, 0, 100);
                                            rev = ui.askInt(ConsoleUIManager.askRevProb, 0, 100);
                                            rej = ui.askInt(ConsoleUIManager.askRejProb, 0, 100);
                                            //Le decimos al DAO de business que cree la trial
                                            bf.createTrial(trialName, journalNme, quartile, acc, rev, rej);
                                        break;
                                        case 2:
                                            trialName = ui.askString(ConsoleUIManager.askTrialName);
                                            masterName = ui.askString(ConsoleUIManager.askMaster);
                                            creditNum = ui.askInt(ConsoleUIManager.askCredits, 60, 120);
                                            probCredit = ui.askInt(ConsoleUIManager.askProbCredit, 0 , 100);
                                            bf.createTrial(trialName, masterName, creditNum, probCredit);
                                        break;
                                        case 3:
                                            trialName = ui.askString(ConsoleUIManager.askTrialName);
                                            study = ui.askString(ConsoleUIManager.askStudy);
                                            dificulty = ui.askInt(ConsoleUIManager.askDificulty, 1, 10);
                                            bf.createTrial(trialName, study, dificulty, true);
                                        break;
                                        case 4:
                                            trialName = ui.askString(ConsoleUIManager.askTrialName);
                                            entity = ui.askString(ConsoleUIManager.askEntity);
                                            budget = ui.askInt(ConsoleUIManager.askBudget, 1000, 2000000000);
                                            bf.createTrial(trialName, entity, budget, false);

                                    }
                                    break;
                                case LIST_TRIALS:
                                    ui.listTrialsText();
                                    //muestra las listas creadas
                                    int numPrueba = ui.showTrialsName(bf.trialsNames());
                                    // comprovamos back
                                    if(!bf.trialExit(numPrueba)) {
                                        ui.showTrialData(bf.trialInfo(numPrueba));      //mostrar datos
                                    }
                                    break;
                                case DELETE_TRIALS:
                                    ui.deleteTrialsText();
                                    int numPruebaDel = ui.showTrialsName(bf.trialsNames());
                                    // comprovamos back
                                    if(!bf.trialExit(numPruebaDel)) {
                                       if(ui.deleteConfirmation(numPruebaDel, bf.trialsNames())) {
                                           bf.deleteTrial(numPruebaDel);
                                           ui.deleteOK();
                                       }
                                    }
                                    break;
                                default:


                            }
                            break;

                        case MANAGE_EDITIONS:
                            switch (ui.menuEditions()){
                                case CREATE_EDITION:
                                    //pedimos info
                                    año = ui.askInt(ConsoleUIManager.askEditionYear, 1000, 9999);
                                    numJugadores = ui.askInt(ConsoleUIManager.askEditionPlayers, 1, 5);
                                    numPruebas= ui.askInt(ConsoleUIManager.askEditionNumberTrials, 3, 12);
                                    seleccionPruebas = ui.pickEditionTrials(bf.trialsNames(), numPruebas);
                                    if(seleccionPruebas[0] == -1) {
                                        ui.showTrialERROR();
                                    }
                                    else {
                                        bf.createEdition(año, numJugadores, numPruebas, seleccionPruebas);
                                    }
                                    break;

                                case LIST_EDITIONS:
                                    ui.showEditionMessage(MenuOptions.LIST_EDITIONS);
                                    int numEdition = ui.showEditionYears(bf.editionYear());
                                    if(!bf.exitEdition(numEdition)) {
                                        ui.showEditionData(bf.editionInfo(numEdition));
                                    }
                                    break;
                                case DUPLICATE_EDITION:
                                    ui.showEditionMessage(MenuOptions.DUPLICATE_EDITION);
                                    int numEditionDuplicate = ui.showEditionYears(bf.editionYear());
                                    if(!bf.exitEdition(numEditionDuplicate)) {
                                        int numDuplicateYear = ui.askInt(ConsoleUIManager.askDuplicateYear, 1000, 9999);
                                        int numDuplicatePlayer = ui.askInt(ConsoleUIManager.askDuplicateNumPlayers, 1 , 5);
                                        int[] numDuplicateNum = bf.editionTrials(numEditionDuplicate);
                                        bf.createEdition(numDuplicateYear, numDuplicatePlayer, numDuplicateNum.length, numDuplicateNum);
                                    }
                                    break;
                                case DELETE_EDITION:
                                    ui.showEditionMessage(MenuOptions.DELETE_EDITION);
                                    int numEditionDelete = ui.showEditionYears(bf.editionYear());
                                    if(!bf.exitEdition(numEditionDelete)) {
                                        ui.askDeleteYear(Integer.valueOf(bf.editionInfo(numEditionDelete)[0]));
                                        bf.deleteEdition(numEditionDelete);
                                    }
                                    break;

                            }
                            break;
                        case EXIT:
                            break;

                    }
                    break;
                case SELECT_CONDUCTOR:
                    String[] results;
                    do {
                        if(bf.checkCurrentEdition()) {
                            int[] editionData = bf.getCurrentEditionData();
                            if (bf.loadCurrentEdition() == null) {
                                results = bf.startEdition(ui.askPlayers(editionData[0], editionData[1]));
                                ui.showResults(results);
                                if(!ui.askToContinue()) {
                                    ui.exitProgram();
                                    System.exit(0);
                                }
                            }
                            else {
                                if(!bf.checkEditionFinish()) {
                                    results = bf.executeEdition();
                                    ui.showResults(results);
                                    if(bf.checkEditionFinish()) {
                                        ui.showEditionResult(results, bf.getCurrentYear());
                                        bf.clearCurrentEdition();
                                        System.exit(0);
                                    }
                                    else if(!ui.askToContinue()) {
                                        ui.exitProgram();
                                        System.exit(0);
                                    }
                                }
                                else {
                                    System.exit(0);
                                }
                            }
                        }
                        else {
                            ui.noCurrentEdition(bf.getCurrentYear());
                            System.exit(0);
                        }
                    }while (true);

            }
        }

    }
}
