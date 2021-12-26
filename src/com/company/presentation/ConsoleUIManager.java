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
        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
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


}


