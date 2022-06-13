package com.company.business;

public class Doctor extends Player{
    public Doctor(String name, int PI) {
        super(name, PI);
    }

    public Doctor(Player player) {
        super(player.getName(), player.getPI());
    }

    public int getType() {
        return 2;
    }

    /**
     * Sumar o restar la puntuacion que va consiguiendo el jugador
     * @param PI puntuacion del jugador
     */
    public void addPI(int PI) {
        if(PI > 0) {
            setPI(getPI()+PI*2);
        }
        else {
            setPI(getPI()+(PI/2));
        }
    }
}
