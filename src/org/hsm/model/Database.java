package org.hsm.model;

/**
 *Interface for DBplants.
 */
public interface Database {

    //TODO aggiungere getter tutti
    /**
     * Add a plant into database.
     * @param name name plant
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
    void addPlantModel(final  String name, final String botanicalName, final int ph, final int brightness,
            final int optimalGrowthTime, final int life, final int size, final int conductivity, final int optimalTemperature);

    public void removePlantModel(final String botanicalName);

    public PlantModel getPlantModel(final String str);
}
