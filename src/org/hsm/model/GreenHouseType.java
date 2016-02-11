package org.hsm.model;

/**
 *
 * ENUM Greenhouse types.
 *
 */
public enum GreenHouseType {
    /**
     * Type of disposition of the plants in the greenhouse.
     *
     */
    GRID("Grid"),
    LINEAR("Linear"),
    CIRCULAR("Circular"),
    PYRAMIDAL("Pyramidal");
    
    private final String name;
    
    private GreenHouseType(final String n){
        this.name= n;
    }
    
    public String toString(){
        return this.name();
    }

}
