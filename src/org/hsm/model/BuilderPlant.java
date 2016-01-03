package org.hsm.model;

/**
 * Builder pattern.
 */

public class BuilderPlant {
	
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
	
	/**
	 * @param s is the name of the plant.
	 * @return the entire obj. 
	 */
	public BuilderPlant name(final String s) {
		this.name = s;
		return this;
	}
	
	public BuilderPlant ID(final int id) {
		this.id = id;
		return this;
	}
	
	public BuilderPlant botanicalName(final String s) {
		this.botanicalName = s;
		return this;
	}
	
	public BuilderPlant ph(final int ph){
		this.ph = ph;
		return this;
	}
	
	public BuilderPlant name(final int ph) {
		this.ph = ph;
		return this;
	}
	
	public BuilderPlant brightness(final int b) {
		this.brightness = b;
		return this;
	}
	
	public BuilderPlant optimalGrowthTime(final int ogt) {
		this.optimalGrowthTime = ogt;
		return this;
	}
	
	public BuilderPlant life(final int l) {
		this.life = l;
		return this;
	}
	
	public BuilderPlant size(final int s) {
		this.size = s;
		return this;
	}
	
	public BuilderPlant cost(final int c) {
		this.cost = c;
		return this;
	}
	
	public BuilderPlant conductibility(final int c) {
		this.conductibility = c;
		return this;
	}
	
	public BuilderPlant optimalTemperature(final int ot){
		this.optimalTemperature = ot;
		return this;
	}
	
	public Plant build() throws IllegalArgumentException{
		//TODO THROW exception
		return new PlantImp(this.id, this.name, this.botanicalName, this.ph, this.brightness, this.optimalGrowthTime,
							this.life, this.size, this.cost, this.conductibility, this.optimalTemperature);
	}
	
	
}
