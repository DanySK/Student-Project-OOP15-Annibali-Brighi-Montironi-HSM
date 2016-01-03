package org.hsm.model;

/**
 * Plant interface. 
 */

public interface Plant {
		
	public int getID();
	
	public String getName();
	
	public String getBotanicalName();
	
	public int getPH();
	
	public int getBrightness();
	
	public int getOptimalGrowthTime();
	
	public int getLife();
	
	public int getSize();
	
	public int getCost();
	
	public int getConductibility();
	
	public int getOptimalTemperature();
}
