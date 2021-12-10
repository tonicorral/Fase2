package com.company.business;

public class PruebaPublicacionArticulo extends Prueba {
    private String nombreRevista;
    private String quartil;
    private int probAceptar;
    private int probRevision;
    private int probDenegar;

    public PruebaPublicacionArticulo(String nombre, String nombreRevista, String quartil, int probAceptar,
                                     int probRevision, int probDenegar) {
        super(nombre);
        this.nombreRevista = nombreRevista;
        this.quartil = quartil;
        this.probAceptar = probAceptar;
        this.probRevision = probRevision;
        this.probDenegar = probDenegar;
    }
}
