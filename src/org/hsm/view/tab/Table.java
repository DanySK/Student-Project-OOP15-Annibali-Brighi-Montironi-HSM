package org.hsm.view.tab;

import org.hsm.view.gui.GUIComponent;

/**
 * This interface contains the basic methods to operate on a table.
 *
 * @param <X> the row identifier type
 */
public interface Table<X> extends GUIComponent {

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
     * Get the unique identifier of the row.
     * @return the identifier of the row
     * @throws IllegalStateException no row is selected
     */
    X getSelectedRowIdentifier() throws IllegalStateException;

    /**
     *Clean the table.
     */
    void clean();

}
