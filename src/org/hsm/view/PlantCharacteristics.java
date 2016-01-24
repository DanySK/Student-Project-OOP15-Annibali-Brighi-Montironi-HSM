package org.hsm.view;

/**
 *The enum represents all the current characteristics of a plant in a greenhouse.
 *
 */
public enum PlantCharacteristics {

    /**
     *The personal id of the plant in the greenhouse.
     */
    ID("ID"),
    /**
     *The typology of the plant (model).
     */
    TYPE("Type"),
    /**
     *The single cost of the plant.
     */
    COST("Cost (€)"),
    /**
     *The current ph of the plant.
     */
    PH("ph"),
    /**
     *The current value of brightness of the plant.
     */
    BRIGHTNESS("Brightness"),
    /**
     *The current value of conductivity of the plant. 
     */
    CONDUCTIVITY("Conductivity"),
    /**
     *The current temperature of the plant.
     */
    TEMPERATURE("Temperature");

    private final String name;

    PlantCharacteristics(final String name) {
        this.name = name;
    }

    /**
     * Get a description of the specific characteristic.
     * @return the string represents the characteristic
     */
    public String getDescriprion() {
        return this.name;
    }

}
