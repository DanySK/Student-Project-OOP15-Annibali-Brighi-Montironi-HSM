package org.hsm.controller;

import org.hsm.model.GreenHouse;
import org.hsm.model.GreenHouseType;
import org.hsm.model.Plant;

import javafx.scene.chart.PieChart.Data;

 /**
 * 	Interface for controller operations.
 *
 */

public interface Controller {

	/**
	 * Create a new Greenhouse.
	 *  
	 * @param name
	 * 			the name for Greenhouse
	 * @param greenhouseType
	 * 			the type of greenhouse you want (watch enumeration)
	 * 
	 * @return the Greenhouse create with the type selected
	 */
    GreenHouse crateGreenhouse(String name, GreenHouseType greenhouseType);
	
    /**
     * Delete the Greenhouse actually loaded.
     */
	void deleteGreenhouse();
	
	/**
	 * Add n plants to Greenhouse loaded.
	 * @param nPlants
	 * 			number of plants to insert in the Greenhouse 
	 * @param plant
	 * 			the type of plant to insert
	 * 
	 * @throws IllegalArgumentException
	 * 				in case of the plant doesn't exist
	 */
	void addPlants(int nPlants, Plant plant);
	
	/**
	 * Delete plant with the ID provided in input.
	 * 
	 * @param id
	 * 			Identifier for the plant
	 */
	void delPlant(int id);
	
	/**
	 * Delete all plants of the same type of plant provided in input.
	 * 
	 * @param plant
	 * 			type of plant to be delete
	 */
	void delPLants(Plant plant);
	
	/**
	 * Create a new type of plant for the database.
	 * 
	 * @param name
	 * 			the name of the plant 
	 * @param id
	 * 			the identifier of the plant
	 * @param botanicalName
	 * 			the scientific name for the plant
	 * @param ph
	 * 			optimal ph for the plant
	 * @param brightness
	 * 			optimal brightness for the plant
	 * @param conductibility
	 * 			optimal terrain conductibility for the plant
	 * @param optimalGrowthTime
	 * 			optimal Growth time for the plant
	 * @param temperature
	 * 			optimal temperature for the plant
	 * @param life
	 * 			the life of the plant
	 * @param size
	 * 			the space occupied from the plant in the greenhouse
	 * @param cost
	 * 			the cost in euro of the plant
	 * @return the new Plant
	 */
	Plant createNewPlant(String name, int id, String botanicalName, int ph, int brightness, int conductibility,
						 int optimalGrowthTime, int temperature, Data life, int size, int cost);
	
	/**
	 * Save the current Greenhouse opened in the program.
	 */
	void saveGreenhouse();
	
	/**
	 * Load a saved Greenhouse in the program.
	 */
	void loadGreenhouse();
}