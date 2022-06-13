package com.company.business;

/**
 * Datos de la prueba tipo Budget
 */
public class TrialBudget extends Trial{
    private String entity;
    private int budget;

    /**
     * Constructor donde inicializamos las variables de budget
     * @param nombre nombre de la prueba
     * @param entity nombre de la entidad que se le pide presupuesto
     * @param budget cantidad de presupuesto
     */
    public TrialBudget(String nombre, String entity, int budget) {
        super(nombre);
        this.entity = entity;
        this.budget = budget;
    }

    /**
     * Accedemos a la entidad
     * @return devuelve la entidad
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Accedemos al presupuesto
     * @return devuelve el presupuesto
     */
    public int getBudget() {
        return budget;
    }
}
