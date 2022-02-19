package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Player;
import com.company.business.Trial;
import com.company.business.TrialPublicacionArticulo;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

public class EditionDAOcsv implements EditionDAO {
    private Path path;
    private Path pathCurrent;

    public EditionDAOcsv(String path, String pathCurrent) {
        try {
            Path p = Paths.get(path);       //ruta donde guardamos los archivos
            Path p1 = Paths.get(pathCurrent);
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            if (!Files.exists(p1)) {
                Files.createFile(p1);
            }
            this.path = p;
            this.pathCurrent = p1;

        } catch (IOException | InvalidPathException e) {
            e.getMessage();
        }
    }

    @Override
    public void save(Edition edition) {
        try {
            Files.write(path, Collections.singletonList(editionToCSV(edition)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Edition[] getAll() {
        ArrayList<Edition> edition = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                Edition a = editionFromCSV(fileLine);
                edition.add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return edition.toArray(new Edition[edition.size()]);
    }

    @Override
    public Edition get(int numEdition) {
        ArrayList<Edition> editions = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                Edition a = editionFromCSV(fileLine);
                editions.add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return editions.get(numEdition-1);
    }

    @Override
    public boolean exitEdition(int numEdition) {
        Edition[] editions = getAll();      //llamamos a la funcion
        if(editions.length + 1 == numEdition) {
            return true;
        }
        return false;
    }

    @Override
    public void delete(int numEdition) {
        ArrayList<Edition> edition = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                Edition a = editionFromCSV(fileLine);
                edition.add(a);
            }

            ArrayList<String> editionList = new ArrayList<>();     //arraylist de pruebas
            for (int i = 0; i < edition.size(); i++) {
                if(i != numEdition - 1){
                    editionList.add(editionToCSV(edition.get(i)));       //guardamos todas las pruebas menos la que borramos
                }
            }

            Files.write(path, editionList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCurrent(Edition edition, Player[] players, int currentTrial) {
        try { //String a = ""
            Files.write(pathCurrent, Collections.singletonList(currentEditionToCSV(edition, players, currentTrial)), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String loadCurrent() {

        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(pathCurrent));
            if(fileContent.size() == 0 || fileContent.get(0).equals("")){
                return null;
            }
            return fileContent.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void emptyCurrent() {
        try {
            String a = "";
            Files.write(pathCurrent, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String currentEditionToCSV(Edition edition, Player[] players, int currentTrial) {
        int year = edition.getYear();
        int numPlayers = edition.getNumPlayers();
        String[] name = new String[numPlayers];
        int[] PI = new int[numPlayers];
        String data = year+","+numPlayers+",";

        for (int i = 0; i < numPlayers; i++) {
            name[i] = players[i].getName();
            PI[i] = players[i].getPI();
            data = data+name[i]+","+PI[i]+",";
        }
        data = data+currentTrial+";";

        return data;
    }


    //info a texto
    private String editionToCSV(Edition edition) {
        String file;
        int[] numeros = edition.getNum();
        file = edition.getYear()+","+edition.getNumPlayers()+","+edition.getNumTrials();

        for (int i = 0; i < numeros.length; i++) {
            file = file+","+numeros[i];
        }
        file = file+";";

        return file;
    }

    private Edition editionFromCSV(String edition) {
        Edition t;
        String[] a;
        String año;
        String numPlayers;
        int numTrials;

        a = edition.split(",");
        año = a[0];
        numPlayers = a[1];
        numTrials = Integer.parseInt(a[2]);

        int[] nums = new int[numTrials];
        String[] b = a[a.length - 1].split(";");
        int i;

        for (i = 0; i < numTrials - 1; i++) {
            nums[i] = Integer.parseInt(a[i+3]);
        }
        nums[i] = Integer.parseInt(b[0]);
        t = new Edition(Integer.parseInt(año),Integer.parseInt(numPlayers), numTrials, nums);

        return t;
    }

}
