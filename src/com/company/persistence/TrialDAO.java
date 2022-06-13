package com.company.persistence;

import com.company.business.*;

/**
 * Interfaz de objeto de acceso a datos. Se declaran las funciones de las pruebas en la capa de pesistencia
 */
public interface TrialDAO {
    void save(TrialPublicacionArticulo trial);
    void save(TrialMaster trial);
    void save(TrialTesis trial);
    void save(TrialBudget trial);
    Trial[] getAll();
    TrialPublicacionArticulo getPublicacion(int numberTrial);
    TrialMaster getMaster(int numberTrial);
    TrialTesis getTesis(int numberTrial);
    TrialBudget getBudget(int numberTrial);
    boolean trialExit(int numPrueba);
    void delete(int numPrueba);
    int getType(int numPrueba);
}

