package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Trial;
import com.company.business.TrialPublicacionArticulo;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

public class EditionDAOcsv implements EditionDAO {
    private Path path;

    public EditionDAOcsv(String path) {
        try {
            Path p = Paths.get(path);       //ruta donde guardamos los archivos
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            this.path = p;

        } catch (IOException | InvalidPathException e) {
            e.getMessage();
        }
    }

    @Override
    public void save(Edition edition, int[] nums) {
        try {
            Files.write(path, Collections.singletonList(editionToCSV(edition, nums)), StandardOpenOption.APPEND);
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
                System.out.println(a.getYear());
                System.out.println(a.getNumPlayers());
                System.out.println(a.getNumTrials());
                System.out.println(a.getNum()[0]);
                System.out.println(a.getNum()[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return editions.get(numEdition-1);
    }

    //info a texto
    private String editionToCSV(Edition edition, int[] numeros) {
        String file;

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

        //TODO QUITAR ;
        int[] nums = new int[numTrials];
        a[a.length - 1].split(";");

        for (int i = 0; i < numTrials; i++) {
            nums[i] = Integer.parseInt(a[i+3]);
        }

        t = new Edition(Integer.parseInt(año),Integer.parseInt(numPlayers), numTrials, nums);

        return t;
    }
}
