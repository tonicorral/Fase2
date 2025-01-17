package com.company.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Implementa la interfaz del dao BusinessFacade
 */
public class BusinessFacadeImpl implements BusinessFacade{
    private final TrialManager trialManager;
    private final EditionManager editionManager;

    /**
     * Inicializamos trialManager y editionManager para realizar la gestion de prueba y ediciones
     */
    public BusinessFacadeImpl(Boolean pType) {
        trialManager = new TrialManager(pType);
        editionManager = new EditionManager(pType);
    }

    /**
     * Creamos la prueba de Paper publication
     * @param trialName nombre de la prueba
     * @param trialJournal nombre de la revista
     * @param quartile la dificultad
     * @param acc probabilidad de aceptar
     * @param rev probabilidad de revisar
     * @param rej probabilidad de denegar
     */
    @Override
    public void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej) {
        trialManager.crearPruebaPublicacion(trialName, trialJournal, quartile, acc, rev, rej,true);
    }

    /**
     * Creamos la prueba de Master
     * @param trialName nombre de la prueba
     * @param masterName nombre del master
     * @param credits los creditos del master
     * @param prob la probabilidad de aprobar un credito
     */
    public void createTrial(String trialName, String masterName, int credits, int prob) {
        trialManager.crearTrialMaster(trialName, masterName, credits, prob, true);
    }

    /**
     * Crear las pruebas tesis doctoral y presupuesto
     * @param trialName nombre de la prueba
     * @param b String con la seccion de cada prueba
     * @param num cantidad de dificultad o presupuesto
     * @param check variable de comprobacion
     */
    public void createTrial(String trialName, String b, int num, boolean check) {
        if(check){
            trialManager.crearTrialTesis(trialName, b, num, true);
        }
        else{
            trialManager.crearTrialBudget(trialName, b, num, true);
        }
    }

    /**
     * Lista de los nombre de las pruebas
     * @return devuelve la lista de las pruebas
     */
    @Override
    public String[] trialsNames() {
        return trialManager.listaPruebas();
    }

    /**
     * Informaciones de la prueba que se ha elegido
     * @param numberTrial numero de la prueba del cual contiene toda su informacion
     * @return devuelve toda la informacion de la prueba
     */
    @Override
    public String[] trialInfo(int numberTrial) {
        return trialManager.infoPrueba(numberTrial);
    }

    /**
     * Salir de una prueba
     * @param numPrueba prueba de la cual se quiere salir
     * @return devuelve la prueba que se desea dejar de ejecutar
     */
    @Override
    public boolean trialExit(int numPrueba) {
        return trialManager.trialExit(numPrueba);
    }

    /**
     * Eliminar prueba
     * @param numPrueba numero de la prueba que se quiere eliminar
     */
    @Override
    public void deleteTrial(int numPrueba) {
        trialManager.delete(numPrueba);
    }

    /**
     * Crear la edicion
     * @param year año de la edicion
     * @param numPlayers numero de jugadores que jugaran
     * @param numTrials numero de prueba que se ejecutaran
     * @param nums ordenar las pruebas
     */
    @Override
    public void createEdition(int year, int numPlayers, int numTrials, int[] nums) {
        editionManager.crearEdition(year, numPlayers, numTrials, nums);
    }

    /**
     * Gestionar el año de la edicion
     * @return devuelve el año
     */
    @Override
    public int[] editionYear() {
        return editionManager.listaEdition();
    }

    /**
     * Añadir la prueba que se quiere ejecutar segun los tipos que hay
     * @param numEdition numero de la edicion para ordenarlas y identificarlas
     * @return devuelve la edicion añadida dependiendo de los tipos de pruebas que hay
     */
    @Override
    public Edition addEditionTrial(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition);
        ArrayList trials = new ArrayList();

        for (int i = 0; i < edition.getNumTrials(); i++) {
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i]);         //pillamos todos los datos de la prueba
            switch (prueba[0]) {
                case "1":
                    trials.add(trialManager.crearPruebaPublicacion(prueba[1], prueba[2], prueba[3], Integer.parseInt(prueba[4]),
                            Integer.parseInt(prueba[5]), Integer.parseInt(prueba[6]), false));
                    break;
                case "2":
                    trials.add(trialManager.crearTrialMaster(prueba[1], prueba[2], Integer.parseInt(prueba[3]),  Integer.parseInt(prueba[4]), false));
                    break;
                case "3":
                    trials.add(trialManager.crearTrialTesis(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false));
                    break;
                case "4":
                    trials.add(trialManager.crearTrialBudget(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false));
                    break;
            }
        }
        edition.setTrials(trials);
        return edition;
    }

    /**
     * Informacion de la edicion segun el tipo de prueba que se ha elegido
     * @param numEdition numero de la edicion para ordenarlas
     * @return devuelve toda la informacion segun el tipo de prueba
     */
    @Override
    public String[] editionInfo(int numEdition) {
        ArrayList<String> info = new ArrayList<>();
        Edition edition = addEditionTrial(numEdition);      //crearla y meterle todas las pruebas

        ArrayList trials = edition.getTrials();
        info.add(String.valueOf(edition.getYear()));
        info.add(String.valueOf(edition.getNumPlayers()));
        info.add(String.valueOf(edition.getNumTrials()));

        for (Object trial: trials) {
            if(trial instanceof TrialPublicacionArticulo) {
                TrialPublicacionArticulo t = (TrialPublicacionArticulo) trial;
                info.add(t.getNombre());
                info.add("Paper publication");
            }
            else if (trial instanceof TrialMaster) {
                TrialMaster t = (TrialMaster) trial;
                info.add(t.getNombre());
                info.add("Master studies");
            }
            else if (trial instanceof TrialTesis){
                TrialTesis t = (TrialTesis) trial;
                info.add(t.getNombre());
                info.add("Doctoral thesis defense");
            }
            else if (trial instanceof TrialBudget){
                TrialBudget t = (TrialBudget) trial;
                info.add(t.getNombre());
                info.add("Budget request");
            }
        }

        return info.toArray(new String[info.size()]);
    }

    /**
     * Salir de la edicion
     * @param numEdition numero de la edicion para identificarlas
     * @return devuelve si se quiere salir de la edicion o no
     */
    @Override
    public boolean exitEdition(int numEdition) {
        return editionManager.exitEdition(numEdition);
    }

    /**
     * Editar las pruebas
     * @param numEdition numero de la edicion que se quiere gestionar
     * @return devuelve que prueba queremos gestionar
     */
    @Override
    public int[] editionTrials(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition);
        return edition.getNum();
    }

    /**
     * Eliminar edicion
     * @param numEdition numero de la edicion que se quiere gestionar
     */
    @Override
    public void deleteEdition(int numEdition) {
        editionManager.deleteEdition(numEdition);
    }

    /**
     * Comprovar si existe edicion
     * @return devuelve si hay o no edicion creada
     */
    @Override
    public boolean checkCurrentEdition() {
        if(editionManager.getCurrentEdition() == null) {
            return false;
        }
        return true;
    }

    /**
     * Conseguir la informacion de la edicion que se ejecutando actualmente
     * @return devuelve el año y el numero de jugadores que habra
     */
    @Override
    public int[] getCurrentEditionData() {
        Edition edition = editionManager.getCurrentEdition();
        int year = edition.getYear();
        int numPlayers = edition.getNumPlayers();

        return new int[] {year,numPlayers};
    }

    /**
     * Conseguir el año que se esta gestionando actualmente
     * @return devuelve el año
     */
    @Override
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Ejecuta la edicion segun el tipo de prueba que se ha elegido
     * @return devuelve toda la ejecucion de las diferentes acciones de las ediciones
     */
    @Override
    public String[] executeEdition() {
        Edition edition = addEditionTrial(loadCurrentEdition());
        Player[] players0 = edition.getPlayers();
        Player[] players1;
        String trial;
        int[] types = new int[edition.getNumPlayers()];

        try {
            for (int i = 0; i < edition.getNumPlayers(); i++) {
                types[i] = players0[i].getType();
            }

            if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialPublicacionArticulo) {
                players1 = executeTrial((TrialPublicacionArticulo) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "1";
            }
            else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialMaster) {
                players1 = executeTrial((TrialMaster) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "2";
            }
            else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialTesis) {
                players1 = executeTrial((TrialTesis) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "3";
            }
            else {
                players1 = executeTrial((TrialBudget) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "4";
            }

            editionManager.saveCurrentEdition(editionManager.getCurrentEdition(), players1, edition.getCurrentTrial()+1);

            return doEdition(edition, players1, trial, types);
        } catch (IndexOutOfBoundsException e) {
            editionManager.deleteData();
            return null;
        }

    }

    /**
     * Ejecucion del inicio de la edicion
     * @param players array de String con la informacion jugadores
     * @return devuelve una String con los resultados de una edicion
     */
    @Override
    public String[] startEdition(String[] players) {
        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(),savePlayers(players),0);
        Edition edition = addEditionTrial(editionManager.loadCurrentEdition());
        Player[] players0 = edition.getPlayers();
        Player[] players1;
        String trial;
        int[] types = new int[edition.getNumPlayers()];

        try {
            for (int i = 0; i < edition.getNumPlayers(); i++) {
                types[i] = players0[i].getType();
            }

            if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialPublicacionArticulo) {
                players1 = executeTrial((TrialPublicacionArticulo) edition.getTrials().get(0), savePlayers(players));
                trial = "1";
            }
            else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialMaster) {
                players1 = executeTrial((TrialMaster) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "2";
            }
            else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialTesis) {
                players1 = executeTrial((TrialTesis) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "3";
            }
            else {
                players1 = executeTrial((TrialBudget) edition.getTrials().get(edition.getCurrentTrial()), edition.getPlayers());
                trial = "4";
            }

            editionManager.saveCurrentEdition(editionManager.getCurrentEdition(), players1, 1);
            return doEdition(edition, players1, trial, types);

        } catch (IndexOutOfBoundsException e) {
            editionManager.deleteData();
            return null;
        }
    }

    private String[] doEdition(Edition edition, Player[] players1, String trial, int[] types) {
        String[] results;

        for (int i = 0; i < edition.getNumPlayers(); i++) {
            if(players1[i].getType() > types[i]) {
                types[i] = players1[i].getType();
            }
            else {
                types[i] = 0;
            }
        }
        if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialPublicacionArticulo ||
                edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialMaster) {
            results = new String[4+edition.getNumPlayers()*5];
        }
        else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialTesis) {
            results = new String[4+edition.getNumPlayers()*4];
        }
        else {
            results = new String[5+edition.getNumPlayers()*3];
        }

        results[0] = ((Trial) edition.getTrials().get(edition.getCurrentTrial())).getNombre();
        results[1] = trial;
        results[2] = String.valueOf(edition.getCurrentTrial()+1);
        results[3] = String.valueOf(edition.getNumPlayers());

        int j = 4;
        if (edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialPublicacionArticulo) {
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
        }
        else if (edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialMaster) {
            for (int i = 0; i < edition.getNumPlayers(); i++) {
                if (players1[i].getCounter() == -1) {
                    results[j] = players1[i].getName() + "*";
                } else {
                    results[j] = players1[i].getName();
                }
                j++;
                results[j] = String.valueOf(players1[i].getECTS());
                j++;
                Integer r;
                if (players1[i].getECTS() >= ((TrialMaster) edition.getTrials().get(edition.getCurrentTrial())).getCreditNumber() - players1[i].getECTS()) {
                    r = ((TrialMaster) edition.getTrials().get(edition.getCurrentTrial())).getCreditNumber();
                } else {
                    r = (((TrialMaster) edition.getTrials().get(edition.getCurrentTrial())).getCreditNumber() * -1);
                }
                results[j] = r.toString();
                j++;
                results[j] = String.valueOf(players1[i].getPI());
                j++;
                results[j] = String.valueOf(types[i]);
                j++;
            }
        }
        else if(edition.getTrials().get(edition.getCurrentTrial()) instanceof TrialTesis){
            for (int i = 0; i < edition.getNumPlayers(); i++) {
                if (players1[i].getCounter() == -1) {
                    results[j] = players1[i].getName() + "*";
                } else {
                    results[j] = players1[i].getName();
                }
                j++;
                results[j] = String.valueOf(players1[i].getWin());
                j++;
                results[j] = String.valueOf(players1[i].getPI());
                j++;
                results[j] = String.valueOf(types[i]);
                j++;
            }
        }
        else {
            if (players1[0].getWin()) {
                results[j] = "1";
            } else {
                results[j] = "0";
            }
            j++;
            for (int i = 0; i < edition.getNumPlayers(); i++) {
                if (players1[i].getCounter() == -1) {
                    results[j] = players1[i].getName() + "*";
                } else {
                    results[j] = players1[i].getName();
                }
                j++;
                results[j] = String.valueOf(players1[i].getPI());
                j++;
                results[j] = String.valueOf(types[i]);
                j++;
            }
        }

        return results;
    }

    private Player[] executeTrial (TrialPublicacionArticulo trial, Player[] player) {

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
                                player[i].addPI(4);
                                break;
                            case '2':
                                player[i].addPI(3);
                                break;
                            case '3':
                               player[i].addPI(2);
                                break;
                            case '4':
                               player[i].addPI(1);
                                break;
                        }
                        player[i].setWin(true);
                        b = true;
                    }
                    else if (random.nextInt(trial.getProbDenegar()+trial.getProbRevision()) <= trial.getProbDenegar()) {
                        switch (quartilNum) {
                            case '1':
                                player[i].addPI(-5);
                                break;
                            case '2':
                                player[i].addPI(-4);
                                break;
                            case '3':
                                player[i].addPI(-3);
                                break;
                            case '4':
                                player[i].addPI(-2);
                                break;
                        }
                        player[i].setWin(false);
                        b = true;
                    }
                    else {
                        counter = counter + 1;
                    }
                }while (!b);
                player[i].setCounter(counter);
            }

            if(player[i].getType() != 2) {
                if(player[i].getPI() >= 10) {
                    player[i] = player[i].evolve();
                }
            }
        }

        return player;
    }

    private Player[] executeTrial (TrialMaster trial, Player[] player) {

        Random random = new Random();
        int[] aprobado = new int[player.length];

        for (int i = 0; i < player.length; i++) {
            aprobado[i] = 0;
            if(player[i].getPI() <= 0) {
                player[i].setCounter(-1);
            }
            else {
                player[i].setCounter(0);
            }
            for (int j = 0; j < trial.getCreditNumber(); j++) {
                if(random.nextInt(100) <= trial.getProbCredit()) {
                    aprobado[i]++;
                }
            }
            player[i].setECTS(aprobado[i]);
            if(aprobado[i] >= trial.getCreditNumber()-aprobado[i]) {
                switch (player[i].getType()) {
                    case 0 -> player[i].setPI(10);
                    case 1,2 -> player[i].addPI(3);
                }
            }
            else {
                player[i].addPI(-3);
            }

            if(player[i].getType() != 2) {
                if(player[i].getPI() >= 10) {
                   player[i] = player[i].evolve();
                }
            }
        }

        return player;
    }

    private Player[] executeTrial(TrialTesis trial, Player[] player){
        double d = 0;

        for (int i = 1; i == trial.getDificulty(); i++) {
            d += (2*i -1);
        }
        d = Math.sqrt(d);

        for (int i = 0; i < player.length; i++) {
            if(player[i].getPI() <= 0) {
                player[i].setCounter(-1);
            }
            else {
                player[i].setCounter(0);
            }
            if(player[i].getPI() > d){
                player[i].setWin(true);
                switch (player[i].getType()) {
                    case 1 -> player[i].setPI(10);
                    case 0,2 -> player[i].addPI(5);
                }
            }
            else{
                player[i].setWin(false);
                player[i].addPI(-5);
            }
            if(player[i].getType() != 2) {
                if (player[i].getPI() >= 10) {
                    player[i] = player[i].evolve();
                }
            }
        }
        return player;
    }

    private Player[] executeTrial(TrialBudget trial, Player[] player){
        double d = Math.log(trial.getBudget())/Math.log(2);
        int PI = 0;
        for (int i = 0; i < player.length; i++) {
            if(player[i].getPI() <= 0) {
                player[i].setCounter(-1);
            }
            else {
                player[i].setCounter(0);
            }
            PI += player[i].getPI();
        }
        if(PI > d){
            for (int i = 0; i < player.length; i++) {
                player[i].setWin(true);
                player[i].addPI(player[i].getPI()/2);
                if(player[i].getType() != 2) {
                    if (player[i].getPI() >= 10) {
                        player[i] = player[i].evolve();
                    }
                }
            }
        }
        else{
            for (int i = 0; i < player.length; i++) {
                player[i].setWin(false);
                player[i].addPI(-2);
            }
        }

        return player;
    }

    private Edition addEditionTrial(Edition edition) {
        ArrayList trials = new ArrayList();

        for (int i = 0; i < edition.getNumTrials(); i++) {
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i]);         //pillamos todos los datos de la prueba
            switch (prueba[0]) {
                case "1":
                    trials.add(trialManager.crearPruebaPublicacion(prueba[1], prueba[2], prueba[3], Integer.parseInt(prueba[4]),
                            Integer.parseInt(prueba[5]), Integer.parseInt(prueba[6]), false));
                    break;
                case "2":
                    trials.add(trialManager.crearTrialMaster(prueba[1], prueba[2], Integer.parseInt(prueba[3]),  Integer.parseInt(prueba[4]), false));
                    break;
                case "3":
                    trials.add(trialManager.crearTrialTesis(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false));
                    break;
                case "4":
                    trials.add(trialManager.crearTrialBudget(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false));
                    break;
            }
        }
        edition.setTrials(trials);
        return edition;
    }

    /**
     * Cargar la edicion actual
     * @return devolver la edicion
     */
    @Override
    public Edition loadCurrentEdition() {
        return editionManager.loadCurrentEdition();
    }

    /**
     * Revisar si se ha finalizado correctamente la edicion
     * @return devuelve si ha salido satisfactoriamente o no la ejecucion de la edicion
     */
    @Override
    public boolean checkEditionFinish() {
        Edition edition = loadCurrentEdition();
        if(edition.getNumTrials() == edition.getCurrentTrial()){
            return true;
        }
        return false;
    }

    /**
     * Vaciar archivo donde se guarda la edicion que se ejecuta
     */
    @Override
    public void clearCurrentEdition() {
        editionManager.clearCurrentEdition();
    }

    @Override
    public boolean checkTrialEdition(int numPrueba) {
        Edition[] editions = editionManager.getAll();

        for (Edition e: editions) {
            for (int i = 0; i < e.getNum().length; i++) {
                if(numPrueba == e.getNum()[i]) {
                    return true;
                }
            }
        }

        return false;
    }

    private Engineer[] savePlayers(String[] names) {
        Engineer[] players = new Engineer[names.length];

        for (int i = 0; i < names.length; i++) {
            players[i] = new Engineer(names[i]);
        }
        return players;
    }
}

