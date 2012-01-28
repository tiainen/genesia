package net.sertik.genesia.entity;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Skyora
 */
public class LandTest {
	@Test
	public void testEquals() {
		Land land1 = new Land();
		Land land2 = new Land();
		assertEquals(land1, land2);

		land1.setX(1);
		land1.setY(1);
		land2.setX(2);
		land2.setY(2);
		assertNotEquals(land1, land2);

		land1.setX(2);
		assertNotEquals(land1, land2);

		land1.setX(1);
		land1.setY(2);
		assertNotEquals(land1, land2);

		land1.setX(2);
		assertEquals(land1, land2);
	}
}
