package org.hsm.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Database of the plants that the user can add to his Greenhouse.
 */
public class DBplants implements Database {
    
    private Map<String, Plant> db = new TreeMap<>();

    /**
     *Add a plant in the database. 
     *@param name name plant
     * @param botanicalName botanical name plant
     * @param ph optimal ph value for the plant
     * @param brightness light needed by the plant
     * @param optimalGrowthTime time growth 
     * @param life time life
     * @param size max size for the plant expressed in cm³
     * @param cost cost 
     * @param conductivity conductivity 
     * @param optimalTemperature optimal temperature for the plant
     */
    public void addDB(final  String name, final String botanicalName, final int ph, final int brightness, 
            final int optimalGrowthTime, final int life, final int size, final int cost, final int conductivity, final int optimalTemperature) {
        
        final Plant p = new BuilderPlant()
                        .iD(0)
                        .name(name)
                        .botanicalName(botanicalName)
                        .ph(ph)
                        .brightness(brightness)
                        .optimalGrowthTime(optimalGrowthTime)
                        .life(life)
                        .size(size)
                        .cost(cost)
                        .conductivity(conductivity)
                        .optimalTemperature(optimalTemperature)
                        .build();
        
        db.put(botanicalName, p);
        
                                        
    }
}
