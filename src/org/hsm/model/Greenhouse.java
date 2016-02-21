package org.hsm.model;

import java.util.List;
import java.util.Map;

/**
 * interface for the communication between the model and the controller.
 */
public interface Greenhouse {

    /**
     * Add n plant in the Greenhouse.
     *
     * @param plant
     *            type of plant to add
     * @param cost
     *            cost of the single plant
     * @return id plant
     */
    int addPlant(final PlantModel plant, final int cost);

    /**
     * Remove a single plant from the Greenhouse.
     *
     * @param id
     *            id plant to remove
     */
    void delPlant(final int id);

    /**
     * Remove a kind of plant from the Greenhouse.
     *
     * @param plant
     *            type of plant to remove
     */
    void delPlants(final PlantModel plant);

    /**
     * @return Map<Integer, Plant> return a Map. The key is ID plant. The value
     *         is Plant
     */
    Map<Integer, Plant> getPlants();

    /**
     * @return return name of the greenhouse
     */
    String getName();

    /**
     *
     * @param n
     *            new name of the greenhouse
     */
    void setName(final String n);

    /**
     * @return total size of the greenhouse in m³
     */
    double getSize();

    /**
     * @param s
     *            the size of the greenhouse in m³
     */
    void setSize(int s);

    /**
     * @return Free space of the greenhouse
     */
    double getFreeSize();

    /**
     * @return Return the space occupied by plants in m³
     */
    double getOccSize();

    /**
     * @return return the costGreenhouse field
     */
    double getCost();

    /**
     * @return Number of plants in the greenhouse
     */
    int getNumberOfPlants();

    /**
     * @param cost
     *            cost of the greenhouse
     */
    void setCost(double cost);

    /**
     * @return total cost of the greenhouse (greenhouse cost + plants cost)
     */
    double totalCost();

    /**
     * @return return the type of the greenhouse
     */
    GreenHouseType getType();

    /**
     * @return a map: keys are botanical names, values are quantity
     */
    Map<String, Integer> getCompositionByNumber();

    /**
     * @return a map: Keys are botanical names, values are occupied space by
     *         plants
     */
    Map<String, Double> getCompositionByOccupiedSpace();

    /**
     * Increment the number of refresh.
     */
    void incrementCounter();

    /**
     * @return the number of refresh.
     */
    int getUpdateCounter();

    /**
     * Provide a list of water comsuption of the greenhouse from simulator.
     *
     * @return the list of simulated water comsuption
     */
    List<Double> getSimulatedWaterConsuption();

    /**
     * Provide a list of plants grown from simulator.
     *
     * @return the list of simulated plant grow
     */
    List<Integer> getSimulatedPlantGrow();

    /**
     * Provide a list of water comsuption of the traditional coltivation from
     * simulator.
     *
     * @return the list of water comsuption
     */
    List<Double> getRealWaterConsuption();

    /**
     * Provide a list of plants grown in traditional coltivation from simulator.
     *
     * @return the list of plant grow
     */
    List<Integer> getRealPlantGrow();

}
