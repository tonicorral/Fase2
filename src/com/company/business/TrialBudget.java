package com.company.business;

public class TrialBudget extends Trial{
    private String entity;
    private int budget;

    public TrialBudget(String nombre, String entity, int budget) {
        super(nombre);
        this.entity = entity;
        this.budget = budget;
    }

    public String getEntity() {
        return entity;
    }

    public int getBudget() {
        return budget;
    }
}
