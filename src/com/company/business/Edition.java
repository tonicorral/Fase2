package com.company.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Datos de Edicion
 */
public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList trials;
    private int[] num;
    private Player[] players;
    private  int currentTrial;

    /**
     * Acceder a los jugadores
     * @return devuelve los jugadores
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Acceder a la prueba actual
     * @return devolver la prueba actual
     */
    public int getCurrentTrial() {
        return currentTrial;
    }

    /**
     * Fijar el valor de los jugadores
     * @param players array de los juagadores
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Fijar el valor de la prueba actual
     * @param currentTrial prueba actual
     */
    public void setCurrentTrial(int currentTrial) {
        this.currentTrial = currentTrial;
    }

    /**
     * Fijar el valor de las pruebas
     * @param trials arrayList de las pruebas
     */
    public void setTrials(ArrayList trials) {
        this.trials = trials;
    }

    /**
     * Constructor de las ediciones
     * @param year año de la edicion
     * @param numPlayers numero de jugadores
     * @param numTrials numero de pruebas
     * @param num numero de la edicion
     */
    public Edition(int year, int numPlayers, int numTrials, int[] num) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.num = num;
        this.trials = new ArrayList();
    }

    /**
     * Acceder a las pruebas
     * @return devuelve una arrayList de las pruebas
     */
    public ArrayList getTrials() {
        return trials;
    }

    /**
     * Acceder al numero de la edicion
     * @return devolver el nuemro de la edicon que se desea
     */
    public int[] getNum() {
        return num;
    }

    /**
     * Acceder al año de la edicion
     * @return devolver el año de la edicion
     */
    public int getYear() {
        return year;
    }

    /**
     * Acceder a los numeros de jugadores de la edicion
     * @return devolver el numero de jugadores que hay en la edicion
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Accede al numero de pruebas
     * @return devuelve el numero de pruebas
     */
    public int getNumTrials() {
        return numTrials;
    }
}
