package org.hsm.model;

/**Class for manage the ID of the plants in the greenhouse.
 *
 *
 */
public class IDmanager {

	private int id;

	/**This method increment the id field and return the new value.
	 * @return id
	 */
	protected int getID() {
		return ++this.id;
	}
}
