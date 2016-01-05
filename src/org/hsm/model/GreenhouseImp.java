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
    public void addPlants(final int n, final Plant plant) {
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
    public void delPlant(final int id) {
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
   public void delPLants(final Plant plant) {
       for (final Plant i : lp) {
           if (i.getBotanicalName().equals(plant.getBotanicalName())) {
               lp.remove(i);
           }
       }
    }
   
}
