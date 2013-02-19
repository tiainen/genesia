package net.sertik.genesia.entity;

/**
 * A Construction is an actual building being constructed in the game world. In
 * order to be able to start a construction, the player will need the required
 * resources of the building.
 *
 * @author joeri
 */
public class Construction implements GameObject {
	public enum Stage {
		GROUNDBREAKING, HALFWAY, NEAR_COMPLETION, FINISHED
	}

  private Tile location;
  private Building building;
  private Stage stage;

  @Override
  public String getName() {
    return building == null ? null : building.getName();
  }

	/**
	 * Returns the building that this construction represents.
	 *
	 * @return the building representing this construction
	 */
  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

	/**
	 * Returns the stage of the construction process. A construction doesn't have
	 * to go through every stage, although Stage.GROUNDBREAKING and
	 * Stage.FINISHED will always be part of the construction process. A
	 * construction can process to the next stage when a season ends and
	 * an architect was present during the construction.
	 *
	 * @return the stage of the construction process
	 */
  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

	/**
	 * Defines the location of the construction within the game world.
	 *
	 * @return the location of this construction in the game world
	 */
  public Tile getTile() {
    return location;
  }

  public void setTile(Tile tile) {
    this.location = tile;
  }
}
