package net.sertik.genesia.entity;

/**
 *
 * @author joeri
 */
public class Recruit implements GameObject {
  private Player owner;
  private Unit unit;
  private Tile location;

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

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
