package org.hsm.model;

/**Class for manage the ID of the plants in the greenhouse.
 * 
 * 
 */
public class IDmanager {
	
	private int id = 0;
	
	/**This method increment the id field and return the new value.
	 * @return id
	 */
	protected int getID() {
		this.id++;
		return this.id;
	}

}
