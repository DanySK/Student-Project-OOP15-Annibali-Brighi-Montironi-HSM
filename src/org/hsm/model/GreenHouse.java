package org.hsm.model;

import java.util.Map;

/**
 * interface for the communication between the model and the controller.
 */
public interface GreenHouse {

    /**
     * Add n plant in the Greenhouse.
     * @param n
     *      number of plants to add
     * @param plant
     *      type of plant to add
     */
    void addPlants(final int n, final PlantModel plant, final int cost);

    /**
     * Remove a single plant from the Greenhouse.
     * @param id
     *      id plant to remove
     */
    void delPlant(final int id);

    /**
     * Remove a kind of plant from the Greenhouse.
     * @param plant
     *      type of plant to remove
     */
    void delPlants(final PlantModel plant);
    
    /**
     * @return Map<Integer, Plant>
     *      return a Map. The key is ID plant. The value is Plant
     */
    Map<Integer, Plant> getPlants();

}
