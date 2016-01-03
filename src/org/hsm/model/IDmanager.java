package org.hsm.model;

public class IDmanager {
	
	private int id = 0;
	
	/**
	 * @return id
	 */
	protected int getID() {
		this.id++;
		return this.id;
	}

}
