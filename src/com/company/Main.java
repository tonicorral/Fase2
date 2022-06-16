package com.company;

import com.company.business.*;
import com.company.persistence.EditionDAOjson;
import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;
import com.company.persistence.TrialDAOjson;
import com.company.presentation.ConsoleUIManager;
import com.company.presentation.UIController;
import com.company.presentation.UIManager;
import org.w3c.dom.CDATASection;

/**
 * Se ejecutan todas las funciones de las diferentes capas
 */
public class Main {

    /**
     * Llamamos la funciones de las diferentes capas
     * @param args recoger y almanecenar los valores
     */
    public static void main(String[] args) {
        UIManager consoleUIManager = new ConsoleUIManager();
        UIController uiController = new UIController(consoleUIManager);
        uiController.run();

    }
}
