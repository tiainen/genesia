package net.sertik.genesia.entity;

/**
 * A GameObject is the smallest level of the game world. A Tile will contain
 * one or more GameObject instances. This can be a construction, a recruit or
 * a simple scenery object.
 *
 * @author joeri
 */
public interface GameObject {
	/**
	 * The name that will be used in the game screen.
	 *
	 * @return 
	 */
  public String getName();
}
