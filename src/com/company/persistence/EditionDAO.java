package com.company.persistence;

import com.company.business.Edition;

public interface EditionDAO {
    void save(Edition edition);
    Edition[] getAll();
    Edition get(int numEdition);
    boolean exitEdition(int numEdition);
    void delete(int numEdition);

}
