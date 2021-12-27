package com.company.persistence;

import com.company.business.Trial;
import com.company.business.TrialPublicacionArticulo;

public interface TrialDAO {
    void save(TrialPublicacionArticulo trial);
    Trial[] getAll();
    TrialPublicacionArticulo get(int numberTrial);
    boolean trialExit(int numPrueba);       //comprovar para ir para atras lista pruebas
    void delete(int numPrueba);
}

