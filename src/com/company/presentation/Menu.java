package com.company.presentation;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean exit;

    public Menu() {
        this.scanner = new Scanner(System.in);      //crear scanner para leer lo que ponga el usuario
        this.exit = false;
    }

    public void mostrarRoles() {
        System.out.println("Welcome to The Trials. Who are you?\n");
        System.out.println("    A) The Composer");
        System.out.println("    B) This year's Conductor\n");
        System.out.println("Enter a role:");
    }

    public String entrarInfo() {
        String datos = this.scanner.nextLine();

        return datos;
    }

    public void opcionesCompositor() {
        System.out.println("Entering managment mode...\n");
        System.out.println("    1) Manage Trials");
        System.out.println("    2) Manage Editions\n");
        System.out.println("    3) Exit\n");
        System.out.println("Enter an option:");
    }

    public void opcionesConductor() {
        System.out.println("opcionesConductor");
    }

    public void ejecutarMenu() {
        while (!exit) {
            mostrarRoles();

            switch (entrarInfo()) {
                case "A":
                    opcionesCompositor();
                    break;
                case "B":
                    opcionesConductor();
                    break;

                default:
                    System.out.println("Please enter a valid option");
            }
        }
    }

}
