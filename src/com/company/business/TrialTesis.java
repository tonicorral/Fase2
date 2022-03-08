package com.company.business;

public class TrialTesis extends Trial{
    private String study;
    private int dificulty;

    public TrialTesis(String nombre, String study, int dificulty) {
        super(nombre);
        this.study = study;
        this.dificulty = dificulty;
    }

    public String getStudy() {
        return study;
    }

    public int getDificulty() {
        return dificulty;
    }

}
