package org.hsm.view;

/**
 *This interface contains all the most important action that an user
 *can do with this application. 
 */
public interface UserInteface {

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

}
