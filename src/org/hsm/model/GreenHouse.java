package org.hsm.model;

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
    void addPlants(final int n, final Plant plant);

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

}
