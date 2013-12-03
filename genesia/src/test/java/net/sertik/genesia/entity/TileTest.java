package net.sertik.genesia.entity;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Skyora
 */
public class TileTest {

	@Test
	public void testEquals() {
		Tile tile1 = new Tile();
		Tile tile2 = new Tile();
		assertEquals(tile1, tile2);

		tile1.setX(1);
		tile1.setY(1);
		tile2.setX(2);
		tile2.setY(2);
		assertNotEquals(tile1, tile2);

		tile1.setX(2);
		assertNotEquals(tile1, tile2);

		tile1.setX(1);
		tile1.setY(2);
		assertNotEquals(tile1, tile2);

		tile1.setX(2);
		assertEquals(tile1, tile2);
	}

	@Test
	public void testEqualsLand() {
		Land land1 = new Land();
		land1.setX(1);
		land1.setY(1);
		Land land2 = new Land();
		land2.setX(2);
		land2.setY(2);

		Tile tile1 = new Tile();
		tile1.setLand(land1);
		Tile tile2 = new Tile();
		tile2.setLand(land1);
		assertEquals(tile1, tile2);

		tile1.setX(1);
		tile1.setY(1);
		tile2.setX(1);
		tile2.setY(1);
		assertEquals(tile1, tile2);

		tile2.setLand(land2);
		assertNotEquals(tile1, tile2);
	}
}
