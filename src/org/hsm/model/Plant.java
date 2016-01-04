package org.hsm.model;

/**
 * Plant interface. 
 */

public interface Plant {

	/**
	 * @return 
	 * 		return the id of the plant
	 */
	int getID();
	
	/**
	 * @return 
	 * 		return the name of the plant
	 */
	String getName();
	
	/**
	 * @return 
	 * 		return the botanical name of the plant
	 */
	String getBotanicalName();

	/**
	 * @return 
	 * 		return the optimal PH for the plant 
	 */
	int getPH();

	/**
	 * @return
	 * 		return the light needed by the plant
	 */
	int getBrightness();

	/**
	 * @return
	 * 		return the growth time of the plant 
	 */
	int getOptimalGrowthTime();

	/**
	 * @return 
	 * 		return 
	 */
	int getLife();

	/**
	 * @return 
	 * 		return the maximum size of the plant expressed in cm³
	 */
	int getSize();

	/**
	 * @return 
	 * 		return the cost of the plant
	 */
	int getCost();

	/**
	 * @return 
	 * 		return the conductivity needed by the plant
	 */
	int getConductivity();

	/**
	 * @return 
	 * 		return the optimal temperature for the plant
	 */
	int getOptimalTemperature();
}
