package org.hsm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Database of the plants that the user can add to his Greenhouse.
 */
public class DBplants implements Database, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Map<String, PlantModel> db = new HashMap<>();

    /**
     * Add a plant in the database.
     *
     * @param name
     *            name plant
     * @param botanicalName
     *            botanical name plant
     * @param ph
     *            optimal ph value for the plant
     * @param brightness
     *            light needed by the plant
     * @param optimalGrowthTime
     *            time growth
     * @param life
     *            time life
     * @param size
     *            max size for the plant expressed in cm³
     * @param cost
     *            cost
     * @param conductivity
     *            conductivity
     * @param optimalTemperature
     *            optimal temperature for the plant
     */
    @Override
    public void addPlantModel(final String name, final String botanicalName, final int ph, final int brightness,
            final int optimalGrowthTime, final int life, final int size, final int conductivity,
            final int optimalTemperature) {

        final PlantModel p = new BuilderPlant().name(name).botanicalName(botanicalName).ph(ph).brightness(brightness)
                .optimalGrowthTime(optimalGrowthTime).life(life).size(size).conductivity(conductivity)
                .optimalTemperature(optimalTemperature).build();

        db.put(botanicalName, p);

    }

    @Override
    public void removePlantModel(final String botanicalName) {
        this.db.remove(botanicalName);
    }

    @Override
    public PlantModel getPlantModel(final String str) {
        return this.db.get(str);
    }

    @Override
    public Map<String, PlantModel> getDb() {
        return db;
    }

    @Override
    public boolean isEmpty() {
        return db.isEmpty();
    }

}
