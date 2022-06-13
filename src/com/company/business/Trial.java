package com.company.business;

/**
 * Datos de la prueba
 */
public class Trial {
    private String nombre;

    /**
     * Constructor donde inicializamos el nombre de la prueba
     * @param nombre nombre de la prueba
     */
    public Trial(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Accedemos al nombre de la prueba
     * @return devolvemos el nombre de la prueba
     */
    public String getNombre() {
        return nombre;
    }
}
