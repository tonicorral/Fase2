package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;

public class EditionManager {
    EditionDAO editionDAO;

    public EditionManager() {
        editionDAO = new EditionDAOcsv();
    }
}
