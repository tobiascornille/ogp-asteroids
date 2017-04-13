package asteroids.tests;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;

public class EntityTest {

	@Test (expected = IllegalArgumentException.class)
	public void testIsValidPosition(){
		World world = new World (new Size(500, 500));
		Ship ship1 = new Ship(new Vector(50, 50), new Vector(0, 0), 11, 0, 0);
		world.addEntity(ship1);
		Ship ship2 = new Ship(new Vector(50, 50), new Vector(0, 0), 11, 0 , 0);	
		world.addEntity(ship2);
		
		
	}

	@Test
	public void testOverlap() {
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(0, 0), 11,0,0);
		assertTrue(ship1.overlap(ship1));
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testliesWithinBoundsWorld() {
		// 0.99 * 11 = 10.89, so this would not lie fully within the bounds of the world.
		World world = new World (new Size(100,100));
		Ship ship1 = new Ship(new Vector(10.88, 10.88), new Vector(0, 0), 11,0,0);
		world.addEntity(ship1);
	}

	@Test 
	public void testliesWithinBoundsWorld2() {
		World world = new World (new Size(100,100));
		Ship ship1 = new Ship(new Vector(10.90, 10.90), new Vector(0, 0), 11,0,0);
		world.addEntity(ship1);
	}
	
	
	
	// Tests for other methods can be found in the part 1 tests.
	
}
