package com.company.presentation;

import java.util.Scanner;

/**
 * Mostrar por pantalla los diferentes mensajes del programa
 */
public class ConsoleUIManager implements UIManager {
    private Scanner scanner;

    //TRIALS
    public static final String askTrialName = "Enter the trial’s name:";
    public static final String askTrialJournal = "Enter the journal's name:";
    public static final String askTrialQuartile = "Enter the journal’s quartile:";
    public static final String askAccessProb = "Enter the acceptance probability:";
    public static final String askRevProb = "Enter the revision probability:";
    public static final String askRejProb = "Enter the rejection probability:";
    public static final String askMaster = "Enter the master's name:";
    public static final String askCredits = "Enter the master's ECTS number:";
    public static final String askProbCredit = "Enter the credit pass probability:";
    public static final String askStudy = "Enter the thesis field of study:";
    public static final String askDificulty = "Enter the defense difficulty:";
    public static final String askEntity = "Enter the entity’s name:";
    public static final String askBudget = "Enter the budget amount:";

    //EDITIONS
    public static final String askEditionYear = "Enter the edition’s year:";
    public static final String askEditionPlayers = "Enter the initial number of players:";
    public static final String askEditionNumberTrials = "Enter the number of trials:";
    public static final String askDuplicateYear = "Enter the new edition’s year:";
    public static final String askDuplicateNumPlayers = "Enter the new edition’s initial number of players:";

    /**
     * Crear scanner para leer lo que ponga el usuario
     */
    public ConsoleUIManager() {
        this.scanner = new Scanner(System.in);      //crear scanner para leer lo que ponga el usuario
    }

    /**
     * Elegir el rol que desee el usuario
     * @return Seleccionar entre el compositor o el conductor
     */
    @Override
    public MenuOptions showRoles() {
        while (true) {
            System.out.println("\nWelcome to The Trials. Who are you?\n");
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

    /**
     * Opciones del rol compositor
     * @return Elegir entre manejar las pruebas o las ediciones
     */
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

    /**
     * Menu de controlar las pruebas
     * @return Elegir entre las diferentes acciones posibles: crear, lista, eliminar o volver a elegir rol
     */
    @Override
    public MenuOptions menuTrials() {
        do{
            System.out.println("\ta) Create Trial");
            System.out.println("\tb) List Trials");
            System.out.println("\tc) Delete Trial");
            System.out.println("\td) Back");
            try {
                String data = askString("\nEnter an option!");
                switch (data) {
                    case "a", "A":
                        return MenuOptions.CREATE_TRIALS;

                    case "b", "B":
                        return MenuOptions.LIST_TRIALS;

                    case "c", "C":
                        return MenuOptions.DELETE_TRIALS;

                    case "d", "D":
                        return MenuOptions.EXIT;
                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please select a valid option");
            }
        }while(true);
    }

    /**
     * Elegir el tipo de prueba que queremos tratar
     * @return Devolver el tipo de pruba que ha deseado el usuario: Paper publication, Master studies, Doctoral thesis defense o Budget request
     */
    @Override
    public int askTrialsTypes() {
        System.out.println("--- Trial types ---");
        System.out.println("1) Paper publication");
        System.out.println("2) Master studies");
        System.out.println("3) Doctoral thesis defense");
        System.out.println("4) Budget request\n");

        return askInt("Enter the trial’s type:", 1, 4);
    }

    /**
     * Mostar el nombre de la prueba
     * @param names String con el nombre que elegido el usuario
     * @return devuelve la opcion elegida por el usuario
     */
    @Override
    public int showTrialsName(String[] names) {
        int i;
        for (i = 0; i < names.length; i++) {
            System.out.println((i+1)+") "+names[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");

        return askInt("Enter an option: ");
    }

    /**
     * Mostrar toda la informacion de la prueba escrita por el usuario
     * @param data guardar las diferentes informaciones segun el tipo de prueba
     */
    @Override
    public void showTrialData(String[] data) {

        switch (data[0]) {
            case "1":
                System.out.println("\nTrial: "+ data[1]+ " (Paper Publication)");
                System.out.println("Journal: "+ data[2]+ " ("+ data[3]+ ")");
                System.out.println("Chances: "+ data[4]+ "% acceptance, "+ data[5] +"% revision, " + data[6]+ "% rejection");
                break;
            case "2":
                System.out.println("\nTrial: "+ data[1]+ " (Master studies)");
                System.out.println("Master: "+ data[2]);
                System.out.println("ECTS: "+data[3]+", with a "+data[4]+" chance to pass each one.");
                break;
            case "3":
                System.out.println("\nTrial: "+ data[1]+ " (Doctoral thesis defense)");
                System.out.println("Field: "+data[2]);
                System.out.println("Difficulty: "+data[3]);
                break;
            case "4":
                System.out.println("\nTrial: "+ data[1]+ " (Budget request)");
                System.out.println("Entity: "+data[2]);
                System.out.println("Budget: "+data[3]+"€");
                break;
        }
    }

    /**
     * Mensaje informativo de cargando las pruebas
     */
    @Override
    public void listTrialsText() {
        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
    }

    /**
     * Mensaje de querrer eliminar la prueba en cuestion
     */
    @Override
    public void deleteTrialsText() {
        System.out.println("Which trial do you want to delete?\n");
    }

    /**
     * Confirmacion de eliminar la prueba mediante su nombre
     * @param numPrueba numero de la prueba que se eliminara
     * @param names nombre de la prueba que se eliminara
     * @return devolver la prueba que se ha eliminado
     */
    @Override
    public boolean deleteConfirmation(int numPrueba, String[] names) {
        String name = askString("\nEnter the trial’s name for confirmation:");
        return name.equalsIgnoreCase(names[numPrueba - 1]);
    }

    /**
     * Mensaje informativo de que se ha eliminado satisfactoriamente
     */
    @Override
    public void deleteOK() {
        System.out.println("\nThe trial was successfully deleted.\n");
    }

    /**
     * Menu de las diferentes opciones de gestionar la edicion
     * @return devuelve la accion que se desea ejecutar: crear, lista, duplicar, eliminar o volver al menu anterior.
     */
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

    /**
     * Elegir que prueba queremos para crear una edicion
     * @param nameTrial nombre de la prueba que queremos gestionar
     * @param numPruebas numero de la prueba que queremos gestionar
     * @return devuelve la prueba que ha elegido el usuario
     */
    @Override
    public int[] pickEditionTrials(String[] nameTrial, int numPruebas) {
        if(nameTrial.length < 3) {
            int[] num = {-1};
            return num;
        }
        int i;
        System.out.println("\n\t--- Trials ---\n");
        for (i = 0; i < nameTrial.length; i++) {
            System.out.println((i+1)+") "+nameTrial[i]);
        }

        int[] nums = new int[numPruebas];
        for (int j = 0; j < numPruebas; j++) {
            nums[j] = askInt("Pick a trial ("+(j+1)+"/"+numPruebas+"):", 1, nameTrial.length);
        }

        System.out.println("\nThe edition was created successfully!");
        return nums;
    }

    /**
     * Elegir las ediciones que queremos visualizar segun el año de la prueba
     * @param years año de la prueba
     * @return devolver la opcion que quiere listar el usuario
     */
    @Override
    public int showEditionYears(int[] years) {
        int i;

        for (i = 0; i < years.length; i++) {
            System.out.println((i+1)+") The Trials "+years[i]);
        }
        System.out.println("\n"+(i+1)+") Back\n");

        return askInt("Enter an option: ");
    }

    /**
     * Mostar la informacion de la edicion elegida por el usuario
     * @param info String con los datos de la edicion
     */
    @Override
    public void showEditionData(String[] info) {
        System.out.println("Year: "+info[0]);
        System.out.println("Players: "+info[1]);
        System.out.println("Trials:");
        int j = 3;
        for (int i = 0; i < Integer.parseInt(info[2]); i++) {
            System.out.print("\t"+(i+1)+"- "+info[j]);
            j++;
            System.out.println(" ("+info[j]+")");
            j++;
        }
    }

    /**
     * Mensajes de las diferentes opciones que puede hacer el usuario
     * @param option segun la opcion que eliga se ejecutara: lista, duplicar o eliminar ediciones
     */
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

    /**
     * Confirmar el año de la edicion que se quiere eliminar
     * @param editionYear comprovar que el año elegido por el usuario coincide con las ya creadas
     * @return devolver el año de la prueba que se quiere eliminar si existe
     */
    @Override
    public boolean askDeleteYear(int editionYear) {
        int year = askInt("Enter the edition’s year for confirmation:");
        if(year == editionYear){
            return true;
        }
         return false;
    }

    /**
     * Pedir informacion de tipo String
     * @param text texto del cual queremos tratar
     * @return devuelve la String
     */
    @Override
    public String askString(String text) {
        System.out.println(text);
        do {
            String info = scanner.nextLine();

            if(!info.equals("") &&  info.length() > 0 && !info.equals(" ")) {
                return info;
            }
            System.out.println("Enter a valid text!");

        }while(true);
    }

    /**
     * Pedir informacion de tipo int
     * @param text texto del cual queremos tratar
     * @return devuelve el texto transformado a int
     */
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

    /**
     * Pedir tipo int entre dos valores eligidos por el usuario
     * @param text texto del cual queremos tratar
     * @param min numero minimio que puede introducir el usuario
     * @param max numero maximo que puede introducir el usuario
     * @return devuelve el int que ha elegido el usuario
     */
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

    /**
     * Mensaje informativo de que no ha creado ninguna edicion anteriormente
     * @param year año de la edicion
     */
    @Override
    public void noCurrentEdition(int year) {
        System.out.println("\nEntering execution mode...\n");
        System.out.println("No edition is defined for the current year ("+year+").\n");
        System.out.println("Shutting down...");
    }

    /**
     * Nombre de los jugadores de las pruebas
     * @param year año de la prueba que queremos gestionar
     * @param numPlayers numero de jugadores que hay en la prueba
     * @return devuelve el nombre de los jugadores
     */
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

    /**
     * Mensaje de si el usuario quiere continuar la ejecucion
     * @return devolver la opcion que quiere hacer el usuario, si seguir o no
     */
    @Override
    public boolean askToContinue() {
        while (true) {
            String response = askString("\nContinue the execution? [yes/no]:");
            if(response.equalsIgnoreCase("yes")){
                return true;
            }
            else if(response.equalsIgnoreCase("no")){
                return false;
            }
        }
    }

    /**
     * Mostrar los resultados de la ejecucion
     * @param results informacion de los diferentes tipos de pruebas
     */
    @Override
    public void showResults(String[] results) {
        int numPlayers = Integer.parseInt(results[3]);
        int[] types = new int[numPlayers];
        int k = 0, i= 4;
        int trialType = Integer.parseInt(results[1]);
        String[] names = new String[numPlayers];

        System.out.println("\nTrial #"+results[2]+" - "+results[0]+"\n");
        if(trialType == 4) {
           if(results[4] == "1") {
               System.out.println("The research group got the budget!");
           }
           else {
               System.out.println("The research group did not get the budget...");
           }
           i++;
        }
        for (; i < results.length; i++) {
            if(results[i].charAt(results[i].length()-1) == '*') {
                String name = results[i].substring(0,results[i].length()-1);
                System.out.println(name+" is disqualified");
                names[k] = name;
                i++;
                i++;
                i++;
                i++;
            }
            else {
                switch (trialType) {
                    case 1:
                        System.out.print(results[i]+" is submitting...");
                        names[k] = results[i];
                        i++;
                        int counter = Integer.parseInt(results[i]);
                        for (int j = 0; j < counter; j++) {
                            System.out.print(" Revisions...");
                        }
                        i++;
                        if(results[i] == "true") {
                            System.out.print(" Accepted!");
                        }
                        else {
                            System.out.print(" Rejected.");
                        }
                        i++;
                        break;
                    case 2:
                        System.out.print(results[i]+" passed ");
                        names[k] = results[i];
                        i++;
                        System.out.print(results[i]+"/");
                        i++;
                        System.out.print(Math.abs(Integer.valueOf(results[i]))+" ECTS. ");
                        if(Integer.valueOf(results[i]) > 0) {
                            System.out.print("Congrats!");
                        }
                        else {
                            System.out.print("Sorry...");
                        }
                        i++;
                        break;
                    case 3:
                        names[k] = results[i];
                        i++;
                        if(results[i].equals("true")) {
                            System.out.print(names[k]+" was successful. Congrats!");
                        }
                        else {
                            System.out.println(names[k]+" was not successful. Sorry...");
                        }
                        i++;
                        break;
                    case 4:
                        names[k] = results[i];
                        System.out.print(names[k]+".");
                        i++;
                        break;
                }
                int pi = Integer.parseInt(results[i]);
                if (pi > 0) {
                    System.out.println(" PI count: "+pi);
                }
                else {
                    System.out.println(" PI count: 0 - Disqualified!");
                }
                i++;
                types[k] = Integer.parseInt(results[i]);
            }
            k++;
        }
        for (i = 0; i < numPlayers; i++) {
            if(types[i] == 1) {
                System.out.println(names[i]+" is now a master (with 5 PI).");
            }
            else if(types[i] == 2) {
                System.out.println(names[i]+" is now a doctor (with 5 PI).");
            }

        }
    }

    /**
     * Mensaje de saliendo del programa
     */
    @Override
    public void exitProgram() {
        System.out.println("Saving & Shutting down...");
    }

    /**
     * Mostrar si los jugadores han ganado o perdido
     * @param results gestionar los resultados segun los tipos de prueba
     * @param year el año de la prueba del cual estaba ejecutando el usuario
     */
    @Override
    public void showEditionResult(String[] results, int year) {
        boolean r = false;

        switch (Integer.parseInt(results[1])) {
            case 1,2:
                for (int i = 4; i < results.length; i++) {
                    i++;
                    i++;
                    i++;
                    int pi = Integer.parseInt(results[i]);
                    if(pi > 0) {
                        r = true;
                    }
                    i++;
                }
                break;
            case 3:
                for (int i = 4; i < results.length; i++) {
                    i++;
                    i++;
                    int pi = Integer.parseInt(results[i]);
                    if(pi > 0) {
                        r = true;
                    }
                    i++;
                }
                break;
            case 4:
                for (int i = 5; i < results.length; i++) {
                    i++;
                    int pi = Integer.parseInt(results[i]);
                    if(pi > 0) {
                        r = true;
                    }
                    i++;
                }
                break;
        }

        System.out.print("\nTHE TRIALS "+year+" HAVE ENDED - ");
        if(r) {
            System.out.println("PLAYERS WON\n");
        }
        else {
            System.out.println("PLAYERS LOSE\n");
        }
        System.out.println("Shutting down...");
    }

    /**
     * Mensaje de que hay que haber 3 pruebas creadas anteriormente para crear una edicion
     */
    @Override
    public void showTrialERROR() {
        System.out.println("There must be at least 3 trials to create a edition!");
    }

    /**
     * Mostrar al usuario las restricciones del quartile
     * @return devuelve el quartile escrito por el usuario
     */
    @Override
    public String askQuartile() {
        String quartile;
        System.out.println(ConsoleUIManager.askTrialQuartile);

        do{
            quartile = scanner.nextLine();
            if((quartile.charAt(0) == 'q' || quartile.charAt(0) == 'Q') && quartile.length() == 2 && quartile.charAt(1) > '0' && quartile.charAt(1) < '5' ) {
                return quartile;
            }
            System.out.println("Quartile must be 'q + [1,4]'. Please enter it again");
        }while(true);
    }

    /**
     * Preguntar el formato que desea guardar el usuario
     * @return devuelve el tipo de persistencia que el usuario quiere guardar
     */
    @Override
    public boolean askPersistence() {
        String faction;
        System.out.println("The IEEE needs to know where your allegiance lies.\n\n" +
                "\tI) People’s Front of Engineering (CSV)\n" +
                "\tII) Engineering People’s Front (JSON)\n");
        do {
            faction = askString("Pick a faction: ");
            if(faction.equalsIgnoreCase("I")) {
                System.out.println("Loading data from CSV files...");
                return false;
            }
            else if(faction.equalsIgnoreCase("II")) {
                System.out.println("Loading data from JSON files...");
                return true;
            }
            else {
                System.out.println("Please enter a valid option!\n");
            }
        }while (true);

    }

    /**
     * Restriccion de las probabilidades de la prueba paper publication
     * @param acc probabilidad de aceptar
     * @param rev probabilidad de revisar
     * @param rej probabilidad de denegar
     * @return devuelve si la suma de las 3 da 100
     */
    @Override
    public boolean checkPercentage(int acc, int rev, int rej) {
        if(acc+rev+rej == 100) {
            return true;
        }
        System.out.println("The sum of the three probabilities must be 100!\n");
        return false;
    }

    @Override
    public void deleteNot() {
        System.out.println("The trial you are trying to delete, is already in an edition.");
    }
}


