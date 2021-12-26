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
        String trialName;
        String journalNme;
        String quartile;
        int acc;
        int rev;
        int rej;
        while(true){
            switch (ui.showRoles()){
                case SELECT_COMPOSITOR:
                    switch (ui.opcionesCompositor()){
                        case MANAGE_TRIALS:
                            switch (ui.menuTrials()){
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
                                    //muestra las listas creadas
                                    int numPrueba = ui.showTrialsName(bf.showTrialsName());
                                    ui.showTrialData(bf.trialInfo(numPrueba));
                            }
                        case EXIT:
                            break;
                    }
            }
        }

    }
}
