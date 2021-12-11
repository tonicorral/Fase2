package com.company.business;

public class TrialPublicacionArticulo extends Trial {
    private String nombreRevista;
    private String quartil;
    private int probAceptar;
    private int probRevision;
    private int probDenegar;

    public TrialPublicacionArticulo(String nombre, String nombreRevista, String quartil, int probAceptar,
                                    int probRevision, int probDenegar) {
        super(nombre);
        this.nombreRevista = nombreRevista;
        this.quartil = quartil;
        this.probAceptar = probAceptar;
        this.probRevision = probRevision;
        this.probDenegar = probDenegar;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public String getQuartil() {
        return quartil;
    }

    public int getProbAceptar() {
        return probAceptar;
    }

    public int getProbRevision() {
        return probRevision;
    }

    public int getProbDenegar() {
        return probDenegar;
    }
}
