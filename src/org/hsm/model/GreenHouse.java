package org.hsm.model;

/**
 * interface for the communication between the model and the controller.
 */
public interface GreenHouse {
   
    void addPlants(final int n, final Plant plant);
    
    void delPlant(final int id);
    
    void delPLants(final Plant plant);
    
}
