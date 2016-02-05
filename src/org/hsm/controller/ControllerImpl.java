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
import org.hsm.view.BarChartDialog;
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
                this.saveDatabase();
            }
        }
        this.database = Optional.of(new DBplants());
        this.view.cleanDatabase();
    }

    @Override
    public void deleteGreenhouse() {
        this.checkSave();
        if (!this.loadGh) {
            Utilities.errorMessage(this.view.getFrame(), "Niente da cancellare (nessun Greenhouse caricato)");
        }
        this.greenhouse = Optional.empty();
        this.database = Optional.empty();
        this.loadGh = false;
        this.view.cleanGreenhouse();
        this.view.cleanDatabase();
        this.view.setActive(false);
    }

    @Override
    public void addPlant(final PlantModel plant, final int cost) {
        this.ghMod = true;
        final int id = this.greenhouse.get().addPlant(plant, cost);
        this.view.insertPlant(id, plant.getName(), cost / 100.0, 0, 0, 0, 0);
    }

    @Override
    public void delPlant() {
        try {
            final int id = this.view.getSelectedIDPlant();
            this.ghMod = true;
            this.greenhouse.get().delPlant(id);
            this.view.removeSelectedPlant();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
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
    public void deleteDbPlant() {
        try {
            final String botanicalName = this.view.getSelectedBotanicalName();
            this.dbMod = true;
            this.database.get().removePlantModel(botanicalName);
            this.view.removeSelectedModelPlant();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public boolean getLoadState() {
        return this.loadGh;
    }

    @Override
    public boolean isDbEmpty() {
        if (this.loadGh) {
            return this.database.get().isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public boolean isGhLoad() {
        return this.loadGh;
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
        final Optional<String> filenameGh = this.view.loadGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        this.database = Optional.of(new DBplants());
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
        this.dbMod = false;
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
        final Optional<String> filenameDb = this.view.importDatabaseDialog();
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
        this.view.insertDatabase();
    }

    @Override
    public void exit() {
        if (this.isDbEmpty()) {
            this.dbMod = false;
        }

        this.checkSave();

        if (Utilities.exitMessage(this.view.getFrame())) {
            System.exit(0);
        }
    }

    /**
     * Check if a save is necessary.
     */
    private void checkSave() {

        if (this.loadGh && this.ghMod && !this.dbMod) {
            if (Utilities.saveGreenhouseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }

        } else if (this.loadGh && this.dbMod && !this.ghMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveDatabase();
            }

        } else if (this.loadGh && this.dbMod && this.ghMod) {
            if (Utilities.saveGreenhouseAndDBMessage(this.view.getFrame())) {
                this.saveDatabase();
                this.saveGreenhouse();
            }
        }
    }

    @Override
    public void showBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog(this.view.getFrame(), id).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
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
