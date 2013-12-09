package net.sertik.genesia.util;

/**
 *
 * @author joeri
 */
public class Coords {
	public int x;
	public int y;

	public Coords() {
	}

	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + this.x;
		hash = 29 * hash + this.y;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Coords other = (Coords) obj;
		if (this.x != other.x) {
			return false;
		}
		return this.y == other.y;
	}

}
