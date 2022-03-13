package com.company;

import com.company.business.BusinessFacade;
import com.company.business.BusinessFacadeImpl;
import com.company.business.TrialMaster;
import com.company.business.TrialPublicacionArticulo;
import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;
import com.company.persistence.TrialDAOjson;
import com.company.presentation.ConsoleUIManager;
import com.company.presentation.UIController;
import com.company.presentation.UIManager;
import org.w3c.dom.CDATASection;

public class Main {

    public static void main(String[] args) {
        UIManager consoleUIManager = new ConsoleUIManager();
        TrialDAO trialDAO = new TrialDAOcsv("data/trials.csv");
        BusinessFacade businessFacade = new BusinessFacadeImpl();
        UIController uiController = new UIController(consoleUIManager, businessFacade);
        uiController.run();
    }
}
