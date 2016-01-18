package org.hsm.controller;

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
     *            the type of greenhouse you want (watch enumeration)
     */
    void crateGreenhouse(String name, GreenHouseType greenhouseType, double size);

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
     * @param conductibility
     *            optimal terrain conductibility for the plant
     * @param optimalGrowthTime
     *            optimal Growth time for the plant
     * @param temperature
     *            optimal temperature for the plant
     * @param life
     *            the life of the plant
     * @param size
     *            the space occupied from the plant in the greenhouse
     * @param cost
     *            the cost in euro of the plant
     */
    void createNewPlant(String name, String botanicalName, int ph, int brightness, int conductibility,
            int optimalGrowthTime, int temperature, int life, int size);

    /**
     * Save the current Greenhouse opened in the program.
     */
    void saveGreenhouse();

    /**
     * Load a saved Greenhouse in the program.
     */
    void loadGreenhouse();
}
