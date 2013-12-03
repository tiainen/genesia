package net.sertik.genesia.world;

import net.sertik.genesia.entity.World;

/**
 *
 * @author Joeri
 */
public interface WorldGenerator {

	public World generate(int numberLands, int numberTilesPerLand);
}
