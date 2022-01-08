package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;

import java.util.ArrayList;
import java.util.Calendar;

public class EditionManager {
    private EditionDAO editionDAO;

    public EditionManager() {
        editionDAO = new EditionDAOcsv("data/edition.csv", "data/currentEdition.csv");
    }

    public void crearEdition(int year, int numPlayers, int numTrials, int[] nums) {

        Edition edition = new Edition(year, numPlayers,numTrials, nums);
        editionDAO.save(edition);
    }

    public int[] listaEdition() {
        Edition[] editions = editionDAO.getAll();
        int[] names = new int[editions.length];

        for (int i = 0; i < editions.length; i++) {
            names[i] = editions[i].getYear();
        }
        return names;
    }

    public Edition getEdition(int numEdition) {
        return editionDAO.get(numEdition);
    }

    public boolean exitEdition (int numEdition){
        return editionDAO.exitEdition(numEdition);
    }

    public void deleteEdition(int numEdition) {
        editionDAO.delete(numEdition);
    }

    public Edition getCurrentEdition() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int[] years = listaEdition();
        Edition editionCurrent = null;

        for (int i = 0; i < years.length; i++) {
            if (year == years[i]) {
                editionCurrent = getEdition(i+1);      //la guardamos
            }
        }
        return editionCurrent;
    }

    public void saveCurrentEdition(Edition edition, Player[] players, int currentTrial) {
        editionDAO.saveCurrent(edition, players, currentTrial);
    }
    public Edition loadCurrentEdition(){
        String edition = editionDAO.loadCurrent();
        if(edition != null){
            String[] data = edition.split(",");
            int year = Integer.parseInt(data[0]);
            if(year == Calendar.getInstance().get(Calendar.YEAR)) {
                int numPlayers = Integer.parseInt(data[1]);
                Player[] players = new Player[numPlayers];
                int j = 2;
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(data[j], Integer.parseInt(data[j+1]));
                    j = j+2;
                }
                String[] b = data[data.length - 1].split(";");
                int currentTrial = Integer.parseInt(b[0]);
                Edition edition1 = getCurrentEdition();

                edition1.setCurrentTrial(currentTrial);
                edition1.setPlayers(players);

                return edition1;
            }
        }
        return null;
    }


}

