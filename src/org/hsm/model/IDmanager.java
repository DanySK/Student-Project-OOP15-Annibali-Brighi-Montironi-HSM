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
		this.id++;
		return this.id;
	}
	
	/**
	 * This method set the value of the "id" field.
	 * @param newID set the new value for the field "id" 
	 */
	protected void setID(final int newID) {
	    this.id = newID;
	}

}
