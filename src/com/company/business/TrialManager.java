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
                                       int probRevision, int probDenegar, boolean save) {

        TrialPublicacionArticulo prueba = new TrialPublicacionArticulo(nombrePrueba, nombreRevista, quartil, probAceptar,
                probRevision, probDenegar);
        if(save) {
            trialDAO.save(prueba);
        }


        return prueba;
    }

    public TrialMaster crearTrialMaster(String nombrePrueba, String masterName, int credits, int prob, boolean save){
        TrialMaster prueba = new TrialMaster(nombrePrueba,  masterName,  credits,  prob);
        if(save) {
            trialDAO.save(prueba);
        }
        return prueba;
    }
    public TrialTesis crearTrialTesis(String nombrePrueba, String study, int dificulty, boolean save){
        TrialTesis prueba = new TrialTesis(nombrePrueba, study, dificulty);
        if(save) {
            trialDAO.save(prueba);
        }
        return prueba;
    }
    public TrialBudget crearTrialBudget(String nombrePrueba, String entity, int budget, boolean save){
        TrialBudget prueba = new TrialBudget(nombrePrueba,  entity, budget);
        if(save) {
            trialDAO.save(prueba);
        }
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

        switch (trialDAO.getType(numberPrueba)) {
            case 1:
                TrialPublicacionArticulo trial = trialDAO.getPublicacion(numberPrueba);
                ArrayList<String> data = new ArrayList<>();
                data.add("1");
                data.add(trial.getNombre());
                data.add(trial.getNombreRevista());
                data.add(trial.getQuartil());
                data.add(String.valueOf(trial.getProbAceptar()));
                data.add(String.valueOf(trial.getProbRevision()));
                data.add(String.valueOf(trial.getProbDenegar()));
                return data.toArray(new String[data.size()]);
            case 2:
                TrialMaster trial2 = trialDAO.getMaster(numberPrueba);
                ArrayList<String> data2 = new ArrayList<>();
                data2.add("2");
                data2.add(trial2.getNombre());
                data2.add(trial2.getMasterName());
                data2.add(String.valueOf(trial2.getCreditNumber()));
                data2.add(String.valueOf(trial2.getProbCredit()));
                return data2.toArray(new String[data2.size()]);
            case 3:
                TrialTesis trial3 = trialDAO.getTesis(numberPrueba);
                ArrayList<String> data3 = new ArrayList<>();
                data3.add("3");
                data3.add((trial3.getNombre()));
                data3.add(trial3.getStudy());
                data3.add(String.valueOf(trial3.getDificulty()));
                return data3.toArray(new String[data3.size()]);
            case 4:
                TrialBudget trial4 = trialDAO.getBudget(numberPrueba);
                ArrayList<String> data4 = new ArrayList<>();
                data4.add("4");
                data4.add((trial4.getNombre()));
                data4.add(trial4.getEntity());
                data4.add(String.valueOf(trial4.getBudget()));
                return data4.toArray(new String[data4.size()]);

            default: return null;
        }
    }

    public boolean trialExit(int numPrueba) {
        return trialDAO.trialExit(numPrueba);
    }

    public void delete(int numPrueba) {
        trialDAO.delete(numPrueba);
    }


}
