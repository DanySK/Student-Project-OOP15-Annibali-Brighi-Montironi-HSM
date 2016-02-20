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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hsm.model.Database;
import org.hsm.model.DatabaseImpl;
import org.hsm.model.GreenHouseType;
import org.hsm.model.Greenhouse;
import org.hsm.model.GreenhouseImpl;
import org.hsm.model.Plant;
import org.hsm.model.PlantModel;
import org.hsm.view.chart.BarChartDialog;
import org.hsm.view.chart.LineChartDialog;
import org.hsm.view.gui.MainFrame;
import org.hsm.view.gui.View;
import org.hsm.view.utility.Utilities;

import controller.update.AutoUpdater;

/**
 * Implementation of Controller Interface.
 *
 */
public final class ControllerImpl implements Controller, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6514503129089230642L;

    /**
     * Singleton istance of controller.
     */
    // CHECKSTYLE:OFF
    private static ControllerImpl CONTROLLER_IMPL;

    private ControllerImpl() {
    };

    private Database database = new DatabaseImpl();
    private Optional<Greenhouse> greenhouse;
    private Optional<AutoUpdater> updater = Optional.empty();
    private final View view = new MainFrame();

    private boolean ghMod;
    private boolean loadGh;
    private boolean dbMod;
    private boolean loadDb = true;

    private boolean updating;
    private boolean updated;
    private int updatetime;

    /**
     *
     * @return the istance of controller
     */
    public static ControllerImpl getController() {
        if (CONTROLLER_IMPL == null) {
            synchronized (ControllerImpl.class) {
                if (CONTROLLER_IMPL == null) {
                    CONTROLLER_IMPL = new ControllerImpl();
                }
            }
        }
        return CONTROLLER_IMPL;
    }

    @Override
    public void createGreenhouse(final String name, final String greenhouseType, final int cost, final int size) {
        this.stopUpdating();
        this.ghMod = true;
        this.loadGh = true;
        if (!this.loadDb) {
            this.database = new DatabaseImpl();
        }
        try {
            this.greenhouse = Optional.of(new GreenhouseImpl(name, size, cost, this.getGreenhouseType(greenhouseType)));
            this.view.setActive(true);
            this.view.insertGreenhouse(name, size, this.greenhouse.get().getCost(), greenhouseType, size, 0, 0,
                    this.greenhouse.get().getCost());
        } catch (IllegalArgumentException e) {
            Utilities.errorMessage(this.view.getFrame(), "Greenhouse name can't be empty");
            this.ghMod = false;
            this.loadGh = false;
        }

    }

    private GreenHouseType getGreenhouseType(final String type) {
        switch (type) {
            case "Linear":
                return GreenHouseType.LINEAR;
            case "Grid":
                return GreenHouseType.GRID;
            case "Pyramidal":
                return GreenHouseType.PYRAMIDAL;
            case "Circular":
                return GreenHouseType.CIRCULAR;
            default:
                return GreenHouseType.LINEAR;
        }
    }

    @Override
    public Greenhouse getGreenhouse() {
        return this.greenhouse.get();
    }

    @Override
    public Database getDatabase() {
        return this.database;
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void newDatabase() {
        this.loadDb = true;
        if (this.dbMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveDatabase();
            }
        }
        this.database = new DatabaseImpl();
        this.view.cleanDatabase();
    }

    @Override
    public void deleteGreenhouse() {
        this.stopUpdating();
        this.checkSave();
        if (!this.loadGh) {
            Utilities.errorMessage(this.view.getFrame(), "No greenhouse is loaded");
        }
        this.greenhouse = Optional.empty();
        this.database = null;
        this.loadGh = false;
        this.loadDb = false;
        this.dbMod = false;
        this.ghMod = false;
        this.view.cleanGreenhouse();
        this.view.cleanDatabase();
        this.view.setActive(false);
    }

    @Override
    public void addPlants(final PlantModel plant, final int cost, final int n) {
        this.checkUpdater();
        this.ghMod = true;
        for (int i = 0; i < n; i++) {
            int id;
            try {
                id = this.greenhouse.get().addPlant(plant, cost);
                this.view.insertNewPlant(id, plant.getName(), cost / 100.0, 0, 0, 0, 0);
            } catch (IllegalStateException e) {
                Utilities.errorMessage(this.view.getFrame(), "Insufficient space in Greenhouse");
            }
        }
        this.restoreUpdater();
    }

    @Override
    public void delPlant() {
        this.checkUpdater();
        try {
            final int id = this.view.getSelectedIDPlant();
            this.ghMod = true;
            this.greenhouse.get().delPlant(id);
            this.view.removeSelectedPlant();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected");
        }
        this.restoreUpdater();
    }

    @Override
    public void delPLants() {
        this.checkUpdater();
        try {
            final int id = this.view.getSelectedIDPlant();
            this.ghMod = true;
            this.greenhouse.get().delPlants(this.greenhouse.get().getPlants().get(id).getModel());
            this.view.cleanGreenhouse();
            this.view.insertGreenhouse(this.greenhouse.get().getName(), this.greenhouse.get().getSize(),
                    this.greenhouse.get().getCost(), this.greenhouse.get().getType().toString(),
                    this.greenhouse.get().getFreeSize(), this.greenhouse.get().getOccSize(),
                    this.greenhouse.get().getNumberOfPlants(), this.greenhouse.get().totalCost());
            for (final Map.Entry<Integer, Plant> elem : this.greenhouse.get().getPlants().entrySet()) {
                this.view.insertPlant(elem.getKey(), elem.getValue().getModel().getName(), elem.getValue().getCost(),
                        elem.getValue().getLastPhValue(), elem.getValue().getLastBrightValue(),
                        elem.getValue().getLastConductValue(), elem.getValue().getLastTempValue());
            }
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected");
        }
        this.restoreUpdater();
    }

    @Override
    public void autoUpdate(final int time) {
        this.updater = Optional.of(new AutoUpdater());
        this.updating = true;
        try {
            this.updater.get().start();
            this.updater.get().setTime(time);
            this.updatetime = time;
        } catch (IllegalThreadStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "Updater already start");
        }
    }

    @Override
    public void stopUpdate() {
        if (!this.updating) {
            return;
        }
        this.updating = false;
        try {
            this.updater.get().interrupt();
        } catch (SecurityException e) {
            Utilities.errorMessage(this.view.getFrame(), "Unable to stop auto Update");
        }
        this.updater = Optional.empty();
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final int ph, final int brightness,
            final int conductivity, final int optimalGrowthTime, final int temperature, final int life,
            final int size) {
        this.dbMod = true;
        try {
            this.database.addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size,
                    conductivity, temperature);
            this.view.insertModelPlant(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                    temperature);
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "This plant is already in database");
        } catch (IllegalArgumentException e) {
            Utilities.errorMessage(this.view.getFrame(), "Pant name can't be empty");
        }

    }

    @Override
    public void deleteDbPlant() {
        try {
            final String botanicalName = this.view.getSelectedBotanicalName();
            this.dbMod = true;
            this.database.removePlantModel(botanicalName);
            this.view.removeSelectedModelPlant();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected");
        }
    }

    @Override
    public boolean getLoadState() {
        return this.loadGh;
    }

    @Override
    public boolean isDbEmpty() {
        if (this.loadGh) {
            return this.database.isEmpty();
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
        this.checkUpdater();
        final Optional<String> filenameGh = this.view.saveGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        this.ghMod = false;
        try (ObjectOutput shmGh = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filenameGh.get())))) {
            shmGh.writeObject(this.greenhouse.get());
            shmGh.close();
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }
        this.restoreUpdater();
    }

    @Override
    public void loadGreenhouse() {
        this.stopUpdating();
        if (this.ghMod) {
            if (Utilities.saveGreenhouseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }
        }
        final Optional<String> filenameGh = this.view.loadGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        if (!this.loadDb) {
            this.database = new DatabaseImpl();
            this.loadDb = true;
        }
        this.loadGh = true;
        try (ObjectInput shmGh = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filenameGh.get())))) {
            try {
                this.greenhouse = Optional.of((Greenhouse) shmGh.readObject());
                shmGh.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), "Nothing to load");
                this.deleteGreenhouse();
                return;
            }
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), "File not found");
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }
        this.view.setActive(true);
        this.view.insertGreenhouse(this.greenhouse.get().getName(), this.greenhouse.get().getSize(),
                this.greenhouse.get().getCost(), this.greenhouse.get().getType().toString(),
                this.greenhouse.get().getFreeSize(), this.greenhouse.get().getOccSize(),
                this.greenhouse.get().getNumberOfPlants(), this.greenhouse.get().totalCost());
        for (final Map.Entry<Integer, Plant> elem : this.greenhouse.get().getPlants().entrySet()) {
            this.view.insertPlant(elem.getKey(), elem.getValue().getModel().getName(), elem.getValue().getCost(),
                    elem.getValue().getLastPhValue(), elem.getValue().getLastBrightValue(),
                    elem.getValue().getLastConductValue(), elem.getValue().getLastTempValue());
        }
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
            shmDb.writeObject(this.database);
            shmDb.close();
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }

    }

    @Override
    public void loadDatabase() {
        if (this.dbMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveDatabase();
            }
        }
        final Optional<String> filenameDb = this.view.importDatabaseDialog();
        if (!filenameDb.isPresent()) {
            return;
        }
        this.loadDb = true;
        try (final ObjectInput shmDb = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filenameDb.get())))) {
            try {
                this.database = (Database) shmDb.readObject();
                shmDb.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), "Nothing to load");
                this.deleteDbPlant();
            }
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), "File not found");
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }
        this.view.cleanDatabase();
        for (final PlantModel elem : this.database.getDb().values()) {
            this.view.insertModelPlant(elem.getName(), elem.getBotanicalName(), elem.getPH(), elem.getBrightness(),
                    elem.getOptimalGrowthTime(), elem.getLife(), elem.getSize(), elem.getConductivity(),
                    elem.getOptimalTemperature());
        }
    }

    @Override
    public void exit() {
        if (this.isDbEmpty()) {
            this.dbMod = false;
        }

        this.checkSave();
        System.exit(0);
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
    public void showBrightnessBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Brightness", "lumen",
                    this.greenhouse.get().getPlants().get(id).getModel().getBrightness(),
                    this.greenhouse.get().getPlants().get(id).getLastBrightValue()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showBrightnessLineChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new LineChartDialog("Brightness", "lumen", this.greenhouse.get().getPlants().get(id).getBrightList())
                    .start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showPhBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Basicity", "ph", this.greenhouse.get().getPlants().get(id).getModel().getPH(),
                    this.greenhouse.get().getPlants().get(id).getLastPhValue()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showPhLineChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new LineChartDialog("Basicity", "ph", this.greenhouse.get().getPlants().get(id).getPhList()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showTemperatureBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Temperature", "Celsius degrees",
                    this.greenhouse.get().getPlants().get(id).getModel().getOptimalTemperature(),
                    this.greenhouse.get().getPlants().get(id).getLastTempValue()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showTemperatureLineChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new LineChartDialog("Temperature", "Celsius degrees",
                    this.greenhouse.get().getPlants().get(id).getTempList()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showConductivityBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Conductivity", "cf",
                    this.greenhouse.get().getPlants().get(id).getModel().getConductivity(),
                    this.greenhouse.get().getPlants().get(id).getLastConductValue()).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showConductivityLineChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new LineChartDialog("Conductivity", "cf", this.greenhouse.get().getPlants().get(id).getConductList())
                    .start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public List<String> getGreenhouseTypes() {
        final List<String> list = new ArrayList<>();
        for (final GreenHouseType elem : GreenHouseType.values()) {
            list.add(elem.toString());
        }
        return list;
    }

    /**
     * Stop auto updater if it runs.
     */
    private void stopUpdating() {
        if (this.updating) {
            this.stopUpdate();
        }
    }

    private void checkUpdater() {
        if (this.updating) {
            this.stopUpdate();
            this.updated = true;
        }
    }

    private void restoreUpdater() {
        if (this.updated) {
            this.autoUpdate(this.updatetime);
            this.updated = false;
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
