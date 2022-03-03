package com.company.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class BusinessFacadeImpl implements BusinessFacade{
    private final TrialManager trialManager;        //Solo una vez
    private final EditionManager editionManager;

    public BusinessFacadeImpl() {
        trialManager = new TrialManager();
        editionManager = new EditionManager();
    }

    @Override   //Le passa los datos al manager para crear la prueba
    public void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej) {
        trialManager.crearPruebaPublicacion(trialName, trialJournal, quartile, acc, rev, rej,true);
    }
    public void createTrial(String trialName, String masterName, int credits, int prob){
        trialManager.crearTrialMaster(trialName, masterName, credits, prob, true);
    }
    public void createTrial(String trialName, String b, int num, boolean check){
        if(check){
            //creamos tipo 3
        }
        else{
            //tipo 4
        }
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
                    Integer.parseInt(prueba[4]), Integer.parseInt(prueba[5]), false);
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
    public String[] executeEdition() {

        Edition edition = addEditionTrial(loadCurrentEdition());
        Player[] players0 = edition.getPlayers();
        int[] type = new int[edition.getNumPlayers()];
        for (int i = 0; i < edition.getNumPlayers(); i++) {
            type[i] = players0[i].getType();
        }
        Player[] players1 = executeTrial(edition.getTrials()[edition.getCurrentTrial()], edition.getPlayers());
        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(), players1, edition.getCurrentTrial()+1);

        int[] types = new int[edition.getNumPlayers()];
        for (int i = 0; i < edition.getNumPlayers(); i++) {
            if(players1[i].getType() > type[i]) {
                types[i] = players1[i].getType();
            }
            else {
                types[i] = 0;
            }
        }

        String[] results = new String[3+edition.getNumPlayers()*5];
        results[0] = edition.getTrials()[edition.getCurrentTrial()].getNombre();
        results[1] = String.valueOf(edition.getCurrentTrial()+1);
        results[2] = String.valueOf(edition.getNumPlayers());

        int j = 3;
        for (int i = 0; i < edition.getNumPlayers(); i++) {
            if(players1[i].getCounter() == -1) {
                results[j] = players1[i].getName()+"*";
            }
            else {
                results[j] = players1[i].getName();
            }
            j++;
            results[j] = String.valueOf(players1[i].getCounter());
            j++;
            results[j] = String.valueOf(players1[i].getWin());
            j++;
            results[j] = String.valueOf(players1[i].getPI());
            j++;
            results[j] = String.valueOf(types[i]);
            j++;
        }

        return results;
    }


    @Override
    public String[] startEdition(String[] players) {
        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(),savePlayers(players),0);
        Edition edition = addEditionTrial(editionManager.loadCurrentEdition());
        Player[] players0 = edition.getPlayers();
        Player[] players1 = executeTrial(edition.getTrials()[0], savePlayers(players));
        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(), players1, 1);

        int[] types = new int[edition.getNumPlayers()];
        for (int i = 0; i < edition.getNumPlayers(); i++) {
            System.out.println(players1[i].getType());
            System.out.println(players0[i].getType());

            if(players1[i].getType() > players0[i].getType()) {
                types[i] = players1[i].getType();
                System.out.println("esta dentro");
            }
            else {
                types[i] = 0;
            }
        }

        String[] results = new String[3+edition.getNumPlayers()*5];
        results[0] = edition.getTrials()[0].getNombre();
        results[1] = "1";
        results[2] = String.valueOf(edition.getNumPlayers());

        int j = 3;

        for (int i = 0; i < edition.getNumPlayers(); i++) {
            results[j] = players1[i].getName();
            j++;
            results[j] = String.valueOf(players1[i].getCounter());
            j++;
            results[j] = String.valueOf(players1[i].getWin());
            j++;
            results[j] = String.valueOf(players1[i].getPI());
            j++;
            results[j] = String.valueOf(types[i]);
            j++;
        }

        return results;
    }

    private Player[] executeTrial (TrialPublicacionArticulo trial, Player[] player){

        Random random = new Random();
        boolean b;
        char quartilNum = trial.getQuartil().charAt(1);

        for (int i = 0; i < player.length; i++) {
            int counter = 0;

            if(player[i].getPI() <= 0) {
                player[i].setCounter(-1);
            }
            else{
                do {
                    b = false;
                    if (random.nextInt(100) <= trial.getProbAceptar()) {
                        switch (quartilNum){
                            case '1':
                                if(player[i].getType() != 2) {
                                    player[i].addPI(4);
                                }
                                else {
                                    player[i].addPI(8);
                                }
                                break;
                            case '2':
                                if(player[i].getType() != 2) {
                                    player[i].addPI(3);
                                }
                                else {
                                    player[i].addPI(6);
                                }
                                break;
                            case '3':
                                if(player[i].getType() != 2) {
                                    player[i].addPI(2);
                                }
                                else {
                                    player[i].addPI(4);
                                }
                                break;
                            case '4':
                                if(player[i].getType() != 2) {
                                    player[i].addPI(1);
                                }
                                else {
                                    player[i].addPI(2);
                                }
                                break;
                        }
                        player[i].setWin(true);
                        b = true;
                    }
                    else if (random.nextInt(100) <= trial.getProbDenegar()) {
                        switch (quartilNum) {
                            case '1':
                                if(player[i].getType() != 0) {
                                    player[i].addPI(-3);
                                }
                                else {
                                    player[i].addPI(-5);
                                }
                                break;
                            case '2':
                                if(player[i].getType() != 0) {
                                    player[i].addPI(-2);
                                }
                                else {
                                    player[i].addPI(-4);
                                }
                                break;
                            case '3':
                                if(player[i].getType() != 0) {
                                    player[i].addPI(-2);
                                }
                                else {
                                    player[i].addPI(-3);
                                }
                                break;
                            case '4':
                                if(player[i].getType() != 0) {
                                    player[i].addPI(-1);
                                }
                                else {
                                    player[i].addPI(-2);
                                }
                                break;
                        }
                        player[i].setWin(false);
                        b = true;
                    }
                    counter = counter + 1;
                }while (!b);

                player[i].setCounter(counter);

            }
            if(player[i].getType() != 2) {
                if(player[i].getPI() >= 10) {
                    player[i].setType(player[i].getType() + 1);
                    player[i].setPI(5);

                }
            }
        }

        return player;
    }
    private Edition addEditionTrial(Edition edition) {
        TrialPublicacionArticulo[] trials = new TrialPublicacionArticulo[edition.getNumTrials()];

        for (int i = 0; i < edition.getNumTrials(); i++) {
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i]);         //pillamos todos los datos de la prueba
            trials[i] = trialManager.crearPruebaPublicacion(prueba[0], prueba[1], prueba[2], Integer.parseInt(prueba[3]),
                    Integer.parseInt(prueba[4]), Integer.parseInt(prueba[5]), false);
        }
        edition.setTrials(trials);
        return edition;
    }
    @Override
    public Edition loadCurrentEdition() {
        return editionManager.loadCurrentEdition();
    }

    @Override
    public boolean checkEditionFinish() {
        Edition edition = loadCurrentEdition();
        if(edition.getNumTrials() == edition.getCurrentTrial()){
            return true;
        }
        return false;
    }

    @Override
    public void clearCurrentEdition() {
        editionManager.clearCurrentEdition();
    }

    private Player[] savePlayers(String[] names) {
        Player[] players = new Player[names.length];

        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }
        return players;
    }


}
