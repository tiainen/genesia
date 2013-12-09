package net.sertik.genesia.world;

import java.util.Random;
import net.sertik.genesia.entity.Land;
import net.sertik.genesia.entity.Scenery;
import net.sertik.genesia.entity.Tile;
import net.sertik.genesia.entity.World;

/**
 *
 * @author Joeri
 */
public class FlatLandWorldGenerator implements WorldGenerator {

	private double lusciousness = 0.5;

	public FlatLandWorldGenerator(double lusciousness) {
		this.lusciousness = lusciousness;
	}

	@Override
	public World generate(int numberLands, int numberTilesPerLand) {
		World world = new World(numberLands, numberTilesPerLand);

		world.setName("Genesia");

		int numberLandsSqrt = (int) Math.sqrt(numberLands);
		int numberTilesSqrt = (int) Math.sqrt(numberTilesPerLand);

		int halfOfNumberTilesSqrt = numberTilesSqrt / 2;

		Random rand = new Random();

		for (int x = 0; x < numberLandsSqrt; x++) {
			for (int y = 0; y < numberLandsSqrt; y++) {
				Land land = new Land();
				land.setX(x);
				land.setY(y);
				land.setName("Judokan");
				land.setWorld(world);

				for (int lx = 0; lx < numberTilesSqrt; lx++) {
					for (int ly = 0; ly < numberTilesSqrt; ly++) {
						Tile tile = new Tile();
						tile.setX(lx);
						tile.setY(ly);
						tile.setLand(land);
						if (rand.nextDouble() < lusciousness) {
							tile.getObjects().add(new Scenery("tree"));
						} else {
							tile.getObjects().add(new Scenery("grass"));
						}

						land.getTiles().add(tile);
					}
				}

				// generate flag tile around center
				Tile tile = land.getTile(halfOfNumberTilesSqrt + (rand.nextInt(3) - 1),
								halfOfNumberTilesSqrt + (rand.nextInt(3) - 1));
				tile.getObjects().clear();
				tile.getObjects().add(new Scenery("flag"));

				world.getLands().add(land);
			}
		}

		return world;
	}
}
