package org.hsm.view;

/**
 *The enum represents all the characteristics of the greenhouse.
 *
 */
public enum GreenhouseCharacteristics {

    /**
     *The greenhouse name.
     */
    NAME("Name"),
    /**
     *The greenhouse dimension (m3).
     */
    DIMENSION("Dimension (m3)"),
    /**
     *The greenhouse used space (m3).
     */
    USED_SPACE("Used Space (m3)"),
    /**
     *The greenhouse free space (m3).
     */
    FREE_SPACE("Free Space (m3)"),
    /**
     *The number of plants inside the greenhouse.
     */
    NUMBER_OF_PLANTS("Number of Plants"),
    /**
     *The typology of the greenhouse.
     */
    TYPOLOGY("Typology");

    private final String name;

    GreenhouseCharacteristics(final String name) {
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
