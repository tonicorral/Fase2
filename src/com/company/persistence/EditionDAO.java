package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Player;

public interface EditionDAO {
    void save(Edition edition);
    Edition[] getAll();
    Edition get(int numEdition);
    boolean exitEdition(int numEdition);
    void delete(int numEdition);
    void saveCurrent(Edition edition, Player[] players, int currentTrial);
    String loadCurrent();
    void emptyCurrent();

}
