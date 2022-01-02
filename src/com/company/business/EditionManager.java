package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;

import java.util.ArrayList;

public class EditionManager {
    EditionDAO editionDAO;

    public EditionManager() {
        editionDAO = new EditionDAOcsv("data/edition.csv");
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

}

