package com.company.persistence;

import com.company.business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementa las funciones del DAO de las ediciones en JSON
 */
public class EditionDAOjson implements EditionDAO {
    private String path;
    private String pathCurrent;

    /**
     * Constructor donde incicialiazamos el path y pathCurrent
     * @param path ruta que se ejecuta
     * @param pathCurrent ruta que se esta ejecutando actualmente
     */
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

    /**
     * Guardar edicion
     * @param edition datos de la clase Edition
     */
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

    /**
     * Acceder a todas las ediciones
     * @return devuelve todas las ediciones
     */
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

    /**
     * Acceder a una edicion en concreto
     * @param numEdition numero de la edicion que se quiere acceder
     * @return devuelve la edicion en concreto
     */
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

        } catch (java.io.IOException | JsonSyntaxException | ArrayIndexOutOfBoundsException e ) {
            System.out.println("Error detected in saved data, shutting down the program...");
            try {
                Path p1 = Paths.get(path);
                Path p2 = Paths.get("data/trials.json");
                Path p3 = Paths.get(pathCurrent);
                String a = "";
                Files.write(p1, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
                Files.write(p2, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
                Files.write(p3, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
            } catch (IOException j) {
                j.printStackTrace();
            }
            System.exit(0);
        }

        return null;
    }

    /**
     * Salir de la edicion
     * @param numEdition numero de la edicon que se quiere salir
     * @return devuelve si se quiere salir o no de la edicion
     */
    @Override
    public boolean exitEdition(int numEdition) {
        Edition[] editions = getAll();
        if(editions.length +1 == numEdition){
            return true;
        }
        return false;
    }

    /**
     * Eliminar edicion
     * @param numEdition numero de la edicion que se quiere eliminar
     */
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

    /**
     * Guardar la edicion actual
     * @param edition datos de la clase Edition
     * @param players jugadores que intervienen
     * @param currentTrial prueba actual
     */
    @Override
    public void saveCurrent(Edition edition, Player[] players, int currentTrial) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList data = new ArrayList <>();
            String[][] p = new String[players.length][3];

            for (int i = 0; i < players.length; i++) {
                String[] info = new String[3];
                info[0] = players[i].getName();
                info[1] = String.valueOf(players[i].getPI());
                info[2] = String.valueOf(players[i].getType());
                p[i] = info;
            }

            data.add(edition);
            data.add(p);
            data.add(currentTrial);
            FileWriter fileWriter = new FileWriter(pathCurrent, false);
            fileWriter.write(gson.toJson(data));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cargar la edicion actual
     * @return devuelve los resultados
     */
    @Override
    public String loadCurrent() {
        try {
            Reader file = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList data;
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
                String[][] p = new String[e.getNumPlayers()][3];
                p = gson.fromJson(o2.toString(), p.getClass());
                Player[] players = new Player[p.length];
                for (int i = 0; i < p.length; i++) {
                    switch (p[i][2]) {
                        case "0" -> players[i] = new Engineer(p[i][0], Integer.parseInt(p[i][1]));
                        case "1" -> players[i] = new Master(p[i][0], Integer.parseInt(p[i][1]));
                        case "2" -> players[i] = new Doctor(p[i][0], Integer.parseInt(p[i][1]));
                    }
                }

                Object o3 = data.get(2);
                int t = gson.fromJson(o3.toString(), int.class);

                String dataResults = e.getYear()+","+e.getNumPlayers()+",";
                for (int i = 0; i < e.getNumPlayers(); i++) {
                    dataResults = dataResults + players[i].getName()+","+players[i].getPI()+","+players[i].getType()+",";
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

    /**
     * Comprueba si esta vacia
     */
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

    @Override
    public void deleteData() {
        System.out.println("Error detected in saved data, shutting down the program...");
        try {
            Path p1 = Paths.get(path);
            Path p2 = Paths.get("data/trials.json");
            Path p3 = Paths.get(pathCurrent);
            String a = "";
            Files.write(p1, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
            Files.write(p2, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
            Files.write(p3, Collections.singletonList(a), StandardOpenOption.DELETE_ON_CLOSE);
        } catch (IOException j) {
            j.printStackTrace();
        }
        System.exit(0);
    }
}
