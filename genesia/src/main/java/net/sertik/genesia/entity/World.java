package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joeri
 */
public class World {
  public static final int TILE_WIDTH = 64;
  public static final int TILE_HEIGHT = 32;

  private int numberLands;
  private int numberTilesPerLand;

  private int sizeSqrt;
  private int numberLandsSqrt;
  private int numberTilesPerLandSqrt;

  private String name;
  private List<Land> lands = new LinkedList<Land>();

	private int hoverWorldX = -1;
	private int hoverWorldY = -1;

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

	public int getHoverWorldX() {
		return hoverWorldX;
	}

	public int getHoverWorldY() {
		return hoverWorldY;
	}

	public boolean setHoverCoords(int worldX, int worldY) {
		boolean changed = false;
		if (worldX >= 0 && worldY >= 0 &&
						worldX < sizeSqrt && worldY < sizeSqrt) {
			changed = worldX != this.hoverWorldX || worldY != this.hoverWorldY;
			this.hoverWorldX = worldX;
			this.hoverWorldY = worldY;
		}
		return changed;
	}

  public Tile getTile(int worldX, int worldY) {
    int landX = worldX / numberTilesPerLandSqrt;
    int landY = worldY / numberTilesPerLandSqrt;
    int tileX = worldX % numberTilesPerLandSqrt;
    int tileY = worldY % numberTilesPerLandSqrt;
    return lands.get(landX * numberLandsSqrt + landY).getTiles().get(tileX * numberTilesPerLandSqrt + tileY);
  }
}
