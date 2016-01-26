package org.hsm.controller;

import java.util.List;

/**
 *
 * Interface for Greenhouse simulator.
 *
 */

public interface Simulator {
    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Ph
     */
    double getSimulatedPh(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Brightness
     */
    int getSimulatedBrightness(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Conductibility
     */
    int getSimulatedConductibility(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Temperature
     */
    int getSimulatedTemperature(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Ph
     */
    List<Integer> getPhDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Brightness
     */
    List<Integer> getBrightnessDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Conducibility
     */
    List<Integer> getConductibilityDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Temperature
     */
    List<Integer> getTemperatureDays(String botanicalName, int days);

}
