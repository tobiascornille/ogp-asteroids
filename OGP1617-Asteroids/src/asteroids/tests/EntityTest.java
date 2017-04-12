package asteroids.tests;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.Vector;
import asteroids.model.World;

public class EntityTest {

	@Test
	public void testIsValidPosition(){
		World world = new World (5000,  5000);
		Ship ship1 = new Ship(new Vector(10, 10), new Vector(0, 0), 11,0);
		ship1.setWorld(world);
		Ship ship2 = new Ship(new Vector(0, 0), new Vector(0, 0), 11,0);	
		ship2.setWorld(world);
	}

	@Test
	public void testOverlap() {
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(0, 0), 11,0);
		assertTrue(ship1.overlap(ship1));
	}
	
	@Test 
	public void testliesWithinBoundsWorld() {
		// 0.99 * 11 = 10.89, so this would not lie fully within the bounds of the world.
		World world = new World (100,  100);
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(0, 0), 11,0);
		ship1.setWorld(world);
		assertFalse(ship1.liesWithinBoundsWorld(new Vector (10.88,10.88)));
	}

	@Test 
	public void testliesWithinBoundsWorld2() {
		World world = new World (100,  100);
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(0, 0), 11,0);
		ship1.setWorld(world);
		assertTrue(ship1.liesWithinBoundsWorld(new Vector (10.89, 10.89)));
	}
	
	
	
	// Tests for other methods can be found in the part 1 tests.
	
}
