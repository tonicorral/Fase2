package com.company.business;

/**
 * Datos de la prueba tipo Master
 */
public class TrialMaster extends Trial{
    private String masterName;
    private int creditNumber;
    private int probCredit;

    /**
     * Acceder al nombre del master
     * @return devuelve el nombre del master
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Acceder a la probabilidad de aprobar el credito
     * @return devuelve la probabilidad de aprobar el credito
     */
    public int getProbCredit() {
        return probCredit;
    }

    /**
     * Acceder a los creditos del master
     * @return Devuelve los creditos
     */
    public int getCreditNumber() {
        return creditNumber;
    }

    /**
     * Constructor donde inicializamos las variables de la prueba tipo Master
     * @param nombre nombre de la prueba
     * @param masterName nombre del master
     * @param creditNumber numero de creditos del master
     * @param probCredit probabilidad de aprobar el master
     */
    public TrialMaster(String nombre, String masterName, int creditNumber, int probCredit) {
        super(nombre);
        this.masterName = masterName;
        this.creditNumber = creditNumber;
        this.probCredit = probCredit;

    }
}
