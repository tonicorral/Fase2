package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Player;
import com.company.business.Trial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EditionDAOjson implements EditionDAO {
    private String path;
    private String pathCurrent;

    public EditionDAOjson(String path, String pathCurrent) {
        try {
            Path p = Paths.get(path);       //ruta donde guardamos los archivos
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            p = Paths.get(pathCurrent);
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            this.path = path;
            this.pathCurrent = pathCurrent;

        } catch (IOException | InvalidPathException e) {
            e.getMessage();
        }
    }

    @Override
    public void save(Edition edition) {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Edition> editions = new ArrayList <>();
            boolean empty = false;


            try {
                file = new FileReader(path);
                if((file.read() == -1)){
                    empty = true;
                }
                file = new FileReader(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!empty) {
                editions = gson.fromJson(file, ArrayList.class);
            }
            editions.add(edition);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(editions));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Edition[] getAll() {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Edition> editions = new ArrayList <>();
            boolean empty = false;


            try {
                file = new FileReader(path);
                if((file.read() == -1)){
                    empty = true;
                }
                file = new FileReader(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!empty) {
                editions = gson.fromJson(file, ArrayList.class);
            }
            return editions.toArray(new Edition[editions.size()]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Edition get(int numEdition) {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Edition> editions = new ArrayList <>();
            boolean empty = false;


            try {
                file = new FileReader(path);
                if((file.read() == -1)){
                    empty = true;
                }
                file = new FileReader(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!empty) {
                editions = gson.fromJson(file, ArrayList.class);
            }
            return editions.get(numEdition - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exitEdition(int numEdition) {
        Edition[] editions = getAll();
        if(editions.length +1 == numEdition){
            return true;
        }
        return false;
    }

    @Override
    public void delete(int numEdition) {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Edition> editions = new ArrayList <>();
            boolean empty = false;


            try {
                file = new FileReader(path);
                if((file.read() == -1)){
                    empty = true;
                }
                file = new FileReader(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!empty) {
                editions = gson.fromJson(file, ArrayList.class);
            }
            editions.remove(numEdition);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(editions));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCurrent(Edition edition, Player[] players, int currentTrial) {


    }

    @Override
    public String loadCurrent() {
        return null;
    }

    @Override
    public void emptyCurrent() {

    }
}
