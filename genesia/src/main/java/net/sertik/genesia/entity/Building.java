package net.sertik.genesia.entity;

/**
 * Contains the definition and the requirements of a single Building. Buildings
 * objects itself won't be part of a game, instead a Construction object is used
 * to specify built buildings in the game world.
 *
 * @author joeri
 */
public class Building {

	private String name;
	private int gold;
	private int wood;
	private int metal;
	private int stone;
	private boolean requiresArchitect;

	/**
	 * The name of the building.
	 *
	 * @return the name of the building
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the amount of gold required to start the construction of this
	 * building.
	 *
	 * @return the amount of gold needed to build this building
	 */
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * Returns the amount of metal required to start the construction of this
	 * building.
	 *
	 * @return the amount of metal needed to build this building
	 */
	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	/**
	 * Specifies whether this building requires an available architect during
	 * construction.
	 *
	 * @return true when an architect is required to build this building
	 */
	public boolean getRequiresArchitect() {
		return requiresArchitect;
	}

	public void setRequiresArchitect(boolean requiresArchitect) {
		this.requiresArchitect = requiresArchitect;
	}

	/**
	 * Returns the amount of stone required to start the construction of this
	 * building.
	 *
	 * @return the amount of stone needed to build this building
	 */
	public int getStone() {
		return stone;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	/**
	 * Returns the amount of wood required to start the construction of this
	 * building.
	 *
	 * @return the amount of wood needed to build this building
	 */
	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}
}
