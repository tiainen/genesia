package net.sertik.genesia.entity;

/**
 *
 * @author Joeri
 */
public class Scenery implements GameObject {
	public static final Scenery HOVER_TILE = new Scenery("SYSTEM_HoverTile");

  private String name;

  public Scenery(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Scenery other = (Scenery) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}
}
