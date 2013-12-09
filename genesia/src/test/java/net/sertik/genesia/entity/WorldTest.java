package net.sertik.genesia.entity;

import java.util.Arrays;
import net.sertik.genesia.util.Coords;
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

		assertTrue(world.isPointWithinBounds(new Coords(0, 0)));
		assertTrue(world.isPointWithinBounds(new Coords(5, 5)));
		assertTrue(world.isPointWithinBounds(new Coords(7, 7)));
		assertFalse(world.isPointWithinBounds(new Coords(8, 1)));
		assertFalse(world.isPointWithinBounds(new Coords(1, 8)));
		assertFalse(world.isPointWithinBounds(new Coords(9, 9)));
		assertFalse(world.isPointWithinBounds(new Coords(-4, 4)));
		assertFalse(world.isPointWithinBounds(new Coords(4, -4)));
		assertFalse(world.isPointWithinBounds(new Coords(-4, -4)));
		assertFalse(world.isPointWithinBounds(new Coords(-4, 10)));
		assertFalse(world.isPointWithinBounds(new Coords(10, -4)));
	}

	@Test
	public void setHoverCoordinates() {
		World world = new World(4, 16);

		assertEquals(world.getHoverWorldCoords().getX(), -1);
		assertEquals(world.getHoverWorldCoords().getY(), -1);

		assertTrue(world.setHoverCoords(new Coords(0, 0)));
		assertEquals(world.getHoverWorldCoords().getX(), 0);
		assertEquals(world.getHoverWorldCoords().getY(), 0);

		assertTrue(world.setHoverCoords(new Coords(4, 2)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertFalse(world.setHoverCoords(new Coords(4, 2)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertFalse(world.setHoverCoords(new Coords(8, 2)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertFalse(world.setHoverCoords(new Coords(2, 8)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertFalse(world.setHoverCoords(new Coords(-2, 4)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertFalse(world.setHoverCoords(new Coords(2, -4)));
		assertEquals(world.getHoverWorldCoords().getX(), 4);
		assertEquals(world.getHoverWorldCoords().getY(), 2);

		assertTrue(world.setHoverCoords(new Coords(2, 6)));
		assertEquals(world.getHoverWorldCoords().getX(), 2);
		assertEquals(world.getHoverWorldCoords().getY(), 6);
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
