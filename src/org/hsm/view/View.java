package org.hsm.view;

/**
 *This interface contains all the most important action that an user
 *can do with this application. 
 */
public interface View {

    /**
     * Insert a plant into database.
     * @param plant
     * The plant to insert
     */
     void insertPlant(final Object... plant);

     /**
     *Remove the selected plant into the table.
     */
    void removeSelectedPlant();

    /**
     *Set the Greenhouse in the GUI.
     */
    void setGreenhouse();

}
