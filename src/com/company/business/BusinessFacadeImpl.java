package com.company.business;

import java.util.ArrayList;

public class BusinessFacadeImpl implements BusinessFacade{
    private final TrialManager trialManager;        //Solo una vez
    private final EditionManager editionManager;
    private final ConductorManager conductorManager;

    public BusinessFacadeImpl() {
        trialManager = new TrialManager();
        editionManager = new EditionManager();
        conductorManager = new ConductorManager();
    }

    @Override   //Le passa los datos al manager para crear la prueba
    public void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej) {
        trialManager.crearPruebaPublicacion(trialName, trialJournal, quartile, acc, rev, rej);
    }

    @Override
    public String[] trialsNames() {

        return trialManager.listaPruebas();
    }

    @Override
    public String[] trialInfo(int numberTrial) {

        return trialManager.infoPrueba(numberTrial);
    }

    @Override
    public boolean trialExit(int numPrueba) {
        return trialManager.trialExit(numPrueba);
    }

    @Override
    public void deleteTrial(int numPrueba) {
        trialManager.delete(numPrueba);
    }

    @Override
    public void createEdition(int year, int numPlayers, int numTrials, int[] nums) {
        editionManager.crearEdition(year, numPlayers, numTrials, nums);
    }

    @Override
    public int[] editionYear() {
        return editionManager.listaEdition();
    }

    @Override
    public Edition editionTrials(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition);
        TrialPublicacionArticulo[] trials = new TrialPublicacionArticulo[edition.getNumTrials()];

        for (int i = 0; i < edition.getNumTrials(); i++) {
            System.out.println(edition.getNum()[i]);
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i]);         //pillamos todos los datos de la prueba
            trials[i] = trialManager.crearPruebaPublicacion(prueba[0], prueba[1], prueba[2], Integer.parseInt(prueba[3]),
                    Integer.parseInt(prueba[4]), Integer.parseInt(prueba[5]));
        }
        edition.setTrials(trials);
        return edition;
    }

    @Override
    public String[] editionInfo(int numEdition) {
        ArrayList<String> info = new ArrayList<>();
        Edition edition = editionTrials(numEdition);      //crearla y meterle todas las pruebas

        TrialPublicacionArticulo[] trials = edition.getTrials();
        info.add(String.valueOf(edition.getYear()));
        info.add(String.valueOf(edition.getNumPlayers()));
        info.add(String.valueOf(edition.getNumTrials()));

        for (TrialPublicacionArticulo trial: trials) {
            info.add(trial.getNombre());
        }

        return info.toArray(new String[info.size()]);
    }


}
