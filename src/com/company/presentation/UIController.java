package com.company.presentation;

public class UIController {
    private final UIManager ui;

    public UIController(UIManager ui) {
        this.ui = ui;
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
                                    ui.showTrialsTypes();
                                    trialName = ui.askTrialName();
                                    journalNme = ui.askJournalName();
                                    quartile = ui.askQuartile();
                                    acc = ui.askAccessProb();
                                    rev = ui.askRevProb();
                                    rej = ui.askRejProb();

                            }
                        case EXIT:
                            break;
                    }
            }
        }

    }
}
