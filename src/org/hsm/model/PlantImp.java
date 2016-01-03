package org.hsm.model;

/**
 * 
 */

public class PlantImp implements Plant {
	
	private int id;
	private String name;
	private String botanicalName;
	private int ph;
	private int brightness;
	private int optimalGrowthTime;
	private int life;
	private int size;
	private int cost;
	private int conductibility;
	private int optimalTemperature;
	
	protected PlantImp(final int id, final  String name, final String botanicalName, final int ph, final int brightness, 
			final int optimalGrowthTime, final int life, final int size, final int cost, final int conductibility, final int optimalTemperature) {
		super();
		this.id = id;
		this.name = name;
		this.botanicalName = botanicalName;
		this.ph = ph;
		this.brightness = brightness;
		this.optimalGrowthTime = optimalGrowthTime;
		this.life = life;
		this.size = size;
		this.cost = cost;
		this.conductibility = conductibility;
		this.optimalTemperature = optimalTemperature;
	}
	
	
	
}
