package com.company.business;

import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;

public class TrialManager {
    private final TrialDAO trialDAO;

    public TrialManager() {
        trialDAO = new TrialDAOcsv("data/trials.csv");
    }

    public void crearPruebaPublicacion(String nombrePrueba, String nombreRevista, String quartil, int probAceptar,
                                       int probRevision, int probDenegar) {

        TrialPublicacionArticulo prueba = new TrialPublicacionArticulo(nombrePrueba, nombreRevista, quartil, probAceptar,
                probRevision, probDenegar);

        trialDAO.save(prueba);
        System.out.println("The trial was created succesfully!");
    }

    /*public void listaPruebas() {
        int num = 1;

        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
        for (Trial prueba: pruebas) {
            System.out.println(num+") "+prueba.getNombre());

            num++;
        }
        System.out.println("\n"+num+") Back");
        System.out.println("\nEnter an option:");

    }*/
}
