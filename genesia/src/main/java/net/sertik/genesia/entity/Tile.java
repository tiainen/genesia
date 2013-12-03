package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A Tile defines what GameObjects are placed at a certain location in a certain
 * land.
 *
 * @author joeri
 */
public class Tile {

	private int x;
	private int y;
	private Land land;
	private List<GameObject> objects = new LinkedList<>();

	/**
	 * Returns the x-coordinate of the tile.
	 *
	 * @return the x-coordinate of the tile
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y-coordinate of the tile.
	 *
	 * @return the y-coordinate of the tile
	 */
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Specifies in which Land this tile is situated. The x- and y-coordinate are
	 * relative within the location of this land.
	 *
	 * @return the land this tile is situated in
	 */
	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	/**
	 * Defines the list of GameObjects that are placed into this Tile. At least
	 * one GameObject should be added to this list.
	 *
	 * @return a list of GameObjects on this tile
	 */
	public List<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(List<GameObject> objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		if (!objects.isEmpty()) {
			int worldX = x + getLand().getX() * getLand().getWorld().getNumberTilesPerLandSqrt();
			int worldY = y + getLand().getY() * getLand().getWorld().getNumberTilesPerLandSqrt();

			GameObject last = objects.get(objects.size() - 1);

			StringBuilder builder = new StringBuilder();
			builder.append(last.getName()).append(" [").append(worldX).append(", ").append(worldY).append("]");
			return builder.toString();
		} else {
			return super.toString();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Tile other = (Tile) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		if (!Objects.equals(this.land, other.land)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + this.x;
		hash = 83 * hash + this.y;
		return hash;
	}
}
