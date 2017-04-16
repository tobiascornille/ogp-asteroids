package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;

public class EntityTest {
	
	private static final double EPSILON = 0.0001;
	
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
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(0, 0), 11, 0, 0);
		assertTrue(ship1.overlap(ship1));
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testliesWithinBoundsWorld() {
		// 0.99 * 11 = 10.89, so this would not lie fully within the bounds of the world.
		World world = new World (new Size(100, 100));
		Ship ship1 = new Ship(new Vector(10.88, 10.88), new Vector(0, 0), 11, 0, 0);
		world.addEntity(ship1);
	}

	@Test 
	public void testliesWithinBoundsWorld2() {
		World world = new World (new Size(100, 100));
		Ship ship1 = new Ship(new Vector(10.90, 10.90), new Vector(0, 0), 11, 0, 0);
		world.addEntity(ship1);
	}
	
	@Test
	public void collisionBoundaryAbove() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(0, 1), 11, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getCollisionBoundaryPosition().getXComponent(), EPSILON);
		assertEquals(100, ship.getCollisionBoundaryPosition().getYComponent(), EPSILON);
	}
	
	@Test
	public void collisionBoundaryBelow() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(0, -1), 11, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getCollisionBoundaryPosition().getXComponent(), EPSILON);
		assertEquals(0, ship.getCollisionBoundaryPosition().getYComponent(), EPSILON);
	}
	
	@Test
	public void collisionBoundaryLeft() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(-1, 0), 11, 0, 0);
		world.addEntity(ship);
		assertEquals(0, ship.getCollisionBoundaryPosition().getXComponent(), EPSILON);
		assertEquals(50, ship.getCollisionBoundaryPosition().getYComponent(), EPSILON);
	}
	
	@Test
	public void collisionBoundaryRight() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(1, 0), 11, 0, 0);
		world.addEntity(ship);
		assertEquals(100, ship.getCollisionBoundaryPosition().getXComponent(), EPSILON);
		assertEquals(50, ship.getCollisionBoundaryPosition().getYComponent(), EPSILON);
	}
	
	@Test
	public void timeToCollisionBoundaryAbove() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 30), new Vector(0, 1), 20, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getTimeToCollisionBoundary(), EPSILON);
	}
	
	@Test
	public void timeToCollisionBoundaryBelow() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(50, 70), new Vector(0, -1), 20, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getTimeToCollisionBoundary(), EPSILON);
	}
	
	@Test
	public void timeToCollisionBoundaryLeft() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(70, 50), new Vector(-1, 0), 20, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getTimeToCollisionBoundary(), EPSILON);
	}
	
	@Test
	public void timeToCollisionBoundaryRight() {
		World world = new World (new Size(100, 100));
		Ship ship = new Ship(new Vector(30, 50), new Vector(1, 0), 20, 0, 0);
		world.addEntity(ship);
		assertEquals(50, ship.getTimeToCollisionBoundary(), EPSILON);
	}
	
	
	
	// Tests for other methods can be found in the part 1 tests.
	
}
