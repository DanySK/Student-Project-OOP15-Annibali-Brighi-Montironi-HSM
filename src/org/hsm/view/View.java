package org.hsm.view;

/**
 *This interface contains all the most important action that an user
 *can do with this application. 
 */
public interface View {

    /**
     *Set the GUI status.
     *@param status the status of thr GUI
     */
    void setActive(final boolean status);
    /**
     * Insert a plant into greenhouse.
     * @param plant
     * The plant to insert
     */
     void insertPlant(final Object... plant);

     /**
     *Remove the selected plant into the table.
     */
    void removeSelectedPlant();

    /**
     * Insert a model plant into database.
     * @param plant
     * The plant to insert
     */
     void insertModelPlant(final Object... plant);

     /**
     *Remove the selected model plant into the database table.
     */
    void removeSelectedModelPlant();

}
