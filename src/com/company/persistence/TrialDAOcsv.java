package com.company.persistence;

import com.company.business.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementa funciones del DAO de las pruebas en CSV
 */
public class TrialDAOcsv implements TrialDAO{
    private Path path;

    /**
     * Constructor donde inicializamos el path
     * @param path ruta que se ejecuta
     */
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

    /**
     * Guardar prueba tipo Publicacion Articulo
     * @param trial prueba tipo TrialPublicacionArticulo
     */
    @Override
    public void save(TrialPublicacionArticulo trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guardar prueba tipo Master
     * @param trial prueba tipo TrialMaster
     */
    public void save(TrialMaster trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guardar prueba tipo Tesis
     * @param trial prueba tipo TrialTesis
     */
    @Override
    public void save(TrialTesis trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guardar prueba tipo Budget
     * @param trial prueba tipo TrialBudget
     */
    @Override
    public void save(TrialBudget trial) {
        try {
            Files.write(path, Collections.singletonList(trialToCSV(trial)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Acceder a todas las pruebas
     * @return devuelve a todas las pruebas
     */
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
                    case 3:
                        TrialTesis c = trialFromCSV3(fileLine);
                        trial.add(c);
                        break;
                    case 4:
                        TrialBudget d = trialFromCSV4(fileLine);
                        trial.add(d);
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trial.toArray(new Trial[trial.size()]);
    }

    /**
     * Accede a la prueba Publicacion Articulo que correpende al numero que le entra
     * @param numberTrial numero de la prueba que le entra
     * @return devuelve la prueba que correpende al numero que le entra
     */
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

    /**
     * Accede a la prueba Master que correpende al numero que le entra
     * @param numberTrial numero de la prueba que le entra
     * @return devuelve la prueba que correpende al numero que le entra
     */
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

    /**
     * Accede a la prueba Tesis que correpende al numero que le entra
     * @param numberTrial numero de la prueba que le entra
     * @return devuelve la prueba que correpende al numero que le entra
     */
    @Override
    public TrialTesis getTesis(int numberTrial) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            return trialFromCSV3(fileContent.get(numberTrial-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Accede a la prueba Budget que correpende al numero que le entra
     * @param numberTrial numero de la prueba que le entra
     * @return devuelve la prueba que correpende al numero que le entra
     */
    @Override
    public TrialBudget getBudget(int numberTrial) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            return trialFromCSV4(fileContent.get(numberTrial-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Salir de la prueba
     * @param numPrueba numero de la prueba que se quiere salir
     * @return devuelve si se quiere salir o no de la prueba
     */
    @Override
    public boolean trialExit(int numPrueba) {
        Trial[] trials = getAll();
        if(trials.length + 1 == numPrueba) {
            return true;
        }
        return false;
    }

    /**
     * Eliminar prueba
     * @param numPrueba numero de la prueba que se quiere eliminar
     */
    @Override
    public void delete(int numPrueba) {
        ArrayList trial = new ArrayList();
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
            ArrayList<String> trialList = new ArrayList<>();     //arraylist de pruebas
            for (int i = 0; i < trial.size(); i++) {
                if (i != numPrueba - 1) {
                    if(trial.get(i) instanceof TrialPublicacionArticulo) {
                        trialList.add(trialToCSV((TrialPublicacionArticulo) trial.get(i)));
                    }
                    else if(trial.get(i) instanceof TrialMaster) {
                        trialList.add(trialToCSV((TrialMaster) trial.get(i)));
                    }
                }
            }

            Files.write(path, trialList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Acceder al tipo de prueba
     * @param numPrueba numero de prueba
     * @return devuelve el tipo de prueba
     */
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
    private String trialToCSV(TrialTesis trial) {
        String file;
        file = "3,"+ trial.getNombre()+","+trial.getStudy()+","+trial.getDificulty()+";";
        return file;
    }
    private String trialToCSV(TrialBudget trial) {
        String file;
        file = "4,"+ trial.getNombre()+","+trial.getEntity()+","+trial.getBudget()+";";
        return file;
    }
    private int checkTrial(String trial) {
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

    private TrialTesis trialFromCSV3(String trial) {
        TrialTesis t;
        String[] a;
        String[] b;
        a = trial.split(",");
        b = a[3].split(";");
        t = new TrialTesis(a[1],a[2],Integer.parseInt(b[0]));

        return t;
    }
    private TrialBudget trialFromCSV4(String trial) {
        TrialBudget t;
        String[] a;
        String[] b;
        a = trial.split(",");
        b = a[3].split(";");
        t = new TrialBudget(a[1],a[2],Integer.parseInt(b[0]));

        return t;
    }
}

