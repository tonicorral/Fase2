package com.company.persistence;

import com.company.business.Trial;
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

    @Override
    public Trial[] getAll() {
        ArrayList<TrialPublicacionArticulo> trial = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                TrialPublicacionArticulo a = trialFromCSV(fileLine);
                trial.add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trial.toArray(new Trial[trial.size()]);
    }

    @Override
    public TrialPublicacionArticulo get(int numberTrial) {
        ArrayList<TrialPublicacionArticulo> trial = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String fileLine: fileContent) {
                TrialPublicacionArticulo a = trialFromCSV(fileLine);
                trial.add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trial.get(numberTrial--);
    }

    private String trialToCSV(TrialPublicacionArticulo trial) {
        String file;

        file = trial.getNombre()+","+trial.getNombreRevista()+","+trial.getQuartil()+","+
                trial.getProbAceptar()+","+trial.getProbRevision()+","+trial.getProbDenegar()+";";

        return file;
    }

    private TrialPublicacionArticulo trialFromCSV(String trial) {
        TrialPublicacionArticulo t;
        String[] a;
        String[] b;
        a = trial.split(",");
        b = a[5].split(";");
        t = new TrialPublicacionArticulo(a[0],a[1], a[2], Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(b[0]));

        return t;
    }








}

