package com.company;

import com.company.business.BusinessFacade;
import com.company.business.BusinessFacadeImpl;
import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;
import com.company.presentation.ConsoleUIManager;
import com.company.presentation.UIController;
import com.company.presentation.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager consoleUIManager = new ConsoleUIManager();
        //menu.ejecutarMenu();
        TrialDAO trialDAO = new TrialDAOcsv("data/trials.csv");
        trialDAO.getAll();
        BusinessFacade businessFacade = new BusinessFacadeImpl();
        UIController uiController = new UIController(consoleUIManager, businessFacade);
        uiController.run();

    }
}
