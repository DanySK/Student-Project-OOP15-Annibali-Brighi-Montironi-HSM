package org.hsm.model;

/**
 * 
 */

public class PlantImp implements Plant {
	
	private final int id;
	private final String name;
	private final  String botanicalName;
	private final int ph;
	private final int brightness;
	private final int optimalGrowthTime;
	private final int life;
	private final int size;
	private final int cost;
	private final int conductivity;
	private final int optimalTemperature;
	
	/**Constructor used by a Builder.
	 * @param id id plant
	 * @param name name plant
	 * @param botanicalName botanical name plant
	 * @param ph optimal ph value for the plant
	 * @param brightness light needed by the plant
	 * @param optimalGrowthTime time growth 
	 * @param life time life
	 * @param size max size for the plant expressed in cm³
	 * @param cost cost 
	 * @param conductivity conductivity 
	 * @param optimalTemperature optimal temperature for the plant
	 * 
	 */
	protected PlantImp(final int id, final  String name, final String botanicalName, final int ph, final int brightness, 
			final int optimalGrowthTime, final int life, final int size, final int cost, final int conductivity, final int optimalTemperature) {
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
		this.conductivity = conductivity;
		this.optimalTemperature = optimalTemperature;
	}

	/**
	 *@return id plant  
	 */
	public int getID() {
		return id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return botanicalName
	 */
	public String getBotanicalName() {
		return botanicalName;
	}

	/**
	 * @return ph
	 */
	public int getPH() {
		return ph;
	}

	/**
	 * @return brightness
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * @return optimalGrowthTime
	 */
	public int getOptimalGrowthTime() {
		return optimalGrowthTime;
	}

	/**
	 * @return life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @return conductivity
	 */
	public int getConductivity() {
		return conductivity;
	}

	/**
	 * @return optimalTemperature
	 */
	public int getOptimalTemperature() {
		return optimalTemperature;
	}
	
	
	
}
