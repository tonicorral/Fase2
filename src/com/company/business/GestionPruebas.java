package com.company.business;

import java.util.ArrayList;

public class GestionPruebas {

    ArrayList<Prueba> pruebas;

    public GestionPruebas() {
        this.pruebas = new ArrayList<>();
    }

    public void crearPruebaPublicacion(String nombrePrueba, String nombreRevista, String quartil, int probAceptar,
                                       int probRevision, int probDenegar) {

        PruebaPublicacionArticulo prueba = new PruebaPublicacionArticulo(nombrePrueba, nombreRevista, quartil, probAceptar,
                probRevision, probDenegar);

        pruebas.add(prueba);       //añadimos la prueba a la lista de pruebas
    }

    public void listaPruebas() {
        int num = 1;

        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
        for (Prueba prueba: pruebas) {
            System.out.println(num+") "+prueba.getNombre());

            num++;
        }
        System.out.println("\n"+num+") Back");
        System.out.println("\nEnter an option:");

    }
}
