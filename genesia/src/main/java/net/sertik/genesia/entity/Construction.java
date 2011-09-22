package net.sertik.genesia.entity;

/**
 *
 * @author joeri
 */
public class Construction implements GameObject {
  private Tile location;
  private Building building;
  private int stage;

  @Override
  public String getName() {
    return building == null ? null : building.getName();
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  public int getStage() {
    return stage;
  }

  public void setStage(int stage) {
    this.stage = stage;
  }

  public Tile getTile() {
    return location;
  }

  public void setTile(Tile tile) {
    this.location = tile;
  }
}
