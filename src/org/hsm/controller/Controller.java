package org.hsm.controller;

import java.util.List;

import org.hsm.model.Database;
import org.hsm.model.Greenhouse;
import org.hsm.model.PlantModel;

/**
 * Interface for controller operations.
 *
 */

public interface Controller {
    /**
     * Create a new Greenhouse.
     *
     * @param name
     *            the name for Greenhouse
     * @param greenhouseType
     *            the type of Greenhouse you want (watch enumeration)
     * @param cost
     *            the cost of Greenhouse
     * @param size
     *            the size of Greenhouse
     */
    void createGreenhouse(String name, String greenhouseType, int cost, int size);

    /**
     * Get the atcually load Greenhouse.
     *
     * @return the actual Greenhouse
     */
    Greenhouse getGreenhouse();

    /**
     * Get the Database.
     *
     * @return the Plants Database
     */
    Database getDatabase();

    /**
     * Delete the Greenhouse actually loaded.
     */
    void deleteGreenhouse();

    /**
     * Delete the Greenhouse actually loaded.
     */
    void newDatabase();

    /**
     * Add n plants to Greenhouse loaded.
     *
     * @param plant
     *            the type of plant to insert
     * @param cost
     *            the cont in euro of the plant
     * @param n
     *            number of platns to add
     *
     */
    void addPlants(PlantModel plant, int cost, int n);

    /**
     * Delete the selected plant in the greenhouse.
     *
     */
    void delPlant();

    /**
     * Delete all plants of the same type of plant provided in input.
     *
     * @param plant
     *            type of plant to be delete
     */
    void delPLants(PlantModel plant);

    /**
     * Start the auto updater thread for update plants values evrey time.
     * seconds;
     *
     * @param time
     *            update rate in seconds
     */
    void autoUpdate(int time);

    /**
     * Create a new type of plant for the database.
     *
     * @param name
     *            the name of the plant
     * @param botanicalName
     *            the scientific name for the plant
     * @param ph
     *            optimal ph for the plant
     * @param brightness
     *            optimal brightness for the plant
     * @param conductivity
     *            optimal terrain conductibility for the plant
     * @param optimalGrowthTime
     *            optimal Growth time for the plant
     * @param temperature
     *            optimal temperature for the plant
     * @param life
     *            the life of the plant
     * @param size
     *            the space occupied from the plant in the greenhouse
     */
    void createNewPlant(String name, String botanicalName, int ph, int brightness, int conductivity,
            int optimalGrowthTime, int temperature, int life, int size);

    /**
     *
     * Delete the selected plant id database.
     */
    void deleteDbPlant();

    /**
     * Check the currently state of greenhouse.
     *
     * @return true if a greenhouse is load
     */
    boolean getLoadState();

    /**
     *
     * @return true if Database is empty
     */
    boolean isDbEmpty();

    /**
     *
     * @return true if a Greenhouse is currently load.
     */
    boolean isGhLoad();

    /**
     * Save the current Greenhouse opened in the program.
     *
     */
    void saveGreenhouse();

    /**
     * Load a saved Greenhouse in the program.
     *
     */
    void loadGreenhouse();

    /**
     * Save the current Database.
     *
     */
    void saveDatabase();

    /**
     * Load a saved Database.
     *
     */
    void loadDatabase();

    /**
     * Close the App.
     */
    void exit();

    /**
     * Show Brightness Bar Chart to compare values of the selected plant.
     */
    void showBrightnessBarChart();

    /**
     * Show Ph Bar Chart to compare values of the selected plant.
     */
    void showPhBarChart();

    /**
     * Show Temperature Bar Chart to compare values of the selected plant.
     */
    void showTemperatureBarChart();

    /**
     * Show Conductivity Bar Chart to compare values of the selected plant.
     */
    void showConductivityBarChart();

    /**
     * Get a set with all the greenhouse possible types.
     * @return a set whith all the greenhouse possible types
     */
    List<String> getGreenhouseTypes();

}
