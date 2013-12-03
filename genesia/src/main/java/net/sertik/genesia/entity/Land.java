package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * A land consists of a series of tiles and a land is in its turn part of the
 * game world. A land can be owned by a player, but can also be without an
 * owner.
 *
 * @author joeri
 */
public class Land {

	private String name;
	private World world;
	private int x;
	private int y;
	private Player owner;
	private List<Tile> tiles = new LinkedList<>();

	/**
	 * The name for this land.
	 *
	 * @return the name of the land
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The World where this land resides in.
	 *
	 * @return the world the land is in
	 */
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Returns the x-coordinate of the land.
	 *
	 * @return the x-coordinate of the land
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y-coordinate of the land.
	 *
	 * @return the y-coordinate of the land
	 */
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the player that is the owner of this land. The owner can be
	 * <code>null</code> if this land has no owner.
	 *
	 * @return the owner of the land
	 */
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Returns all the tiles that are part of this land.
	 *
	 * @return a list of tiles that are part of the land
	 */
	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	/**
	 * Retrieves the tile that is located at the specified x- and y-coordinate.
	 * These coordinates are land coordinates.
	 *
	 * @param tileX the x land coordinate
	 * @param tileY the y land coordinate
	 * @return the tile located at these coordinates
	 */
	public Tile getTile(int tileX, int tileY) {
		return tiles.get(tileX * world.getNumberTilesPerLandSqrt() + tileY);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Land other = (Land) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + this.x;
		hash = 89 * hash + this.y;
		return hash;
	}
}
