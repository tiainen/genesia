package net.sertik.genesia.entity;

/**
 * A recruit is an actual unit being recruited in the game. In order to be able
 * to recruit a unit, the player will need the required gold and have an
 * unemployed inhabitant available.
 *
 * @author joeri
 */
public class Recruit implements GameObject {

	private Player owner;
	private Unit unit;
	private Tile location;

	/**
	 * Returns the player that owns the recruit. In contrast to Buildings, where
	 * the owner is decided by the owner of the Land that building is located in,
	 * a recruit is always owned by the same player that recruited it in the first
	 * place.
	 *
	 * @return the player that owns the recruit
	 */
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Returns the unit that this recruit represents.
	 *
	 * @return the unit representing this recruit
	 */
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * Defines the location of the recruit within the game world.
	 *
	 * @return the location of this recruit in the game world
	 */
	public Tile getLocation() {
		return location;
	}

	public void setLocation(Tile location) {
		this.location = location;
	}

	@Override
	public String getName() {
		return unit == null ? null : unit.getName();
	}
}
