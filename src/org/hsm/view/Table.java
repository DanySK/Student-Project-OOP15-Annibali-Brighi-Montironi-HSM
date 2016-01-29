package org.hsm.view;

/**
 *This interface contains the basic methods to operate on a table.
 *
 */
public interface Table {

    /**
     * Insert row in the table.
     * @param row the row to insert
     * 
     */
    void insertRow(final Object... row);

    /**
     *Remove the selected row into the table.
     */
    void removeSelectedRow();

    /**
     *Clean the table.
     */
    void clean();

}
