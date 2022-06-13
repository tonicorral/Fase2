package com.company.business;

/**
 * Datos de los jugadores
 */
public class Player {
    private String name;
    private int PI;
    private int counter;
    private boolean win;
    private int ECTS;
    private int type;

    /**
     * Acceder al contador de jugadores
     * @return devuelve la cantidad de jugadores que habra
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Fijamos el valor de la variable counter
     * @param counter variable para ir contando el numero de jugadores
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Acceder a la variable win
     * @return devuelve si los jugadores han ganado o perdido
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Fijamos un valor a win
     * @param win indicar si los jugadores han ganado o perdido
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Fijamos el valor de los ECTS
     * @param ECTS creditos del master
     */
    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    /**
     * Accedemos a la variable ECTS
     * @return devolvemos ECTS del jugador
     */
    public int getECTS() {
        return ECTS;
    }

    /**
     * Constructor donde inicializamos los datos del jugador
     * @param name nombre de los jugadores
     */
    public Player(String name, int PI) {
        this.name = name;
        this.PI = PI;
        this.ECTS = 0;
    }

    /**
     * Fijamos el valor de PI
     * @param PI puntuacion que ha conseguido el jugador
     */
    public void setPI(int PI) {
        this.PI = PI;
    }

    /**
     * Sumar o restar la puntuacion que va consiguiendo el jugador
     * @param PI puntuacion del jugador
     */
    public void addPI(int PI) {
        this.PI = this.PI + PI;
    }

    /**
     * Acceder al nombre del juagador
     * @return devuelve el nombre del jugador
     */
    public String getName() {
        return name;
    }

    public Player evolve() {
        return null;
    }

    /**
     * Accedemos a la puntuacion del jugador
     * @return devolvemos la puntuacion
     */
    public int getPI() {
        return PI;
    }

    public int getType() {
        return type;
    }
}
