package com.company.persistence;

import com.company.business.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    @Override
    public void save(TrialPublicacionArticulo trial) {
        try {
            JsonElement trialjson;
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList trials = new ArrayList<>();
            JsonReader reader = null;

            trials.add(trial);
            
            try {
                file = new FileReader(path);
                reader = new JsonReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!(file.read() == -1)) {
                trials.add(gson.fromJson(reader, Object.class));
            }

            trialjson = gson.toJsonTree(trials);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(trialjson));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TrialMaster trial) {
        try {
            JsonElement trialjson;
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList trials = new ArrayList<>();
            JsonReader reader = null;

            trials.add(trial);

            try {
                file = new FileReader(path);
                reader = new JsonReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!(file.read() == -1)) {
                trials.add(gson.fromJson(reader, Object.class));
            }

            System.out.println(trials.size());

            trialjson = gson.toJsonTree(trials);
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(gson.toJson(trialjson));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TrialTesis trial) {

    }

    @Override
    public void save(TrialBudget trial) {

    }

    @Override
    public Trial[] getAll() {
        return new Trial[0];
    }

    @Override
    public TrialPublicacionArticulo getPublicacion(int numberTrial) {
        Reader file = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList trials = new ArrayList<>();
        JsonReader reader = null;

        try {
            file = new FileReader(path);
            reader = new JsonReader(file);
            if(!(file.read() == -1)) {
                trials.add(gson.fromJson(reader, TrialPublicacionArticulo.class));
            }
        } catch (java.io.IOException e ) {
            e.printStackTrace();
        }

        return (TrialPublicacionArticulo) trials.get(numberTrial);
    }

    @Override
    public TrialMaster getMaster(int numberTrial) {
        Reader file = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList trials = new ArrayList<>();
        JsonReader reader = null;

        try {
            file = new FileReader(path);
            reader = new JsonReader(file);
            if(!(file.read() == -1)) {
                trials.add(gson.fromJson(reader, TrialMaster.class));
            }
        } catch (java.io.IOException e ) {
            e.printStackTrace();
        }

        return (TrialMaster) trials.get(numberTrial);
    }

    @Override
    public TrialTesis getTesis(int numberTrial) {
        return null;
    }

    @Override
    public TrialBudget getBudget(int numberTrial) {
        return null;
    }

    @Override
    public boolean trialExit(int numPrueba) {
        return false;
    }

    @Override
    public void delete(int numPrueba) {

    }

    @Override
    public int getType(int numPrueba) {
        return 0;
    }
}
