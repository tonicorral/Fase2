package com.company.business;

public class BusinessFacadeImpl implements BusinessFacade{
    private final TrialManager trialManager;        //Solo una vez
    private final EditionManager editionManager;
    private final ConductorManager conductorManager;

    public BusinessFacadeImpl() {
        trialManager = new TrialManager();
        editionManager = new EditionManager();
        conductorManager = new ConductorManager();
    }

    @Override   //Le passa los datos al manager para crear la prueba
    public void createTrial(String trialName, String trialJournal, String quartile, int acc, int rev, int rej) {
        trialManager.crearPruebaPublicacion(trialName, trialJournal, quartile, acc, rev, rej);
    }

    @Override
    public String[] showTrialsName() {

        return trialManager.listaPruebas();
    }

    @Override
    public String[] trialInfo(int numberTrial) {

        return trialManager.infoPrueba(numberTrial);
    }


}
