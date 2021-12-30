package com.company.presentation;

import java.util.Scanner;

public class ConsoleUIManager implements UIManager {
    private Scanner scanner;


    public ConsoleUIManager() {
        this.scanner = new Scanner(System.in);      //crear scanner para leer lo que ponga el usuario
    }
    @Override
    public MenuOptions showRoles() {
        while (true) {
            System.out.println("Welcome to The Trials. Who are you?\n");
            System.out.println("    A) The Composer");
            System.out.println("    B) This year's Conductor\n");
            System.out.println("Enter a role:");

            try {
                String datos = this.scanner.nextLine();
                switch (datos) {
                    case "A":
                        return MenuOptions.SELECT_COMPOSITOR;

                    case "B":
                        return MenuOptions.SELECT_CONDUCTOR;

                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please select a value option");
            }
        }
    }

    @Override
    public MenuOptions opcionesCompositor() {
        System.out.println("Entering managment mode...\n");
        System.out.println("    1) Manage Trials");
        System.out.println("    2) Manage Editions\n");
        System.out.println("    3) Exit\n");
        System.out.println("Enter an option:");
        try {
            int data = Integer.parseInt(this.scanner.nextLine());
            switch (data) {
                case 1:
                    return MenuOptions.MANAGE_TRIALS;

                case 2:
                    return MenuOptions.MANAGE_EDITIONS;

                case 3:
                    return MenuOptions.EXIT;
                default:
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please select a value option");
        }
        return null;
    }

    @Override
    public MenuOptions menuTrials() {
        System.out.println("\ta) Create Trial");
        System.out.println("\tb) List Trials");
        System.out.println("\tc) Delete Trial");
        System.out.println("\td) Back");
        System.out.println("\nEnter an option!");
        try {
            String data = this.scanner.nextLine();
            switch (data) {
                case "a":
                    return MenuOptions.CREATE_TRIALS;

                case "b":
                    return MenuOptions.LIST_TRIALS;

                case "c":
                    return MenuOptions.DELETE_TRIALS;

                case "d":
                    return MenuOptions.EXIT;
                default:
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please select a value option");
        }
        return null;
    }

    @Override
    public int showTrialsTypes() {
        System.out.println("--- Trial types ---");
        System.out.println("1) Paper publication");
        System.out.println("Enter the trial’s type:");

        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public String askTrialName() {
        System.out.println("Enter the trial’s name:");

        return scanner.nextLine();
    }

    @Override
    public String askJournalName() {
        System.out.println("Enter the journal's name:");

        return scanner.nextLine();
    }

    @Override
    public String askQuartile() {
        System.out.println("Enter the journal’s quartile:");
        return scanner.nextLine();
    }

    @Override
    public int askAccessProb() {
        System.out.println("Enter the acceptance probability:");
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askRevProb() {
        System.out.println("Enter the revision probability:");
        return  Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askRejProb() {
        System.out.println("Enter the rejection probability:");
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int showTrialsName(String[] names) {
        int i;
        for (i = 0; i < names.length; i++) {
            System.out.println((i+1)+") "+names[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");
        System.out.println("Enter an option: ");

        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public void showTrialData(String[] data) {
        System.out.println("\nTrial: "+ data[0]+ " (Paper Publication)");
        System.out.println("Journal: "+ data[1]+ " ("+ data[2]+ ")");
        System.out.println("Chances: "+ data[3]+ "% acceptance, "+ data[4] +"% revision, " + data[5]+ "% rejection");
    }

    @Override
    public void listTrialsText() {
        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
    }

    @Override
    public void deleteTrialsText() {
        System.out.println("Which trial do you want to delete?\n");
    }

    @Override
    public boolean deleteConfirmation(int numPrueba, String[] names) {
        System.out.println("\nEnter the trial’s name for confirmation:");
        String name = scanner.nextLine();
        return name.equalsIgnoreCase(names[numPrueba - 1]);
    }

    @Override
    public void deleteOK() {
        System.out.println("\nThe trial was successfully deleted.");
    }

    @Override
    public MenuOptions menuEditions() {
        System.out.println("\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\td) Delete Edition\n");
        System.out.println("\te) Back");
        System.out.println("\nEnter an option!");
        try {
            String data = this.scanner.nextLine();
            switch (data) {
                case "a":
                    return MenuOptions.CREATE_EDITION;

                case "b":
                    return MenuOptions.LIST_EDITIONS;

                case "c":
                    return MenuOptions.DUPLICATE_EDITION;

                case "d":
                    return MenuOptions.DELETE_EDITION;

                case "e":
                    return MenuOptions.EXIT;
                default:
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please select a value option");
        }
        return null;

    }

    @Override
    public int askEditionYear() {
        System.out.println("Enter the edition’s year:");
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askEditionPlayers() {
        System.out.println("Enter the initial number of players:");
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askEditionNumberTrials() {
        System.out.println("Enter the number of trials:");
        return Integer.valueOf(scanner.nextLine());
    }

    //TODO Comprovar que hay al menos 3 pruebas creadas
    @Override
    public int[] pickEditionTrials(String[] nameTrial, int numPruebas) {
        int i;
        System.out.println("\n\t--- Trials ---\n");
        for (i = 0; i < nameTrial.length; i++) {
            System.out.println((i+1)+") "+nameTrial[i]);
        }

        int[] nums = new int[numPruebas];
        for (int j = 0; j < numPruebas; j++) {
            System.out.println("Pick a trial ("+(j+1)+"/"+numPruebas+"):");
            nums[j] = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("\nThe edition was created successfully!");
        return nums;
    }

    @Override
    public int showEditionYears(int[] years) {
        int i;
        System.out.println("Here are the current editions, do you want to see more details or go back?\n");
        for (i = 0; i < years.length; i++) {
            System.out.println((i+1)+") The Trials "+years[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");
        System.out.println("Enter an option: ");

        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public void showEditionData(String[] info) {
        System.out.println("Year: "+info[0]);
        System.out.println("Players: "+info[1]);
        System.out.println("Trials:");
        for (int i = 0; i < Integer.parseInt(info[2]); i++) {
            System.out.println("\t"+(i+1)+"- "+info[3+i]+" (Paper publication)");
        }
    }


}


