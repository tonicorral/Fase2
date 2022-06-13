package com.company.business;

/**
 * Datos de la prueba tipo Publicacion Articulo
 */
public class TrialPublicacionArticulo extends Trial {
    private String nombreRevista;
    private String quartil;
    private int probAceptar;
    private int probRevision;
    private int probDenegar;

    /**
     * Constructor donde inicializamos las variables de la prueba tipo Publicacion Articulo
     * @param nombre nombre de la prueba
     * @param nombreRevista nombre de la revista
     * @param quartil dificultad
     * @param probAceptar probabildiad de aceptar
     * @param probRevision probabilidad de revisar
     * @param probDenegar probabilidad de denegar
     */
    public TrialPublicacionArticulo(String nombre, String nombreRevista, String quartil, int probAceptar,
                                    int probRevision, int probDenegar) {
        super(nombre);
        this.nombreRevista = nombreRevista;
        this.quartil = quartil;
        this.probAceptar = probAceptar;
        this.probRevision = probRevision;
        this.probDenegar = probDenegar;
    }

    /**
     * Acceder al nombre de la revista
     * @return devuelve el nombre de la revista
     */
    public String getNombreRevista() {
        return nombreRevista;
    }

    /**
     * Accede al grado de dificultad
     * @return devuelve el grado de dificultad
     */
    public String getQuartil() {
        return quartil;
    }

    /**
     * Accedemos a la probabilidad de aceptar
     * @return devuelve la probabildad de acpetar
     */
    public int getProbAceptar() {
        return probAceptar;
    }

    /**
     * Accedemos a la probabilidad de revisar
     * @return devuelve la probabilidad de revisar
     */
    public int getProbRevision() {
        return probRevision;
    }

    /**
     * Accedemos a la probabilidad de denegar
     * @return devuelve la probabildad de denegar
     */
    public int getProbDenegar() {
        return probDenegar;
    }
}
