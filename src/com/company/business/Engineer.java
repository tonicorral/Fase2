package com.company.business;

public class Engineer extends Player{
    public Engineer(String name, int PI) {
        super(name, PI);
    }
    public Engineer(String name) {
        super(name,5);
    }

    public Engineer(Player player) {
        super(player.getName(), player.getPI());
    }

    public int getType() {
        return 0;
    }

    /**
     * Sumar o restar la puntuacion que va consiguiendo el jugador
     * @param PI puntuacion del jugador
     */
    public void addPI(int PI) {
        setPI(getPI()+PI);
    }

    public Player evolve() {
        Master master = new Master(getName(), 5);
        master.setCounter(getCounter());
        master.setECTS(getECTS());
        master.setWin(getWin());
        return master;
    }
}
