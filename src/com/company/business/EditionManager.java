package com.company.business;

import com.company.persistence.EditionDAO;
import com.company.persistence.EditionDAOcsv;
import com.company.persistence.EditionDAOjson;

import java.util.Calendar;

/**
 * Gestionar las ediciones
 */
public class EditionManager {
    private EditionDAO editionDAO;

    /**
     * Inicializamos el formato de persistencia que se desea guardar, csv o json
     */
    public EditionManager(Boolean pType) {
        if(!pType) {
            editionDAO = new EditionDAOcsv("data/edition.csv", "data/currentEdition.csv");
        }
        else {
            editionDAO = new EditionDAOjson("data/edition.json", "data/currentEdition.json");
        }


    }

    public void deleteData() {
        editionDAO.deleteData();
    }

    /**
     * Crear la edicion
     * @param year año de la edicion
     * @param numPlayers numero de jugadores de la edicion
     * @param numTrials numero de pruebas que habra
     * @param nums numero de la edicon que se gestionara
     */
    public void crearEdition(int year, int numPlayers, int numTrials, int[] nums) {
        Edition edition = new Edition(year, numPlayers,numTrials, nums);
        editionDAO.save(edition);
    }

    public Edition[] getAll() {
        return editionDAO.getAll();
    }

    /**
     * Lista de la ediciones
     * @return devuelve el nombre de las ediciones para listarlas
     */
    public int[] listaEdition() {
        Edition[] editions;
        editions = editionDAO.getAll();

        int[] names = new int[editions.length];
        for (int i = 0; i < editions.length; i++) {
            names[i] = editions[i].getYear();
        }
        return names;
    }

    /**
     * Acceder a la edicion
     * @param numEdition numero de la edicion que se quiere gestionar
     * @return devuelve la edicion
     */
    public Edition getEdition(int numEdition) {

        return editionDAO.get(numEdition);

    }

    /**
     * Salir de la edicion
     * @param numEdition numero de la edicion que se quiere gestionar
     * @return devuelve si se quiere salir o no de la edicion
     */
    public boolean exitEdition (int numEdition){

        return editionDAO.exitEdition(numEdition);

    }

    /**
     * Eliminar edicion segun el tipo de persistencia que se haya elegido
     * @param numEdition numero de la edicion que se quiere gestionar
     */
    public void deleteEdition(int numEdition) {

        editionDAO.delete(numEdition);

    }

    /**
     * Acceder a la edicion actual
     * @return devuelve la edicion actual
     */
    public Edition getCurrentEdition() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int[] years = listaEdition();
        Edition editionCurrent = null;

        for (int i = 0; i < years.length; i++) {
            if (year == years[i]) {
                editionCurrent = getEdition(i+1);      //la guardamos
            }
        }
        return editionCurrent;
    }

    /**
     * Guardar todos los datos de la edicion actual dependiendo del tipo de persistencia
     * @param edition clase edicion que contiene toda la informacion
     * @param players numero de jugadores
     * @param currentTrial la prueba actual que se esta trabajando
     */
    public void saveCurrentEdition(Edition edition, Player[] players, int currentTrial) {
        editionDAO.saveCurrent(edition, players, currentTrial);

    }

    /**
     * Cargar la edicion actual
     * @return devuelve la edicion actual con todos sus datos y si no hay edicion no devuelve nada
     */
    public Edition loadCurrentEdition(){
        String edition;

        edition = editionDAO.loadCurrent();

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
                        case "1"-> players[i] = new Master(data[j], Integer.parseInt(data[j+1]));
                        case "2"-> players[i] = new Doctor(data[j], Integer.parseInt(data[j+1]));
                    }
                    j = j+3;
                }
                String[] b = data[data.length - 1].split(";");
                int currentTrial = Integer.parseInt(b[0]);
                Edition edition1 = getCurrentEdition();
                edition1.setCurrentTrial(currentTrial);
                edition1.setPlayers(players);

                return edition1;
            }
        }
        return null;
    }

    /**
     * Borrar el archivo de la edicion actual
     */
    public void clearCurrentEdition() {
        editionDAO.emptyCurrent();
    }
}

