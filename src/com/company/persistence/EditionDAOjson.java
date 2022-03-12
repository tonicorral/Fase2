package com.company.persistence;

import com.company.business.Edition;
import com.company.business.Player;
import com.company.business.Trial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

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
                editions = gson.fromJson(file, editions.getClass());
            }
            Edition[] editions1 = new Edition[editions.size()];
            for (int i = 0; i < editions.size(); i++) {
                Object o = editions.get(i);
                editions1[i] = gson.fromJson(o.toString(),Edition.class);
            }

            return editions1;

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

            Object o =  editions.get(numEdition - 1);
            return gson.fromJson(o.toString(), Edition.class);

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
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList data = new ArrayList <>();

            data.add(edition);
            data.add(players);
            data.add(currentTrial);
            FileWriter fileWriter = new FileWriter(pathCurrent, false);
            fileWriter.write(gson.toJson(data));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String loadCurrent() {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList data = new ArrayList <>();
            boolean empty = false;

            try {
                file = new FileReader(pathCurrent);
                if((file.read() == -1)){
                    empty = true;
                }
                file = new FileReader(pathCurrent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(!empty) {
                data = gson.fromJson(file, ArrayList.class);
                Object o1 = data.get(0);
                Edition e = gson.fromJson(o1.toString(), Edition.class);
                Object o2 = data.get(1);
                Player[] p = gson.fromJson(o2.toString(), Player[].class);
                Object o3 = data.get(2);
                int t = gson.fromJson(o3.toString(), int.class);

                String dataResults = e.getYear()+","+e.getNumPlayers()+",";
                for (int i = 0; i < e.getNumPlayers(); i++) {
                    dataResults = dataResults + p[i].getName()+","+p[i].getPI()+","+p[i].getType()+",";
                }
                dataResults = dataResults +t+";";

                return dataResults;
            }
            else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void emptyCurrent() {
        try {
            Path p1 = Paths.get(pathCurrent);
            String a = "";
            Files.write(p1, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
