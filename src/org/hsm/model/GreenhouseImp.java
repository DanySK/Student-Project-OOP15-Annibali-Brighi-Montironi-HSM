package org.hsm.model;

import java.util.ArrayList;
import java.util.List;

/**
 *implementation of the Greenhouse interface. 
 */
public class GreenhouseImp implements GreenHouse {
    
    private final List<Plant> lp = new ArrayList<>();
    
    /**
     * This method add some plants to the Greenhouse.
     * @param n
     *      number of plants
     * @param plant
     *      an object of type Plant
     */
    protected void addPlants(final int n, final Plant plant) {
       for (int i = 0; i < n; i++) {
           lp.add(plant);
       }
    }
    
    /**
     * Delete plant with the ID provided in input.
     * 
     * @param id
     *            Identifier for the plant
     */
    //TODO check this method (equal()???)
    protected void delPlant(final int id) {
        for (final Plant i : lp) {
            if (i.getID() == id) {
                lp.remove(i);
            }
        }
    }

    /**
     * Delete all plants of the same type of plant provided in input.
     * 
     * @param plant
     *            type of plant to be delete
     */
   protected void delPLants(final Plant plant) {
       for (final Plant i : lp) {
           if (i.getBotanicalName().equals(plant.getBotanicalName())) {
               lp.remove(i);
           }
       }
    }
    
    /**
     * Create a new type of plant for the database.
     * 
     * @param name
     *            the name of the plant
     * @param id
     *            the identifier of the plant
     * @param botanicalName
     *            the scientific name for the plant
     * @param ph
     *            optimal ph for the plant
     * @param brightness
     *            optimal brightness for the plant
     * @param conductibility
     *            optimal terrain conductivity for the plant
     * @param optimalGrowthTime
     *            optimal Growth time for the plant
     * @param temperature
     *            optimal temperature for the plant
     * @param life
     *            the life of the plant
     * @param size
     *            the space occupied from the plant in the greenhouse
     * @param cost
     *            the cost in euro of the plant
     */
    void createNewPlant(String name, int id, String botanicalName, int ph, int brightness, int conductivity,
            int optimalGrowthTime, int temperature, int life, int size, int cost){
        
    }


   
}
