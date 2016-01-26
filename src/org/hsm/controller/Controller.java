package org.hsm.controller;

import java.io.File;

import org.hsm.model.Database;
import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
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
    void crateGreenhouse(String name, GreenHouseType greenhouseType, double cost, double size);

    /**
     * Get the atcually load Greenhouse.
     *
     * @return the actual Greenhouse
     */
    GreenHouse getGreenhouse();

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
     * Add n plants to Greenhouse loaded.
     *
     * @param nPlants
     *            number of plants to insert in the Greenhouse
     * @param plant
     *            the type of plant to insert
     * @param cost
     *            the cont in euro of the plant
     *
     * @throws IllegalArgumentException
     *             in case of the plant doesn't exist
     */
    void addPlants(int nPlants, PlantModel plant, int cost);

    /**
     * Delete plant with the ID provided in input.
     *
     * @param id
     *            Identifier for the plant
     */
    void delPlant(int id);

    /**
     * Delete all plants of the same type of plant provided in input.
     *
     * @param plant
     *            type of plant to be delete
     */
    void delPLants(PlantModel plant);

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
    void createNewPlant(String name, String botanicalName, double ph, double brightness, double conductivity,
            int optimalGrowthTime, double temperature, int life, double size);

    /**
     * Check the currently state of greenhouse.
     *
     * @return true if a greenhouse is load
     */
    boolean getLoadState();

    /**
     * Save the current Greenhouse opened in the program.
     *
     * @param filename
     *            the path of shm file
     */
    void saveGreenhouse(File filename);

    /**
     * Load a saved Greenhouse in the program.
     *
     * @param filename
     *            the path of shm file
     */
    void loadGreenhouse(File filename);
}
