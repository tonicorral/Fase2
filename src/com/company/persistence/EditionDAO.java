package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Player;

import javax.print.attribute.standard.JobKOctets;

/**
 * Interfaz de objeto de acceso a datos. Se declaran las funciones de las ediciones en la capa de pesistencia
 */
public interface EditionDAO {
    void save(Edition edition);
    Edition[] getAll();
    Edition get(int numEdition);
    boolean exitEdition(int numEdition);
    void delete(int numEdition);
    void saveCurrent(Edition edition, Object[] players, int currentTrial);
    String loadCurrent();
    void emptyCurrent();
}
