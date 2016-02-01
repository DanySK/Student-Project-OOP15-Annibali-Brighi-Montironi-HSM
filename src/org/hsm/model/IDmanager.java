package org.hsm.model;

import java.io.Serializable;

/**Class for manage the ID of the plants in the greenhouse.
 *
 *
 */
public class IDmanager implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;

	/**This method increment the id field and return the new value.
	 * @return id
	 */
	protected int getID() {
		return ++this.id;
	}
}
