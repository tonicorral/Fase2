package com.company.persistence;

import com.company.business.Trial;
import com.company.business.TrialMaster;
import com.company.business.TrialPublicacionArticulo;

public interface TrialDAO {
    void save(TrialPublicacionArticulo trial);
    void save(TrialMaster trial);
    Trial[] getAll();
    TrialPublicacionArticulo getPublicacion(int numberTrial);
    TrialMaster getMaster(int numberTrial);
    boolean trialExit(int numPrueba);       //comprovar para ir para atras lista pruebas
    void delete(int numPrueba);
    int getType(int numPrueba);
}

