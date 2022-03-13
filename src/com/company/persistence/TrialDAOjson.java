package com.company.persistence;

import com.company.business.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TrialDAOjson implements TrialDAO {
    private String path;

    public TrialDAOjson(String path) {
        try {
            Path p = Paths.get(path);       //ruta donde guardamos los archivos
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            this.path = path;

        } catch (IOException | InvalidPathException e) {
            e.getMessage();
        }
    }

    private void saveToJson(Object trial) {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList <Object> trials = new ArrayList <>();
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
                trials = gson.fromJson(file, ArrayList.class);
            }
            trials.add(trial);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(trials));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(TrialPublicacionArticulo trial) {
        saveToJson(trial);
    }

    @Override
    public void save(TrialMaster trial) {
        saveToJson(trial);
    }

    @Override
    public void save(TrialTesis trial) {
        saveToJson(trial);
    }

    @Override
    public void save(TrialBudget trial) {
        saveToJson(trial);
    }

    @Override
    public Trial[] getAll() {
        Reader file = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList <Trial> trials = new ArrayList<>();

        try {
            file = new FileReader(path);
            trials = gson.fromJson(file, trials.getClass());
        } catch (java.io.IOException e ) {
            e.printStackTrace();
        }
        Trial[] trialsArray;
        if(trials != null) {
            trialsArray = new Trial[trials.size()];
            for (int i = 0; i < trials.size(); i++) {
                Object o = trials.get(i);
                trialsArray[i] = gson.fromJson(o.toString(), Trial.class);
            }
        }
        else{
            trialsArray = new Trial[0];
        }
        return trialsArray;
    }

    private Object getTrial(int numberTrial) {
        Reader file = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList trials = new ArrayList<>();

        try {
            file = new FileReader(path);
            trials = gson.fromJson(file, ArrayList.class);

        } catch (java.io.IOException e ) {
            e.printStackTrace();
        }

        return  trials.get(numberTrial-1);
    }
    @Override
    public TrialPublicacionArticulo getPublicacion(int numberTrial) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(getTrial(numberTrial).toString(), TrialPublicacionArticulo.class);
    }

    @Override
    public TrialMaster getMaster(int numberTrial) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(getTrial(numberTrial).toString(), TrialMaster.class);
    }

    @Override
    public TrialTesis getTesis(int numberTrial) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(getTrial(numberTrial).toString(), TrialTesis.class);
    }

    @Override
    public TrialBudget getBudget(int numberTrial) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(getTrial(numberTrial).toString(), TrialBudget.class);
    }

    @Override
    public boolean trialExit(int numPrueba) {
        Trial[] trials = getAll();
        if(trials.length +1 == numPrueba){
            return true;
        }
        return false;
    }

    @Override
    public void delete(int numPrueba) {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList <Object> trials = new ArrayList <>();
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
                trials = gson.fromJson(file, ArrayList.class);
            }
            trials.remove(numPrueba - 1);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(trials));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType(int numPrueba) {
        Object o = getTrial(numPrueba);
        if(o.toString().contains("nombreRevista")){
            return 1;
        }
        else if(o.toString().contains("masterName")){
            return 2;
        }
        else if (o.toString().contains("study")){
            return 3;
        }
        else{
            return 4;
        }
    }
}
