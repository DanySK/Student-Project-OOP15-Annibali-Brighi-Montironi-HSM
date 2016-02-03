package org.hsm.controller;

import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.GreenhouseImp;
import org.hsm.model.PlantModel;
import org.hsm.view.MainFrame;
import org.hsm.view.Utilities;
import org.hsm.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

    private boolean ghMod;
    private boolean loadGh;
    private boolean dbMod;
    private boolean loadDb;

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
        this.ghMod = true;
        this.loadGh = true;
        this.dbMod = true;
        this.loadDb = true;
        this.greenhouse = new GreenhouseImp(name, size, cost, greenhouseType);
        this.database = new DBplants();
        this.view.setActive(true);
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
    public void deleteDatabase() {
        if (this.loadDb && !this.dbMod) {
            this.database = null;
            this.loadDb = false;
        } else if (this.loadDb && !this.dbMod) {
            // TODO richiesta di salvataggio
            this.database = null;
            this.loadDb = false;
        } else {
            Utilities.errorMessage(((MainFrame) this.view).getFrame(),
                    "Niente da cancellare (nessun Database caricato)");
        }
    }

    @Override
    public void deleteGreenhouse() {
        if (this.loadGh && !this.ghMod) {
            this.greenhouse = null;
            this.loadGh = false;
        } else if (this.loadGh && !this.ghMod) {
            // TODO richiesta di salvataggio
            this.greenhouse = null;
            this.loadGh = false;
        } else {
            Utilities.errorMessage(((MainFrame) this.view).getFrame(),
                    "Niente da cancellare (nessun Greenhouse caricato)");
        }
    }

    @Override
    public void addPlant(final PlantModel plant, final int cost) {
        this.ghMod = true;
        final int id = this.greenhouse.addPlant(plant, cost);
        this.view.insertPlant(id, plant.getName(), cost, 0, 0, 0, 0);
    }

    @Override
    public void delPlant(final int id) {
        this.ghMod = true;
        this.greenhouse.delPlant(id);
        this.view.removeSelectedPlant();
    }

    @Override
    public void delPLants(final PlantModel plant) {
        this.ghMod = true;
        this.greenhouse.delPlants(plant);
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final int ph, final int brightness,
            final int conductivity, final int optimalGrowthTime, final int temperature, final int life,
            final int size) {
        this.database.addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                temperature);
        this.view.insertModelPlant(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,  temperature);
    }

    @Override
    public void deleteDbPlant(final String botanicalName) {
        this.database.removePlantModel(botanicalName);
        this.view.removeSelectedModelPlant();
    }

    @Override
    public boolean getLoadState() {
        return this.loadGh;
    }

    @Override
    public boolean isDbEmpty() {
        return this.database == null ? true : false;
    }

    @Override
    public void saveGreenhouse() {
        final String filenameGh = this.view.saveGreenhouseDialog().get();
        this.ghMod = false;
        try {
            ObjectOutput shmGh = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameGh)));
            shmGh.writeObject(this.greenhouse);
            shmGh.close();
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void loadGreenhouse() {
        final String filenameGh = this.view.loadGreenhouseDialog().get();
        this.ghMod = false;
        try {
            ObjectInput shmGh = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameGh)));
            try {
                this.greenhouse = (GreenHouse) shmGh.readObject();
                shmGh.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), e.toString());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void saveDatabase() {
        final String filenameDb = this.view.exportDatabaseDialog().get();
        try (final ObjectOutput shmDb = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filenameDb)))) {
            shmDb.writeObject(this.database);
            shmDb.close();
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void loadDatabase() {
        final String filenameDb = this.view.importDatabaseDialog().get();
        this.loadDb = true;
        try (final ObjectInput shmDb = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filenameDb)))) {
            try {
                this.database = (Database) shmDb.readObject();
                shmDb.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), e.toString());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
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
