package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;
import com.company.persistence.EditionDAOjson;

import java.util.Calendar;

public class EditionManager {
    private EditionDAO editionDAOcsv;
    private EditionDAO editionDAOjson;

    public EditionManager() {
        editionDAOcsv = new EditionDAOcsv("data/edition.csv", "data/currentEdition.csv");
        editionDAOjson = new EditionDAOjson("data/edition.json", "data/currentEdition.json");
    }

    public void crearEdition(int year, int numPlayers, int numTrials, int[] nums, boolean pType) {

        Edition edition = new Edition(year, numPlayers,numTrials, nums);
        if(!pType) { editionDAOcsv.save(edition); }
        else { editionDAOjson.save(edition);}

    }

    public int[] listaEdition(boolean pType) {
        Edition[] editions;

        if(!pType) { editions = editionDAOcsv.getAll(); }
        else { editions = editionDAOjson.getAll();}

        int[] names = new int[editions.length];
        for (int i = 0; i < editions.length; i++) {
            names[i] = editions[i].getYear();
        }
        return names;
    }

    public Edition getEdition(int numEdition, boolean pType) {

        if(!pType) { return editionDAOcsv.get(numEdition); }
        else { return editionDAOjson.get(numEdition);}

    }

    public boolean exitEdition (int numEdition, boolean pType){

        if(!pType) {  return editionDAOcsv.exitEdition(numEdition);}
        else {  return editionDAOjson.exitEdition(numEdition);}

    }

    public void deleteEdition(int numEdition, boolean pType) {

        if(!pType) {  editionDAOcsv.delete(numEdition);}
        else {  editionDAOjson.delete(numEdition);}

    }

    public Edition getCurrentEdition(boolean pType) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int[] years = listaEdition(pType);
        Edition editionCurrent = null;

        for (int i = 0; i < years.length; i++) {
            if (year == years[i]) {
                editionCurrent = getEdition(i+1, pType);      //la guardamos
            }
        }
        return editionCurrent;
    }

    public void saveCurrentEdition(Edition edition, Player[] players, int currentTrial, boolean pType) {

        if(!pType) {   editionDAOcsv.saveCurrent(edition, players, currentTrial);}
        else {  editionDAOjson.saveCurrent(edition, players, currentTrial);}

    }

    public Edition loadCurrentEdition(boolean pType){
        String edition;

        if(!pType) {   edition = editionDAOcsv.loadCurrent();}
        else {
            edition = editionDAOjson.loadCurrent();
            System.out.println("json");
        }

        if(edition != null){
            String[] data = edition.split(",");
            int year = Integer.parseInt(data[0]);
            if(year == Calendar.getInstance().get(Calendar.YEAR)) {
                int numPlayers = Integer.parseInt(data[1]);
                Player[] players = new Player[numPlayers];
                int j = 2;

                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(data[j], Integer.parseInt(data[j+1]), Integer.parseInt(data[j+2]));
                    j = j+3;
                }
                String[] b = data[data.length - 1].split(";");
                int currentTrial = Integer.parseInt(b[0]);
                Edition edition1 = getCurrentEdition(pType);
                edition1.setCurrentTrial(currentTrial);
                edition1.setPlayers(players);

                return edition1;
            }
        }
        return null;
    }

    public void clearCurrentEdition(boolean pType) {
        if(!pType) {  editionDAOcsv.emptyCurrent();}
        else { editionDAOjson.emptyCurrent();}
    }
}

