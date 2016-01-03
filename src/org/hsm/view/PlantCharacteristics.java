package org.hsm.view;

/**
 *The enum represents all the characteristics of the plant.
 *
 */
public enum PlantCharacteristics {

    /**
     *The ID of the plant.
     */
    ID("ID"),
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
     *The consucibility optimal level for the plant.
     */
    CONDUCTIBILITY("Conductibility"),
    /**
     *The optimal growth time for the plant.
     */
    OPTIMAL_GROWTH_TIME("Optimal Growth Time"),
    /**
     *The temperature of the plant.
     */
    TEMPERATURE("Temperature"),
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
    COST("Cost (€)");

    private final String name;

    PlantCharacteristics(final String name) {
        this.name = name;
    }

    /**
     * Get a description of the specific enum.
     * @return the string represents the enum
     */
    public String getDescription() {
        return this.name;
    }

}
