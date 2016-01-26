package org.hsm.controller;

import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.GreenhouseImp;
import org.hsm.model.PlantModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.hsm.model.DBplants;
import org.hsm.model.Database;

/**
 * Implementation of Controller Interface.
 *
 */
public class ControllerImpl implements Controller, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6514503129089230642L;

    /**
     * Singleton istance of controller.
     */
    private static final ControllerImpl CONTROLLER_IMPL = new ControllerImpl();

    private Database database;
    private GreenHouse greenhouse;

    private boolean load;

    /**
     *
     * @return the istance of controller
     */
    public static ControllerImpl getController() {
        return CONTROLLER_IMPL;
    }

    @Override
    public void crateGreenhouse(final String name, final GreenHouseType greenhouseType, final double cost) {
        this.greenhouse = new GreenhouseImp();
        this.database = new DBplants();
        greenhouse.setName(name);
        this.load = true;
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
        if (this.load) {
            this.database = null;
            this.greenhouse = null;
            this.load = false;
        } else {
            // TODO richiesta di salvataggio
        }
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
    public boolean getLoadState() {
        return this.load;
    }

    @Override
    public void saveGreenhouse(final File filename) {
        try {
            ObjectOutput shm = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            shm.writeObject(CONTROLLER_IMPL);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void loadGreenhouse(final File filename) {
        try {
            ObjectInput shm = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
            try {
                shm.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
