package com.company.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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
    public Edition addEditionTrial(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition);
        TrialPublicacionArticulo[] trials = new TrialPublicacionArticulo[edition.getNumTrials()];

        for (int i = 0; i < edition.getNumTrials(); i++) {
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
        Edition edition = addEditionTrial(numEdition);      //crearla y meterle todas las pruebas

        TrialPublicacionArticulo[] trials = edition.getTrials();
        info.add(String.valueOf(edition.getYear()));
        info.add(String.valueOf(edition.getNumPlayers()));
        info.add(String.valueOf(edition.getNumTrials()));

        for (TrialPublicacionArticulo trial: trials) {
            info.add(trial.getNombre());
        }

        return info.toArray(new String[info.size()]);
    }

    @Override
    public boolean exitEdition(int numEdition) {
        return editionManager.exitEdition(numEdition);
    }

    @Override
    public int[] editionTrials(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition);
        return edition.getNum();
    }

    @Override
    public void deleteEdition(int numEdition) {
        editionManager.deleteEdition(numEdition);
    }

    @Override
    public boolean checkCurrentEdition() {
        if(editionManager.getCurrentEdition() == null) {
            return false;
        }
        return true;
    }

    @Override
    public int[] getCurrentEditionData() {
        Edition edition = editionManager.getCurrentEdition();
        int year = edition.getYear();
        int numPlayers = edition.getNumPlayers();

        return new int[] {year,numPlayers};
    }

    @Override
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public void executeEdition(String[] players) {
       // editionManager.saveCurrentEdition(editionManager.getCurrentEdition(),savePlayers(players),0);
        Edition edition = editionManager.getCurrentEdition();
        TrialPublicacionArticulo[] trial = edition.getTrials();
        Random random = new Random();
        boolean b;
        int quartilNum = trial[0].getQuartil().charAt(1);
        for (int i = 0; i < edition.getNumPlayers(); i++) {
            b = false;
            if (random.nextInt(0,100) <= trial[0].getProbDenegar()){
                while (b){
                    if (random.nextInt(0,100) <= trial[0].getProbRevision()){
                        b = true;
                    }
                }
                if (random.nextInt(0,100) <= trial[0].getProbAceptar()){
                    switch (quartilNum){
                        case 1:

                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                }
            }

        }

    }

    @Override
    public Edition loadCurrentEdition() {
        return editionManager.loadCurrentEdition();
    }

    private Player[] savePlayers(String[] names) {
        Player[] players = new Player[names.length];

        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }
        return players;
    }


}
