package net.sertik.genesia.entity;

import java.util.Arrays;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Skyora
 */
public class WorldTest {

	@Test
	public void testConstructor() {
		World world = new World(4, 16);

		assertEquals(world.getNumberLands(), 4);
		assertEquals(world.getNumberTilesPerLand(), 16);
		assertEquals(world.getSizeSqrt(), 8);
		assertEquals(world.getNumberLandsSqrt(), 2);
		assertEquals(world.getNumberTilesPerLandSqrt(), 4);
	}

	@Test
	public void testWithinBounds() {
		World world = new World(4, 16);

		assertTrue(world.isPointWithinBounds(0, 0));
		assertTrue(world.isPointWithinBounds(5, 5));
		assertTrue(world.isPointWithinBounds(7, 7));
		assertFalse(world.isPointWithinBounds(8, 1));
		assertFalse(world.isPointWithinBounds(1, 8));
		assertFalse(world.isPointWithinBounds(9, 9));
		assertFalse(world.isPointWithinBounds(-4, 4));
		assertFalse(world.isPointWithinBounds(4, -4));
		assertFalse(world.isPointWithinBounds(-4, -4));
		assertFalse(world.isPointWithinBounds(-4, 10));
		assertFalse(world.isPointWithinBounds(10, -4));
	}

	@Test
	public void setHoverCoordinates() {
		World world = new World(4, 16);

		assertEquals(world.getHoverWorldX(), -1);
		assertEquals(world.getHoverWorldY(), -1);

		assertTrue(world.setHoverCoords(0, 0));
		assertEquals(world.getHoverWorldX(), 0);
		assertEquals(world.getHoverWorldY(), 0);

		assertTrue(world.setHoverCoords(4, 2));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertFalse(world.setHoverCoords(4, 2));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertFalse(world.setHoverCoords(8, 2));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertFalse(world.setHoverCoords(2, 8));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertFalse(world.setHoverCoords(-2, 4));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertFalse(world.setHoverCoords(2, -4));
		assertEquals(world.getHoverWorldX(), 4);
		assertEquals(world.getHoverWorldY(), 2);

		assertTrue(world.setHoverCoords(2, 6));
		assertEquals(world.getHoverWorldX(), 2);
		assertEquals(world.getHoverWorldY(), 6);
	}

	@Test
	public void testLands() {
		World world = new World(4, 16);

		assertNotNull(world.getLands());
		assertTrue(world.getLands().isEmpty());

		// attempt to assign two lands
		world.setLands(Arrays.asList(new Land(), new Land()));
		assertNotNull(world.getLands());
		assertTrue(world.getLands().isEmpty());

		// attempt to assign eight lands
		world.setLands(Arrays.asList(new Land(), new Land(), new Land(), new Land(),
						new Land(), new Land(), new Land(), new Land()));
		assertNotNull(world.getLands());
		assertTrue(world.getLands().isEmpty());

		// attempt to assign four lands
		world.setLands(Arrays.asList(new Land(), new Land(), new Land(), new Land()));
		assertNotNull(world.getLands());
		assertFalse(world.getLands().isEmpty());
		assertEquals(world.getLands().size(), 4);
	}
}
