package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joeri
 */
public class World {
  public static final int TILE_WIDTH = 32;
  public static final int TILE_HEIGHT = 16;

  private int numberLands;
  private int numberTilesPerLand;

  private int sizeSqrt;
  private int numberLandsSqrt;
  private int numberTilesPerLandSqrt;

  private String name;
  private List<Land> lands = new LinkedList<Land>();

	private Tile activeTile = null;

  public World(int numberLands, int numberTilesPerLand) {
    this.numberLands = numberLands;
    this.numberTilesPerLand = numberTilesPerLand;
    this.sizeSqrt = (int) Math.sqrt(numberLands * numberTilesPerLand);
    this.numberLandsSqrt = (int) Math.sqrt(numberLands);
    this.numberTilesPerLandSqrt = (int) Math.sqrt(numberTilesPerLand);
  }

  public List<Land> getLands() {
    return lands;
  }

  public void setLands(List<Land> lands) {
    this.lands = lands;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSizeSqrt() {
    return sizeSqrt;
  }

  public void setSizeSqrt(int sizeSqrt) {
    this.sizeSqrt = sizeSqrt;
  }

	public Tile getActiveTile() {
		return activeTile;
	}

	public void setActiveTile(int worldX, int worldY) {
		if (worldX >= 0 && worldY >= 0 &&
						worldX < sizeSqrt && worldY < sizeSqrt) {
			activeTile = getTile(worldX, worldY);
		}
	}

  public Tile getTile(int worldX, int worldY) {
    int landX = worldX / numberTilesPerLandSqrt;
    int landY = worldY / numberTilesPerLandSqrt;
    int tileX = worldX % numberTilesPerLandSqrt;
    int tileY = worldY % numberTilesPerLandSqrt;
    return lands.get(landX * numberLandsSqrt + landY).getTiles().get(tileX * numberTilesPerLandSqrt + tileY);
  }
}
