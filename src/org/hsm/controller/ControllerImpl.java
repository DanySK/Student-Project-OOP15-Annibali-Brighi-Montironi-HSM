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
import org.hsm.model.GreenhouseImp;
import org.hsm.model.Plant;
import org.hsm.model.PlantModel;
import org.hsm.view.chart.BarChartDialog;
import org.hsm.view.gui.MainFrame;
import org.hsm.view.gui.View;
import org.hsm.view.utility.Utilities;

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

    private Optional<Database> database;
    private Optional<Greenhouse> greenhouse;
    private final View view = new MainFrame();

    private boolean ghMod;
    private boolean loadGh;
    private boolean dbMod;

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
        this.ghMod = true;
        this.loadGh = true;
        this.greenhouse = Optional.of(new GreenhouseImp(name, size, cost, this.getGreenhouseType(greenhouseType)));
        this.database = Optional.of(new DatabaseImpl());
        this.view.setActive(true);
        this.view.insertGreenhouse(name, size, cost, greenhouseType, size, 0, 0);

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
        return this.database.get();
    }

    @Override
    public void newDatabase() {
        if (this.dbMod) {
            if (Utilities.saveDatabaseMessage(this.view.getFrame())) {
                this.saveDatabase();
            }
        }
        this.database = Optional.of(new DatabaseImpl());
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
    public void addPlants(final PlantModel plant, final int cost, final int n) {
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
    public void autoUpdate(final int time) {
        new AutoUpdater(time).start();
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final int ph, final int brightness,
            final int conductivity, final int optimalGrowthTime, final int temperature, final int life,
            final int size) {
        this.dbMod = true;
        try {
            this.database.get().addPlantModel(name, botanicalName, ph, brightness, optimalGrowthTime, life, size,
                    conductivity, temperature);
            this.view.insertModelPlant(name, botanicalName, ph, brightness, optimalGrowthTime, life, size, conductivity,
                    temperature);
        } catch (IllegalArgumentException e) {
            Utilities.errorMessage(this.view.getFrame(), "This plant is already in database");
        }

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
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }

    }

    @Override
    public void loadGreenhouse() {
        if (this.ghMod) {
            if (Utilities.saveGreenhouseMessage(this.view.getFrame())) {
                this.saveGreenhouse();
            }
        }
        final Optional<String> filenameGh = this.view.loadGreenhouseDialog();
        if (!filenameGh.isPresent()) {
            return;
        }
        this.database = Optional.of(new DatabaseImpl());
        this.loadGh = true;
        try {
            ObjectInput shmGh = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameGh.get())));
            try {
                this.greenhouse = Optional.of((Greenhouse) shmGh.readObject());
                shmGh.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), "Nothing to load");
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
                this.greenhouse.get().getNumberOfPlants());
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
            shmDb.writeObject(this.database.get());
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
        try (final ObjectInput shmDb = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filenameDb.get())))) {
            try {
                this.database = Optional.of((Database) shmDb.readObject());
                shmDb.close();
            } catch (ClassNotFoundException e) {
                Utilities.errorMessage(this.view.getFrame(), "Nothing to load");
            }
        } catch (FileNotFoundException e) {
            Utilities.errorMessage(this.view.getFrame(), "File not found");
        } catch (IOException e) {
            Utilities.errorMessage(this.view.getFrame(), e.toString());
        }
        this.view.cleanDatabase();
        for (final PlantModel elem : this.database.get().getDb().values()) {
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
                    this.greenhouse.get().getPlants().get(id).getModel().getBrightness(), 0).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showPhBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Basicity", "ph", this.greenhouse.get().getPlants().get(id).getModel().getPH(), 0)
                    .start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showTemperatureBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Temperature", "Celsius degrees",
                    this.greenhouse.get().getPlants().get(id).getModel().getOptimalTemperature(), 0).start();
        } catch (IllegalStateException e) {
            Utilities.errorMessage(this.view.getFrame(), "No plant is selected!");
        }
    }

    @Override
    public void showConductivityBarChart() {
        try {
            final int id = this.view.getSelectedIDPlant();
            new BarChartDialog("Conductivity", "cf",
                    this.greenhouse.get().getPlants().get(id).getModel().getConductivity(), 0).start();
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
     * Entry Point.
     *
     * @param args
     *            arguments from command line
     */
    public static void main(final String... args) {
        ControllerImpl.getController().view.start();
    }

}
