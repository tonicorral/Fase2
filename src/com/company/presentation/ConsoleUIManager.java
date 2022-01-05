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

            try {
                String datos = askString("Enter a role:");
                switch (datos) {
                    case "A","a":
                        return MenuOptions.SELECT_COMPOSITOR;

                    case "B","b":
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
        try {
            int data = askInt("Enter an option:");
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
        try {
            String data = askString("\nEnter an option!");
            switch (data) {
                case "a","A":
                    return MenuOptions.CREATE_TRIALS;

                case "b","B":
                    return MenuOptions.LIST_TRIALS;

                case "c","C":
                    return MenuOptions.DELETE_TRIALS;

                case "d","D":
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

        return askInt("Enter the trial’s type:");
    }

    @Override
    public int showTrialsName(String[] names) {
        int i;
        for (i = 0; i < names.length; i++) {
            System.out.println((i+1)+") "+names[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");

        return askInt("Enter an option: ");
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
        String name = askString("\nEnter the trial’s name for confirmation:");
        return name.equalsIgnoreCase(names[numPrueba - 1]);
    }

    @Override
    public void deleteOK() {
        System.out.println("\nThe trial was successfully deleted.\n");
    }

    @Override
    public MenuOptions menuEditions() {
        System.out.println("\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\td) Delete Edition\n");
        System.out.println("\te) Back");
        try {
            String data = askString("\nEnter an option!");
            switch (data) {
                case "a","A":
                    return MenuOptions.CREATE_EDITION;

                case "b","B":
                    return MenuOptions.LIST_EDITIONS;

                case "c","C":
                    return MenuOptions.DUPLICATE_EDITION;

                case "d","D":
                    return MenuOptions.DELETE_EDITION;

                case "e","E":
                    return MenuOptions.EXIT;
                default:
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please select a value option");
        }
        return null;

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
            nums[j] = askInt();
        }

        System.out.println("\nThe edition was created successfully!\n");
        return nums;
    }

    @Override
    public int showEditionYears(int[] years) {
        int i;

        for (i = 0; i < years.length; i++) {
            System.out.println((i+1)+") The Trials "+years[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");

        return askInt("Enter an option: ");
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

    @Override
    public void showEditionMessage(MenuOptions option) {
        switch (option){
            case LIST_EDITIONS:
                System.out.println("Here are the current editions, do you want to see more details or go back?\n");
            break;
            case DUPLICATE_EDITION:
                System.out.println("Which edition do you want to clone?\n");
            break;
            case DELETE_EDITION:
                System.out.println("Which edition do you want to delete?\n");
            break;

        }
    }

    @Override
    public boolean askDeleteYear(int editionYear) {
        int year = askInt("Enter the edition’s year for confirmation:");
        if(year == editionYear){
            return true;
        }
         return false;
    }

    @Override
    public String askString(String text) {
        System.out.println(text);
        do {
            String info = scanner.nextLine();

            //comprovar que no esta vacia
            if(!info.equals("")) {
                return info;
            }
            System.out.println("Enter a valid text!");

        }while(true);
    }

    @Override
    public int askInt(String text) {
        System.out.println(text);
        do {
            String info = scanner.nextLine();
            try {
                return Integer.parseInt(info);
            }
            catch(NumberFormatException e) {
                System.out.println("Must write a number!");
            }
        }while(true);
    }

    @Override
    public int askInt() {
        do {
            String info = scanner.nextLine();
            try {
                return Integer.parseInt(info);
            }
            catch(NumberFormatException e) {
                System.out.println("Must write a number!");
            }
        }while(true);
    }

    @Override
    public int askInt(String text, int min, int max) {
        System.out.println(text);
        do {
            String info = scanner.nextLine();
            try {
                int num = Integer.parseInt(info);
                if(num >= min && num <= max) {
                    return num;
                }
                System.out.println("Enter a number between "+min+" and "+max+"!");
            }
            catch(NumberFormatException e) {
                System.out.println("Must write a number!");
            }
        }while(true);
    }

    @Override
    public void noCurrentEdition(int year) {
        System.out.println("\nEntering execution mode...\n");
        System.out.println("No edition is defined for the current year ("+year+").\n");
        System.out.println("Shutting down...");
    }

    @Override
    public String[] askPlayers(int year, int numPlayers) {
        System.out.println("Entering execution mode...\n");
        System.out.println("--- TRIALS "+year+" ---\n");
        String[] names = new String[numPlayers];

        for (int i = 0; i <numPlayers; i++) {
            names[i] = askString("Enter the player's name ("+(i+1)+"/"+numPlayers+"):");
        }

        return names;
    }


}


