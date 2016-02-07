package org.hsm.view;

/**
 *This interface contains methods to use a specific panel for managing euros.
 *
 */
public interface EuroPanel extends GUIComponent {

    /**
     * Get the values expressed in cents.
     * @return the value expressed in cents.
     */
    int getValue();

}
