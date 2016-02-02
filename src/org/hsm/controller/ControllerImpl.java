package org.hsm.controller;

import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.GreenhouseImp;
import org.hsm.model.PlantModel;
import org.hsm.view.MainFrame;
import org.hsm.view.View;

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
    private final View view = new MainFrame();

    private boolean load;

    /**
     *
     * @return the istance of controller
     */
    public static ControllerImpl getController() {
        return CONTROLLER_IMPL;
    }

    @Override
    public void createGreenhouse(final String name, final GreenHouseType greenhouseType, final int cost,
            final double size) {
        this.greenhouse = new GreenhouseImp(name, size, cost, greenhouseType);
        this.database = new DBplants();
        this.load = true;
        this.view.insertGreenhouse();
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
    public int addPlant(final PlantModel plant, final int cost) {
        return this.greenhouse.addPlant(plant, cost);
    }

    @Override
    public void delPlant(final int id) {
        this.greenhouse.delPlant(id);
    }

    @Override
    public void delPLants(final PlantModel plant) {
        this.greenhouse.delPlants(plant);
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final double ph, final double brightness,
            final double conductivity, final int optimalGrowthTime, final double temperature, final int life,
            final double size) {
        this.database.addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                temperature);
        this.view.insertModelPlant(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                temperature);
    }

    @Override
    public void deleteDbPlant(final String botanicalName) {
        this.database.removePlantModel(botanicalName);
        this.view.removeSelectedModelPlant();
    }

    @Override
    public boolean getLoadState() {
        return this.load;
    }

    @Override
    public void saveGreenhouse(final File filenameDb, final File filenameGh) {
        try {
            ObjectOutput shmDb = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameDb)));
            ObjectOutput shmGh = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameGh)));
            shmDb.writeObject(this.database);
            shmDb.close();
            shmGh.writeObject(this.greenhouse);
            shmGh.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void loadGreenhouse(final File filenameDb, final File filenameGh) {
        try {
            ObjectInput shmDb = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameDb)));
            ObjectInput shmGh = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameGh)));
            try {
                this.database = (Database) shmDb.readObject();
                shmDb.close();
                this.greenhouse = (GreenHouse) shmGh.readObject();
                shmGh.close();
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

    /**
     * Entry Point.
     *
     * @param args
     *            arguments from command line
     */
    public static void main(final String... args) {
        ControllerImpl.getController().view.start();
    }

}
