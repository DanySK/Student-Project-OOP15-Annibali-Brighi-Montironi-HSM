package org.hsm.controller;

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
import java.util.Optional;

import org.hsm.model.DBplants;
import org.hsm.model.Database;
import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.GreenhouseImp;
import org.hsm.model.PlantModel;
import org.hsm.view.MainFrame;
import org.hsm.view.Utilities;
import org.hsm.view.View;

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

    private Optional<Database> database;
    private Optional<GreenHouse> greenhouse;
    private final View view = new MainFrame();

    private boolean ghMod;
    private boolean loadGh;
    private boolean dbMod;

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
        if (this.loadGh && this.ghMod) {
            if (Utilities.saveGreenhouseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }
        }
        this.ghMod = true;
        this.loadGh = true;
        this.dbMod = true;
        this.greenhouse = Optional.of(new GreenhouseImp(name, size, cost, greenhouseType));
        this.database = Optional.of(new DBplants());
        this.view.setActive(true);
        this.view.insertGreenhouse();
    }

    @Override
    public GreenHouse getGreenhouse() {
        return this.greenhouse.get();
    }

    @Override
    public Database getDatabase() {
        return this.database.get();
    }

    @Override
    public void newDatabase() {
        if (this.dbMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }
        }
        this.database = null;
        this.database = Optional.of(new DBplants());
        this.view.cleanDatabase();
    }

    @Override
    public void deleteGreenhouse() {
        if (this.loadGh && !this.ghMod && !this.dbMod) {
            this.greenhouse = Optional.ofNullable(null);
            this.database = Optional.ofNullable(null);
            this.loadGh = false;
            this.view.cleanGreenhouse();
            this.view.setActive(false);
        } else if (this.loadGh && this.ghMod) {
            if (Utilities.saveGreenhouseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }
            this.greenhouse = Optional.ofNullable(null);
            this.database = Optional.ofNullable(null);
            this.loadGh = false;
            this.view.cleanGreenhouse();
            this.view.setActive(false);
        } else if (this.loadGh && this.dbMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveDatabase();
            }
            this.greenhouse = Optional.ofNullable(null);
            this.database = Optional.ofNullable(null);
            this.loadGh = false;
            this.view.cleanGreenhouse();
            this.view.setActive(false);

        } else if (this.loadGh && this.dbMod && this.ghMod) {
            if (Utilities.saveGreenhouseAndDBMessage(this.view.getFrame())) {
                this.saveDatabase();
                this.saveGreenhouse();
            }
            this.greenhouse = Optional.ofNullable(null);
            this.database = Optional.ofNullable(null);
            this.loadGh = false;
            this.view.cleanGreenhouse();
            this.view.setActive(false);

        } else {
            Utilities.errorMessage(this.view.getFrame(),
                    "Niente da cancellare (nessun Greenhouse caricato)");
        }
    }

    @Override
    public void addPlant(final PlantModel plant, final int cost) {
        this.ghMod = true;
        final int id = this.greenhouse.get().addPlant(plant, cost);
        this.view.insertPlant(id, plant.getName(), cost / 100.0, 0, 0, 0, 0);
    }

    @Override
    public void delPlant(final int id) {
        this.ghMod = true;
        this.greenhouse.get().delPlant(id);
        this.view.removeSelectedPlant();
    }

    @Override
    public void delPLants(final PlantModel plant) {
        this.ghMod = true;
        this.greenhouse.get().delPlants(plant);
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final int ph, final int brightness,
            final int conductivity, final int optimalGrowthTime, final int temperature, final int life,
            final int size) {
        this.dbMod = true;
        this.database.get().addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size,
                conductivity, temperature);
        this.view.insertModelPlant(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                temperature);
    }

    @Override
    public void deleteDbPlant(final String botanicalName) {
        this.dbMod = true;
        this.database.get().removePlantModel(botanicalName);
        this.view.removeSelectedModelPlant();
    }

    @Override
    public boolean getLoadState() {
        return this.loadGh;
    }

    @Override
    public boolean isDbEmpty() {
        return this.database == null ? true : false; // TODO modello
                                                     // database.isempty
    }

    @Override
    public void saveGreenhouse() {
        final Optional<String> filenameGh = this.view.saveGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        this.ghMod = false;
        try {
            ObjectOutput shmGh = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(filenameGh.get())));
            shmGh.writeObject(this.greenhouse.get());
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
        final Optional<String> filenameGh = this.view.saveGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        this.database = Optional.of(new DBplants());
        this.ghMod = false;
        this.loadGh = true;
        try {
            ObjectInput shmGh = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameGh.get())));
            try {
                this.greenhouse = Optional.of((GreenHouse) shmGh.readObject());
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
        this.view.setActive(true);
        this.view.insertGreenhouse();

    }

    @Override
    public void saveDatabase() {
        final Optional<String> filenameDb = this.view.exportDatabaseDialog();
        if (!filenameDb.isPresent()) {
            return;
        }
        try (final ObjectOutput shmDb = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filenameDb.get())))) {
            shmDb.writeObject(this.database.get());
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
        final Optional<String> filenameDb = this.view.exportDatabaseDialog();
        if (!filenameDb.isPresent()) {
            return;
        }
        try (final ObjectInput shmDb = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filenameDb.get())))) {
            try {
                this.database = Optional.of((Database) shmDb.readObject());
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
