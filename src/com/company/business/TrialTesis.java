package com.company.business;

/**
 * Datos de la prueba tipo Tesis
 */
public class TrialTesis extends Trial{
    private String study;
    private int dificulty;

    /**
     * Constructor donde inicializamos las variables de la prueba tesis
     * @param nombre nombre de la prueba
     * @param study campo de estudio
     * @param dificulty dificultad de la defensa
     */
    public TrialTesis(String nombre, String study, int dificulty) {
        super(nombre);
        this.study = study;
        this.dificulty = dificulty;
    }

    /**
     * Acceder al campo de estudio
     * @return devuelve el campo de estudio
     */
    public String getStudy() {
        return study;
    }

    /**
     * Acceder a la dificultad de defensa
     * @return devuelve la dificultad de defensa
     */
    public int getDificulty() {
        return dificulty;
    }

}
