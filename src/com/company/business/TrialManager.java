package com.company.business;

import com.company.persistence.TrialDAO;
import com.company.persistence.TrialDAOcsv;
import com.company.persistence.TrialDAOjson;

import java.util.ArrayList;

/**
 * Manejar las pruebas
 */
public class TrialManager {
    private final TrialDAO trialDAO;

    /**
     * Inicializamos el formato de persistencia que se desea guardar, csv o json
     */
    public TrialManager(Boolean pType) {

        if(!pType) {
            trialDAO = new TrialDAOcsv("data/trials.csv");
        }
        else {
            trialDAO = new TrialDAOjson("data/trials.json");
        }

    }

    /**
     * Crear la prueba de tipo Publicacion Articulo
     * @param nombrePrueba nombre de la prueba
     * @param nombreRevista nombre de la revista
     * @param quartil dificultad
     * @param probAceptar probabilidad de aceptar
     * @param probRevision probabilidad de revisar
     * @param probDenegar probabilidad de denegar
     * @param save guardar la prueba
     * @return devuelve la prueba Publicacion Articulo
     */
    public TrialPublicacionArticulo crearPruebaPublicacion(String nombrePrueba, String nombreRevista, String quartil, int probAceptar,
                                       int probRevision, int probDenegar, boolean save) {

        TrialPublicacionArticulo prueba = new TrialPublicacionArticulo(nombrePrueba, nombreRevista, quartil, probAceptar,
                probRevision, probDenegar);
        if(save) {
            trialDAO.save(prueba);
        }

        return prueba;
    }

    /**
     * Crear la prueba tipo Master
     * @param nombrePrueba nombre de la prueba
     * @param masterName nombre del master
     * @param credits creditos para acceder al master
     * @param prob probabilidad de aprobar un credito
     * @param save guardar la prueba
     * @return devuelve la prueba de tipo Master
     */
    public TrialMaster crearTrialMaster(String nombrePrueba, String masterName, int credits, int prob, boolean save){
        TrialMaster prueba = new TrialMaster(nombrePrueba,  masterName,  credits,  prob);
        if(save) {
            trialDAO.save(prueba);
        }
        return prueba;
    }

    /**
     * Crear la prueba tipo tesis
     * @param nombrePrueba nombre de la prueba
     * @param study campo de estudios
     * @param dificulty dificultad de defensa
     * @param save guardar la prueba
     * @return devuelve la prueba de tipo Tesis
     */
    public TrialTesis crearTrialTesis(String nombrePrueba, String study, int dificulty, boolean save){
        TrialTesis prueba = new TrialTesis(nombrePrueba, study, dificulty);
        if(save) {
            trialDAO.save(prueba);
        }
        return prueba;
    }

    /**
     * Crear la prueba de tipo Budget
     * @param nombrePrueba nombre de la prueba
     * @param entity entidad que se le pedi presupuesto
     * @param budget presupuesto
     * @param save guardar la prueba
     * @return devuelve la prueba tipo Budget
     */
    public TrialBudget crearTrialBudget(String nombrePrueba, String entity, int budget, boolean save){
        TrialBudget prueba = new TrialBudget(nombrePrueba,  entity, budget);
        if(save) {
            trialDAO.save(prueba);
        }
        return prueba;
    }

    /**
     * Lista de las pruebas
     * @return devuelve una lista de todas las pruebas que hay
     */
    public String[] listaPruebas() {
        Trial[] trials;
        trials = trialDAO.getAll();
        ArrayList <String> names = new ArrayList<>();
        for (Trial trial: trials) {
            names.add(trial.getNombre());
        }

        return names.toArray(new String[names.size()]);
    }

    /**
     * Datos de la prueba dependiendo su tipo y persistencia
     * @param numberPrueba numero de la prueba que se gestiona
     * @return devuelve toda la informacion de la prueba
     */
    public  String[] infoPrueba(int numberPrueba) {
        int type;
        type = trialDAO.getType(numberPrueba);
        switch (type) {
            case 1:
                TrialPublicacionArticulo trial;
                trial = trialDAO.getPublicacion(numberPrueba);
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
                TrialMaster trial2;
                trial2 = trialDAO.getMaster(numberPrueba);
                ArrayList<String> data2 = new ArrayList<>();
                data2.add("2");
                data2.add(trial2.getNombre());
                data2.add(trial2.getMasterName());
                data2.add(String.valueOf(trial2.getCreditNumber()));
                data2.add(String.valueOf(trial2.getProbCredit()));
                return data2.toArray(new String[data2.size()]);
            case 3:
                TrialTesis trial3;
                trial3 = trialDAO.getTesis(numberPrueba);
                ArrayList<String> data3 = new ArrayList<>();
                data3.add("3");
                data3.add((trial3.getNombre()));
                data3.add(trial3.getStudy());
                data3.add(String.valueOf(trial3.getDificulty()));
                return data3.toArray(new String[data3.size()]);
            case 4:
                TrialBudget trial4;
                trial4 = trialDAO.getBudget(numberPrueba);
                ArrayList<String> data4 = new ArrayList<>();
                data4.add("4");
                data4.add((trial4.getNombre()));
                data4.add(trial4.getEntity());
                data4.add(String.valueOf(trial4.getBudget()));
                return data4.toArray(new String[data4.size()]);

            default: return null;
        }
    }

    /**
     * Salir de la prueba
     * @param numPrueba numero de la prueba que se quiere gestionar
     * @return devuelve si se quiere salir o no de la prueba
     */
    public boolean trialExit(int numPrueba) {
        return trialDAO.trialExit(numPrueba);
    }

    /**
     * Eliminar la prueba
     * @param numPrueba numero de la prueba que se quiere eliminar
     */
    public void delete(int numPrueba) {
        trialDAO.delete(numPrueba);
    }
}
