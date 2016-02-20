package controller.simulator;

import java.util.List;

import org.hsm.model.Plant;

/**
 *
 * Interface for Greenhouse simulator.
 *
 */

public interface Simulator {

    /**
     *
     * @param plant
     *            the plant
     * @return the Optimal Ph (pH)
     */
    Double getOptimalPh(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the Optimal Brightness (lumen)
     */
    Double getOptimalBrightness(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the Optimal Conductibility (cF)
     */
    Double getOptimalConductibility(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the Optimal Temperature (°C)
     */
    Double getOptimalTemperature(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the simulated Ph (pH)
     */
    double getSimulatedPh(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the simulated Brightness (lumen)
     */
    double getSimulatedBrightness(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the simulated Conductibility (cF)
     */
    double getSimulatedConductibility(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @return the simulated Temperature (°C)
     */
    double getSimulatedTemperature(Plant plant);

    /**
    *
    * @param plant
    *            the plant
    * @return the real simulated Ph (pH)
    */
   double getRealPh(Plant plant);

   /**
    *
    * @param plant
    *            the plant
    * @return the real simulated Brightness (lumen)
    */
   double getRealBrightness(Plant plant);

   /**
    *
    * @param plant
    *            the plant
    * @return the real simulated Conductibility (cF)
    */
   double getRealConductibility(Plant plant);

   /**
    *
    * @param plant
    *            the plant
    * @return the real simulated Temperature (°C)
    */
   double getRealTemperature(Plant plant);

    /**
     *
     * @param plant
     *            the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Ph
     */
    List<Double> getPhDays(Plant plant, int days);

    /**
     *
     * @param plant
     *            the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Brightness
     */
    List<Double> getBrightnessDays(Plant plant, int days);

    /**
     *
     * @param plant
     *            the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Conducibility
     */
    List<Double> getConductibilityDays(Plant plant, int days);

    /**
     *
     * @param plant
     *            the plant
     * @param days
     *            the dimension of array data in days
     * @return the List of simulated values of Temperature
     */
    List<Double> getTemperatureDays(Plant plant, int days);

}
