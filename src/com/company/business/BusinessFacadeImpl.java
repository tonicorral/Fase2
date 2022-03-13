package com.company.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class BusinessFacadeImpl implements BusinessFacade{
    private final TrialManager trialManager;
    private final EditionManager editionManager;
    private boolean pType;

    public BusinessFacadeImpl() {
        trialManager = new TrialManager();
        editionManager = new EditionManager();
    }
    @Override
    public void setPType(boolean pType){
        this.pType = pType;
    }

    @Override   //Le passa los datos al manager para crear la prueba
    public void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej) {
        trialManager.crearPruebaPublicacion(trialName, trialJournal, quartile, acc, rev, rej,true , pType);
    }
    public void createTrial(String trialName, String masterName, int credits, int prob) {
        trialManager.crearTrialMaster(trialName, masterName, credits, prob, true , pType);
    }
    public void createTrial(String trialName, String b, int num, boolean check) {
        if(check){
            trialManager.crearTrialTesis(trialName, b, num, true,  pType);
        }
        else{
            trialManager.crearTrialBudget(trialName, b, num, true , pType);
        }
    }

    @Override
    public String[] trialsNames() {

        return trialManager.listaPruebas(pType);
    }

    @Override
    public String[] trialInfo(int numberTrial) {

        return trialManager.infoPrueba(numberTrial, pType);
    }

    @Override
    public boolean trialExit(int numPrueba) {
        return trialManager.trialExit(numPrueba, pType);
    }

    @Override
    public void deleteTrial(int numPrueba) {
        trialManager.delete(numPrueba, pType);
    }

    @Override
    public void createEdition(int year, int numPlayers, int numTrials, int[] nums) {
        editionManager.crearEdition(year, numPlayers, numTrials, nums, pType);
    }

    @Override
    public int[] editionYear() {
        return editionManager.listaEdition(pType);
    }

    @Override
    public Edition addEditionTrial(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition, pType);
        ArrayList trials = new ArrayList();

        for (int i = 0; i < edition.getNumTrials(); i++) {
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i], pType);         //pillamos todos los datos de la prueba
            switch (prueba[0]) {
                case "1":
                    trials.add(trialManager.crearPruebaPublicacion(prueba[1], prueba[2], prueba[3], Integer.parseInt(prueba[4]),
                            Integer.parseInt(prueba[5]), Integer.parseInt(prueba[6]), false, pType));
                    break;
                case "2":
                    trials.add(trialManager.crearTrialMaster(prueba[1], prueba[2], Integer.parseInt(prueba[3]),  Integer.parseInt(prueba[4]), false, pType));
                    break;
                case "3":
                    trials.add(trialManager.crearTrialTesis(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false, pType));
                    break;
                case "4":
                    trials.add(trialManager.crearTrialBudget(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false, pType));
                    break;
            }

        }
        edition.setTrials(trials);
        return edition;
    }

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

    @Override
    public boolean exitEdition(int numEdition) {
        return editionManager.exitEdition(numEdition, pType);
    }

    @Override
    public int[] editionTrials(int numEdition) {
        Edition edition = editionManager.getEdition(numEdition, pType);
        return edition.getNum();
    }

    @Override
    public void deleteEdition(int numEdition) {
        editionManager.deleteEdition(numEdition, pType);
    }

    @Override
    public boolean checkCurrentEdition() {
        if(editionManager.getCurrentEdition(pType) == null) {
            return false;
        }
        return true;
    }

    @Override
    public int[] getCurrentEditionData() {
        Edition edition = editionManager.getCurrentEdition(pType);
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
        Player[] players1;
        String trial;
        int[] types = new int[edition.getNumPlayers()];

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

        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(pType), players1, edition.getCurrentTrial()+1, pType);

        return doEdition(edition, players1, trial, types);
    }

    /**
     *
     * @param players array de String con la informacion jugadores
     * @return devuelve una String con los resultados de una edicion
     */
    @Override
    public String[] startEdition(String[] players) {
        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(pType),savePlayers(players),0, pType);
        Edition edition = addEditionTrial(editionManager.loadCurrentEdition(pType));
        Player[] players0 = edition.getPlayers();
        Player[] players1;
        String trial;
        int[] types = new int[edition.getNumPlayers()];

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

        editionManager.saveCurrentEdition(editionManager.getCurrentEdition(pType), players1, 1, pType);

        return doEdition(edition, players1, trial, types);

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
                    else if (random.nextInt(trial.getProbDenegar()+trial.getProbRevision()) <= trial.getProbDenegar()) {
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
                    else {
                        counter = counter + 1;
                    }
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
                    case 1 -> player[i].addPI(3);
                    case 2 -> player[i].addPI(6);
                }
            }
            else {
                switch (player[i].getType()) {
                    case 0 -> player[i].addPI(-3);
                    case 1 -> player[i].addPI(-1);
                }
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
                switch (player[i].getType()) {
                    case 0 -> player[i].addPI(-5);
                    case 1 -> player[i].addPI(-2);
                }
            }
            if(player[i].getType() != 2) {
                if (player[i].getPI() >= 10) {
                    player[i].setType(player[i].getType() + 1);
                    player[i].setPI(5);
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
                if (player[i].getType() == 2){
                    player[i].addPI(player[i].getPI());
                }
                else{
                    player[i].addPI((int) Math.ceil(player[i].getPI()/2));
                }
                if(player[i].getType() != 2) {
                    if (player[i].getPI() >= 10) {
                        player[i].setType(player[i].getType() + 1);
                        player[i].setPI(5);
                    }
                }
            }
        }
        else{
            for (int i = 0; i < player.length; i++) {
                player[i].setWin(false);
                switch (player[i].getType()){
                    case 0 -> player[i].addPI(-2);
                    case 1 -> player[i].addPI(-1);
                }
            }
        }

        return player;
    }

    private Edition addEditionTrial(Edition edition) {
        ArrayList trials = new ArrayList();

        for (int i = 0; i < edition.getNumTrials(); i++) {
            String[] prueba = trialManager.infoPrueba(edition.getNum()[i], pType);         //pillamos todos los datos de la prueba
            switch (prueba[0]) {
                case "1":
                    trials.add(trialManager.crearPruebaPublicacion(prueba[1], prueba[2], prueba[3], Integer.parseInt(prueba[4]),
                            Integer.parseInt(prueba[5]), Integer.parseInt(prueba[6]), false, pType));
                    break;
                case "2":
                    trials.add(trialManager.crearTrialMaster(prueba[1], prueba[2], Integer.parseInt(prueba[3]),  Integer.parseInt(prueba[4]), false, pType));
                    break;
                case "3":
                    trials.add(trialManager.crearTrialTesis(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false, pType));
                    break;
                case "4":
                    trials.add(trialManager.crearTrialBudget(prueba[1], prueba[2], Integer.parseInt(prueba[3]), false, pType));
                    break;
            }
        }
        edition.setTrials(trials);
        return edition;
    }
    @Override
    public Edition loadCurrentEdition() {
        return editionManager.loadCurrentEdition(pType);
    }

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
        editionManager.clearCurrentEdition(pType);
    }

    private Player[] savePlayers(String[] names) {
        Player[] players = new Player[names.length];

        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }
        return players;
    }
}
