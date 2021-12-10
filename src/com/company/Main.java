package com.company;

import com.company.business.GestionPruebas;
import com.company.presentation.ConsoleUIManager;
import com.company.presentation.UIController;
import com.company.presentation.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager consoleUIManager = new ConsoleUIManager();
        //menu.ejecutarMenu();
        UIController uiController = new UIController(consoleUIManager);
        uiController.run();
        System.out.println("d");
    }
}
