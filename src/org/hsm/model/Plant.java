package org.hsm.model;

import java.util.List;

/**
 *
 *
 *
 */
public interface Plant {

    /**
     *
     * @return the PlantModel
     */
    PlantModel getModel();

    /**
     *
     * @return the cost of the plant
     */
    int getCost();

    /**
     *
     * @return the list of pH values
     */
    List<Double> getPhList();

    /**
     *
     * @return the list of Brightness values
     */
    List<Double> getBrightList();

    /**
     *
     * @return the list of Conductivity values
     */
    List<Double> getConductList();

    /**
     *
     * @return the list of Temperature values
     */
    List<Double> getTempList();

    /**
     *
     * @param value Brightness value (lumen)
     */
    void addBrightValue(final double value);

    /**
     *
     * @param value Countuctivity value (cF)
     */
    void addConductValue(final double value);

    /**
     *
     * @param value Temperatur value (°C)
     */
    void addTempValue(final double value);

    /**
     *
     * @param value pH value (pH)
     */
    void addPhValue(final double value);
}
