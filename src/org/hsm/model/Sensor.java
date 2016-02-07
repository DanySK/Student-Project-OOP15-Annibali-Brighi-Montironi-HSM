package org.hsm.model;

/**
 * Interface to manage sensor unit in the greenhouse. In particular this
 * interface provides some getter for the plant field in the greenhouse.
 */
public interface Sensor {

    /**
     * this getter return the PH acquired by a sensor near the root of the
     * plant.
     *
     * @param id
     *            is the id field of the plant
     * @return return a integer value of the PH in the "ground"
     */
    int getPH(int id);

    /**
     * this getter return the temperature near the plant.
     *
     * @param id
     *            is the id field of the plant.
     * @return return a integer value of temperature.
     */
    int getTemperature(int id);

}
