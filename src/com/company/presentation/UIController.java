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
                                    ui.showTrialsTypes();
                                    trialName = ui.askTrialName();
                                    journalNme = ui.askJournalName();
                                    quartile = ui.askQuartile();
                                    acc = ui.askAccessProb();
                                    rev = ui.askRevProb();
                                    rej = ui.askRejProb();
                                    //Le decimos al DAO de business que cree la trial
                                    bf.createTrial(trialName, journalNme, quartile, acc, rev, rej);
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

                            }
                            break;

                        case MANAGE_EDITIONS:
                            switch (ui.menuEditions()){
                                case CREATE_EDITION:
                                    //pedimos info
                                    año = ui.askEditionYear();
                                    numJugadores = ui.askEditionPlayers();
                                    numPruebas= ui.askEditionNumberTrials();
                                    seleccionPruebas = ui.pickEditionTrials(bf.trialsNames(), numPruebas);
                                    //creamos edicion
                                    bf.createEdition(año, numJugadores, numPruebas, seleccionPruebas);
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
                                        int numDuplicateYear = ui.askDuplicateYear();
                                        int numDuplicatePlayer = ui.askDuplicateNumPlayers();
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
            }
        }

    }
}
