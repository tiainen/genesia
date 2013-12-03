package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * The world is the root object of the game. It consists of a series of lands
 * that are in their turn split up into a number of tiles.
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
	private List<Land> lands = new LinkedList<>();

	private int hoverWorldX = -1;
	private int hoverWorldY = -1;

	/**
	 * Constructs a new World with the specified number of lands and tiles per
	 * land.
	 *
	 * @param numberLands
	 * @param numberTilesPerLand
	 */
	public World(int numberLands, int numberTilesPerLand) {
		this.numberLands = numberLands;
		this.numberTilesPerLand = numberTilesPerLand;
		this.sizeSqrt = (int) Math.sqrt(numberLands * numberTilesPerLand);
		this.numberLandsSqrt = (int) Math.sqrt(numberLands);
		this.numberTilesPerLandSqrt = (int) Math.sqrt(numberTilesPerLand);
	}

	/**
	 * Returns a list of Lands that are part of this World.
	 *
	 * @return a list of lands that are part of the world
	 */
	public List<Land> getLands() {
		return lands;
	}

	public void setLands(List<Land> lands) {
		if (lands.size() == numberLands) {
			this.lands = lands;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the total number of lands in this world.
	 *
	 * @return the number of lands of this world
	 */
	public int getNumberLands() {
		return numberLands;
	}

	public void setNumberLands(int numberLands) {
		this.numberLands = numberLands;
	}

	/**
	 * Returns the number of tiles that exist in each land.
	 *
	 * @return the number of tiles per land
	 */
	public int getNumberTilesPerLand() {
		return numberTilesPerLand;
	}

	public void setNumberTilesPerLand(int numberTilesPerLand) {
		this.numberTilesPerLand = numberTilesPerLand;
	}

	/**
	 * Returns the square root of the total number of tiles that will be available
	 * in the entire world.
	 *
	 * @return the square root of the total number of tiles of all lands in this
	 * world
	 */
	public int getSizeSqrt() {
		return sizeSqrt;
	}

	/**
	 * Returns the square root of the number of lands in this world.
	 *
	 * @return the square root of the number of lands
	 */
	public int getNumberLandsSqrt() {
		return numberLandsSqrt;
	}

	/**
	 * Returns the square root of the number of tiles per land.
	 *
	 * @return the square root of the number of tiles per land
	 */
	public int getNumberTilesPerLandSqrt() {
		return numberTilesPerLandSqrt;
	}

	/**
	 * Checks whether the provided coordinates are within the bounds of the world.
	 *
	 * @param worldX the x coordinate
	 * @param worldY the y coordinate
	 * @return true if the provided coordinates lie within the world bounds
	 */
	public boolean isPointWithinBounds(int worldX, int worldY) {
		return worldX >= 0
						&& worldY >= 0
						&& worldX < sizeSqrt
						&& worldY < sizeSqrt;
	}

	/**
	 * Returns the x-coordinate of the tile where the mouse is currently hovering
	 * over in world coordinates.
	 *
	 * @return the x-coordinate in the world of the tile that is being hovered
	 */
	public int getHoverWorldX() {
		return hoverWorldX;
	}

	/**
	 * Returns the y-coordinate of the tile where the mouse is currently hovering
	 * over in world coordinates.
	 *
	 * @return the y-coordinate in the world of the tile that is being hovered
	 */
	public int getHoverWorldY() {
		return hoverWorldY;
	}

	/**
	 * Sets the current x- and y-coordinates of the tile where the mouse is
	 * currently hovering over. The coordinates are world coordinates.
	 *
	 * @param worldX the x world coordinate of the hovered tile
	 * @param worldY the y world coordinate of the hovered tile
	 * @return true when the x- or y-coordinate were different than before this
	 * call
	 */
	public boolean setHoverCoords(int worldX, int worldY) {
		boolean changed = false;
		if (isPointWithinBounds(worldX, worldY)) {
			changed = worldX != this.hoverWorldX || worldY != this.hoverWorldY;
			this.hoverWorldX = worldX;
			this.hoverWorldY = worldY;
		}
		return changed;
	}

	/**
	 * Retrieves the tile that is located at the specified x- and y-coordinate.
	 * These coordinates are world coordinates.
	 *
	 * @param worldX the x world coordinate
	 * @param worldY the y world coordinate
	 * @return the tile located at these coordinates
	 */
	public Tile getTile(int worldX, int worldY) {
		int landX = worldX / numberTilesPerLandSqrt;
		int landY = worldY / numberTilesPerLandSqrt;
		int tileX = worldX % numberTilesPerLandSqrt;
		int tileY = worldY % numberTilesPerLandSqrt;
		return lands.get(landX * numberLandsSqrt + landY).getTiles().get(tileX * numberTilesPerLandSqrt + tileY);
	}
}
