package org.hsm.controller;

import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.GreenhouseImp;
import org.hsm.model.PlantModel;
import org.hsm.model.DBplants;
import org.hsm.model.Database;

/**
 * Implementation of Controller Interface.
 *
 */
public class ControllerImpl implements Controller {

    /**
     * Singleton istance of controller.
     */
    private static final ControllerImpl CONTROLLER_IMPL = new ControllerImpl();

    private Database database;
    private GreenHouse greenhouse;
    private String greenhouseName;

    /**
     *
     * @return the istance of controller
     */
    public static ControllerImpl getController() {
        return CONTROLLER_IMPL;
    }

    @Override
    public void crateGreenhouse(final String name, final GreenHouseType greenhouseType, final double cost) {
        greenhouse = new GreenhouseImp();
        database = new DBplants();
        this.greenhouseName = name;
    }

    @Override
    public GreenHouse getGreenhouse() {
        return this.greenhouse;
    }

    @Override
    public Database getDatabase() {
        return this.database;
    }

    @Override
    public void deleteGreenhouse() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addPlants(final int nPlants, final PlantModel plant, final int cost) {
        greenhouse.addPlants(nPlants, plant, cost);
    }

    @Override
    public void delPlant(final int id) {
        greenhouse.delPlant(id);
    }

    @Override
    public void delPLants(final PlantModel plant) {
        greenhouse.delPlants(plant);
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final double ph, final double brightness,
            final double conductivity, final int optimalGrowthTime, final double temperature, final int life,
            final double size) {
        database.addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                temperature);
    }

    @Override
    public void saveGreenhouse() {
        // TODO Auto-generated method stub
    }

    @Override
    public void loadGreenhouse() {
        // TODO Auto-generated method stub
    }

}
