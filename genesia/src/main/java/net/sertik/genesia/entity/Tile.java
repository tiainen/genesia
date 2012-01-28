package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author joeri
 */
public class Tile {
  private int x;
  private int y;
  private Land land;
  private List<GameObject> objects = new LinkedList<>();

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }

  public List<GameObject> getObjects() {
    return objects;
  }

  public void setObjects(List<GameObject> objects) {
    this.objects = objects;
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
