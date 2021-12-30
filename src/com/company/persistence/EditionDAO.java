package com.company.persistence;

import com.company.business.Edition;

public interface EditionDAO {
    void save(Edition edition, int[] nums);
    Edition[] getAll();
    Edition get(int numEdition);
}
