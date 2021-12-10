package com.company;

import com.company.business.GestionPruebas;
import com.company.presentation.Menu;

public class Main {

    public static void main(String[] args) {
	    Menu menu = new Menu();
        //menu.ejecutarMenu();
        GestionPruebas gestionPruebas = new GestionPruebas();
        gestionPruebas.crearPruebaPublicacion("Prueba1","a","4A",3,3,3);
        gestionPruebas.crearPruebaPublicacion("Prueba2","a","4A",3,3,3);
        gestionPruebas.crearPruebaPublicacion("Prueba3","a","4A",3,3,3);
        gestionPruebas.listaPruebas();
    }
}
