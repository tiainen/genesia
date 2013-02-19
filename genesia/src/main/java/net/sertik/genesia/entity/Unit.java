package net.sertik.genesia.entity;

/**
 * Contains the definition and requirements of a single Unit. Units itself
 * won't be part of the game world, but will be represented by a Recruit
 * object instead.
 *
 * @author joeri
 */
public class Unit {
  private String name;
  private int gold;
  private int attack;
  private int defense;
  private int movement;

	/**
	 * Specifies the basic attack amount for this unit. This will be used as a
	 * starting point when calculating the actual attack power. The actual
	 * attack power is for instance influenced by the morale of a recruit or
	 * the type of weapons that have been discovered.
	 *
	 * @return the base attack power
	 */
  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

	/**
	 * Specifies the basic defense amount for this unit. This will be used as a
	 * starting point when calculating the actual defense power. The actual
	 * defense power is for instance influenced by the morale of a recruit or
	 * the type of weapons that have been discovered.
	 *
	 * @return the base defense power
	 */
  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

	/**
	 * Returns the amount of gold required to recruit this unit into an army.
	 *
	 * @return the amount of gold needed to recruit this unit.
	 */
  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

	/**
	 * Returns the total number of movement points available for this unit.
	 * Movement points will be deducted when moving your recruit. A cavalry
	 * unit will for instance have more movement points than an infantry unit
	 * and therefor be able to move further during one turn. At the beginning
	 * of a turn, the movement points of all recruits will be reset to this
	 * amount.
	 *
	 * @return the total movement points for this unit
	 */
  public int getMovement() {
    return movement;
  }

  public void setMovement(int movement) {
    this.movement = movement;
  }

	/**
	 * The name of the Unit.
	 *
	 * @return the name of the unit
	 */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
