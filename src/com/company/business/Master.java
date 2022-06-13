package com.company.business;

public class Master extends Player{
    public Master(String name, int PI) {
        super(name, PI);
    }

    public Master(Player player) {
        super(player.getName(), player.getPI());
    }

    public int getType() {
        return 1;
    }

    /**
     * Sumar o restar la puntuacion que va consiguiendo el jugador
     * @param PI puntuacion del jugador
     */
    public void addPI(int PI) {
       if(PI > 0) {
           setPI(getPI()+PI);
       }
       else {
           setPI(getPI()+(PI/2));
       }
    }

    public Player evolve() {
        Doctor doctor = new Doctor(getName(), 5);
        doctor.setCounter(getCounter());
        doctor.setECTS(getECTS());
        doctor.setWin(getWin());
        return doctor;
    }
}

