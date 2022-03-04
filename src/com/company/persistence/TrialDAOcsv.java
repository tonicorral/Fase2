package com.company.persistence;

import com.company.business.Trial;
import com.company.business.TrialManager;
import com.company.business.TrialMaster;
import com.company.business.TrialPublicacionArticulo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

public class TrialDAOcsv implements TrialDAO{
    private Path path;

    public TrialDAOcsv(String path) {

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
    public void save(TrialPublicacionArticulo trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(TrialMaster trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Devuelve todas las pruebas
    @Override
    public Trial[] getAll() {
        ArrayList<Trial> trial = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                switch (checkTrial(fileLine)) {
                    case 1:
                        TrialPublicacionArticulo a = trialFromCSV1(fileLine);
                        trial.add(a);
                        break;
                    case 2:
                        TrialMaster b = trialFromCSV2(fileLine);
                        trial.add(b);
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trial.toArray(new Trial[trial.size()]);
    }

    //Devuelve la prueba que corresponde el num que le entra
    @Override
    public TrialPublicacionArticulo getPublicacion(int numberTrial) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            return trialFromCSV1(fileContent.get(numberTrial-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TrialMaster getMaster(int numberTrial) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            return trialFromCSV2(fileContent.get(numberTrial-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean trialExit(int numPrueba) {
        Trial[] trials = getAll();      //llamamos a la funcion
        if(trials.length + 1 == numPrueba) {
            return true;
        }

        return false;
    }

    @Override
    public void delete(int numPrueba) {
        ArrayList<TrialPublicacionArticulo> trials = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                TrialPublicacionArticulo a = trialFromCSV1(fileLine);
                trials.add(a);
            }

            ArrayList<String> trialList = new ArrayList<>();     //arraylist de pruebas
            for (int i = 0; i < trials.size(); i++) {
                if(i != numPrueba - 1){
                    trialList.add(trialToCSV(trials.get(i)));       //guardamos todas las pruebas menos la que borramos
                }
            }

            Files.write(path, trialList);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getType(int numPrueba) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            return checkTrial(fileContent.get(numPrueba-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //info a texto
    private String trialToCSV(TrialPublicacionArticulo trial) {
        String file;

        file = "1," + trial.getNombre()+","+trial.getNombreRevista()+","+trial.getQuartil()+","+
                trial.getProbAceptar()+","+trial.getProbRevision()+","+trial.getProbDenegar()+";";

        return file;
    }

    private String trialToCSV(TrialMaster trial) {
        String file;

        file = "2,"+ trial.getNombre()+","+trial.getMasterName()+","+trial.getCreditNumber()+","+trial.getProbCredit()+";";

        return file;
    }
    private int checkTrial(String trial){
        String[] a;

        a = trial.split(",");
        return Integer.parseInt(a[0]);
    }

    private TrialPublicacionArticulo trialFromCSV1(String trial) {
        TrialPublicacionArticulo t;
        String[] a;
        String[] b;
        a = trial.split(",");
        b = a[6].split(";");
        t = new TrialPublicacionArticulo(a[1],a[2], a[3], Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(b[0]));

        return t;
    }

    private TrialMaster trialFromCSV2(String trial) {
        TrialMaster t;
        String[] a;
        String[] b;
        a = trial.split(",");
        b = a[4].split(";");
        t = new TrialMaster(a[1],a[2],Integer.parseInt(a[3]), Integer.parseInt(b[0]));

        return t;
    }







}

