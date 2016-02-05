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
    * @return the Optimal Ph (pH)
    */
    Double getOptimalPh(String botanicalName);

   /**
    *
    * @param botanicalName
    *            the name of the plant
    * @return the Optimal Brightness (lumen)
    */
    Double getOptimalBrightness(String botanicalName);

   /**
    *
    * @param botanicalName
    *            the name of the plant
    * @return the Optimal Conductibility (cF)
    */
    Double getOptimalConductibility(String botanicalName);

   /**
    *
    * @param botanicalName
    *            the name of the plant
    * @return the Optimal Temperature (°C)
    */
    Double getOptimalTemperature(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Ph (pH)
     */
    double getSimulatedPh(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Brightness (lumen)
     */
    double getSimulatedBrightness(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Conductibility (cF)
     */
    double getSimulatedConductibility(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @return the simulated Temperature (°C)
     */
    double getSimulatedTemperature(String botanicalName);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Ph
     */
    List<Double> getPhDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Brightness
     */
    List<Double> getBrightnessDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Conducibility
     */
    List<Double> getConductibilityDays(String botanicalName, int days);

    /**
     *
     * @param botanicalName
     *            the name of the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Temperature
     */
    List<Double> getTemperatureDays(String botanicalName, int days);

}
