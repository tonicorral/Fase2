package com.company.business;

import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;

import java.util.ArrayList;

public class TrialManager {
    private final TrialDAO trialDAO;

    public TrialManager() {
        trialDAO = new TrialDAOcsv("data/trials.csv");
    }

    public TrialPublicacionArticulo crearPruebaPublicacion(String nombrePrueba, String nombreRevista, String quartil, int probAceptar,
                                       int probRevision, int probDenegar) {

        TrialPublicacionArticulo prueba = new TrialPublicacionArticulo(nombrePrueba, nombreRevista, quartil, probAceptar,
                probRevision, probDenegar);

        trialDAO.save(prueba);
        System.out.println("The trial was created succesfully!");

        return prueba;
    }

    public String[] listaPruebas() {
        Trial[] trials = trialDAO.getAll();
        ArrayList <String> names = new ArrayList<>();
        for (Trial trial: trials) {
            names.add(trial.getNombre());
        }

        return names.toArray(new String[names.size()]);
    }

    public  String[] infoPrueba(int numberPrueba) {
        TrialPublicacionArticulo trial = trialDAO.get(numberPrueba);
        ArrayList<String> data = new ArrayList<>();
        data.add(trial.getNombre());
        data.add(trial.getNombreRevista());
        data.add(trial.getQuartil());
        data.add(String.valueOf(trial.getProbAceptar()));
        data.add(String.valueOf(trial.getProbRevision()));
        data.add(String.valueOf(trial.getProbDenegar()));

        return data.toArray(new String[data.size()]);
    }

    public boolean trialExit(int numPrueba) {
        return trialDAO.trialExit(numPrueba);
    }

    public void delete(int numPrueba) {
        trialDAO.delete(numPrueba);
    }


}
