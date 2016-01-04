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
     * @param namePlant
     *      name of the plant
     */
    protected void addPlants(final int n, final String namePlant) {
       
    }
    
    /**
     * Delete plant with the ID provided in input.
     * 
     * @param id
     *            Identifier for the plant
     */
    void delPlant(int id){
        
    }

    /**
     * Delete all plants of the same type of plant provided in input.
     * 
     * @param plant
     *            type of plant to be delete
     */
    void delPLants(Plant plant){
        
    }

   
}
