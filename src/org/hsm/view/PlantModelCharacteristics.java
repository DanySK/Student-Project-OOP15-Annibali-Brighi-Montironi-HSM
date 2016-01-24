package org.hsm.view;

/**
 *The enum represents all the optimal characteristics of the plant.
 *
 */
public enum PlantModelCharacteristics {

    /**
     *The traditional name of the plant.
     */
    NAME("Name"),
    /**
     *The botanical name of the plant.
     */
    BOTANICAL_NAME("Botanical Name"),
    /**
     *The optimal ph of the plant.
     */
    PH("ph"),
    /**
     *The optimal brightness for the plant.
     */
    BRIGHTNESS("Brigtness"),
    /**
     *The optimal growth time for the plant.
     */
    GROWTH_TIME("Growth Time"),
    /**
     *The days of life of the plant.
     */
    LIFE("Life (Days)"),
    /**
     *The plant size.
     */
    SIZE("Size (cm3)"),
    /**
     *The plant cost.
     */
    COST("Cost (€)"),
    /**
     *The conductivity optimal level for the plant.
     */
    CONDUCTIVITY("Conductivity"),
    /**
     *The temperature of the plant.
     */
    TEMPERATURE("Temperature");

    private final String name;

    PlantModelCharacteristics(final String name) {
        this.name = name;
    }

    /**
     * Get a description of the specific characteristic.
     * @return the string represents the characteristic
     */
    public String getDescription() {
        return this.name;
    }

}
