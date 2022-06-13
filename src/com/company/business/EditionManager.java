package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;
import com.company.persistence.EditionDAOjson;

import java.util.Calendar;

/**
 * Gestionar las ediciones
 */
public class EditionManager {
    private EditionDAO editionDAOcsv;
    private EditionDAO editionDAOjson;

    /**
     * Inicializamos el formato de persistencia que se desea guardar, csv o json
     */
    public EditionManager() {
        editionDAOcsv = new EditionDAOcsv("data/edition.csv", "data/currentEdition.csv");
        editionDAOjson = new EditionDAOjson("data/edition.json", "data/currentEdition.json");
    }

    /**
     * Crear la edicion
     * @param year año de la edicion
     * @param numPlayers numero de jugadores de la edicion
     * @param numTrials numero de pruebas que habra
     * @param nums numero de la edicon que se gestionara
     * @param pType tipo de persistencia
     */
    public void crearEdition(int year, int numPlayers, int numTrials, int[] nums, boolean pType) {

        Edition edition = new Edition(year, numPlayers,numTrials, nums);
        if(!pType) { editionDAOcsv.save(edition); }
        else { editionDAOjson.save(edition);}

    }

    /**
     * Lista de la ediciones
     * @param pType tipo de persistencia
     * @return devuelve el nombre de las ediciones para listarlas
     */
    public int[] listaEdition(boolean pType) {
        Edition[] editions;

        if(!pType) { editions = editionDAOcsv.getAll(); }
        else { editions = editionDAOjson.getAll();}

        int[] names = new int[editions.length];
        for (int i = 0; i < editions.length; i++) {
            names[i] = editions[i].getYear();
        }
        return names;
    }

    /**
     * Acceder a la edicion
     * @param numEdition numero de la edicion que se quiere gestionar
     * @param pType tipo de persistencia que se guardara
     * @return devuelve la edicion
     */
    public Edition getEdition(int numEdition, boolean pType) {

        if(!pType) { return editionDAOcsv.get(numEdition); }
        else { return editionDAOjson.get(numEdition);}

    }

    /**
     * Salir de la edicion
     * @param numEdition numero de la edicion que se quiere gestionar
     * @param pType tipo de persistencia que se guardara
     * @return devuelve si se quiere salir o no de la edicion
     */
    public boolean exitEdition (int numEdition, boolean pType){

        if(!pType) {  return editionDAOcsv.exitEdition(numEdition);}
        else {  return editionDAOjson.exitEdition(numEdition);}

    }

    /**
     * Eliminar edicion segun el tipo de persistencia que se haya elegido
     * @param numEdition numero de la edicion que se quiere gestionar
     * @param pType tipo de persistencia en que se guardara
     */
    public void deleteEdition(int numEdition, boolean pType) {

        if(!pType) {  editionDAOcsv.delete(numEdition);}
        else {  editionDAOjson.delete(numEdition);}

    }

    /**
     * Acceder a la edicion actual
     * @param pType tipo de persistencia en que se guardara
     * @return devuelve la edicion actual
     */
    public Edition getCurrentEdition(boolean pType) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int[] years = listaEdition(pType);
        Edition editionCurrent = null;

        for (int i = 0; i < years.length; i++) {
            if (year == years[i]) {
                editionCurrent = getEdition(i+1, pType);      //la guardamos
            }
        }
        return editionCurrent;
    }

    /**
     * Guardar todos los datos de la edicion actual dependiendo del tipo de persistencia
     * @param edition clase edicion que contiene toda la informacion
     * @param players numero de jugadores
     * @param currentTrial la prueba actual que se esta trabajando
     * @param pType tipo de persistencia en que se guardara
     */
    public void saveCurrentEdition(Edition edition, Object[] players, int currentTrial, boolean pType) {

        if(!pType) {   editionDAOcsv.saveCurrent(edition, players, currentTrial);}
        else {  editionDAOjson.saveCurrent(edition, players, currentTrial);}

    }

    /**
     * Cargar la edicion actual
     * @param pType tipo de persistencia en que se guardara
     * @return devuelve la edicion actual con todos sus datos y si no hay edicion no devuelve nada
     */
    public Edition loadCurrentEdition(boolean pType){
        String edition;

        if(!pType) {   edition = editionDAOcsv.loadCurrent();}
        else {
            edition = editionDAOjson.loadCurrent();
        }

        if(edition != null){
            String[] data = edition.split(",");
            int year = Integer.parseInt(data[0]);
            if(year == Calendar.getInstance().get(Calendar.YEAR)) {
                int numPlayers = Integer.parseInt(data[1]);
                Player[] players = new Player[numPlayers];
                int j = 2;

                for (int i = 0; i < numPlayers; i++) {
                    switch(data[j+2]) {
                        case "0"-> players[i] = new Engineer(data[j], Integer.parseInt(data[j+1]));
                        case "1"-> players[i] = new Doctor(data[j], Integer.parseInt(data[j+1]));
                        case "2"-> players[i] = new Master(data[j], Integer.parseInt(data[j+1]));
                    }
                    j = j+3;
                }
                String[] b = data[data.length - 1].split(";");
                int currentTrial = Integer.parseInt(b[0]);
                Edition edition1 = getCurrentEdition(pType);
                edition1.setCurrentTrial(currentTrial);
                edition1.setPlayers(players);

                return edition1;
            }
        }
        return null;
    }

    /**
     * Borrar el archivo de la edicion actual
     * @param pType tipo de persistencia en que se guardara
     */
    public void clearCurrentEdition(boolean pType) {
        if(!pType) {  editionDAOcsv.emptyCurrent();}
        else { editionDAOjson.emptyCurrent();}
    }
}

