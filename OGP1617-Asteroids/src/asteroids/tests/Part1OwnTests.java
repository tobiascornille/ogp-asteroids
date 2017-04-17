package asteroids.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import asteroids.model.Ship;
import asteroids.model.Vector;
import asteroids.model.World;
import asteroids.model.Size;

public class Part1OwnTests {
	
	private static final double EPSILON = 0.0001;

	@Test
	public void testCreateShip() throws IllegalArgumentException {
		// Testing the first constructor
		Ship ship = new Ship();
		double radius = ship.getRadius();
		assertEquals(11, radius , EPSILON);
		Vector velocity = ship.getVelocity();
		assertEquals(0, velocity.getXComponent(), EPSILON);
		assertEquals(0, velocity.getYComponent(), EPSILON);
		Vector position = ship.getPosition();
		assertEquals(0, position.getXComponent(), EPSILON);
		assertEquals(0, position.getYComponent(), EPSILON);
		double orientation = ship.getOrientation();
		assertEquals(0, orientation, EPSILON);			
	}
	
	@Test 
	public void testCreateShipIllegalVelocity() throws IllegalArgumentException {
		// Testing for very big values, and illegal velocity (does not throw)
		// Checks if velocity's stay on 0
		Ship ship = new Ship(new Vector(60000000, 600000000), new Vector(299000, 299000), 520, Math.PI,0);
		Vector velocity = ship.getVelocity();
		assertEquals(300000,velocity.getMagnitude(), EPSILON);	
	}
	
	@Test
	public void testGetPosition() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, Math.PI,0);
		Vector position = ship.getPosition();
		assertEquals(200, position.getXComponent(), EPSILON);
		assertEquals(200, position.getYComponent(), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipYIsNan() throws IllegalArgumentException {
		new Ship(new Vector(200, Double.NaN), new Vector(10, -10), 20, -Math.PI,0);
	}
	
	@Test
	public void testGetVelocity() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, Math.PI,0);
		Vector velocity = ship.getVelocity();
		assertEquals(50, velocity.getXComponent(), EPSILON);
		assertEquals(50, velocity.getYComponent(), EPSILON);
	}
			
	@Test  (expected = IllegalArgumentException.class)
	public void testCreateShipIllegalRadius() throws IllegalArgumentException {
			// Testing for illegal radius
			new Ship(new Vector(200, 200), new Vector(50, 50), 9, Math.PI,0);	
	}
	
	@Test
	public void testGetRadius() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 20, Math.PI,0);
		assertEquals(20, ship.getRadius(), EPSILON);
	}
	
	@Test
	public void testGetOrientation() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 20, Math.PI,0);
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testGetIllegalOrientation() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, 3 * Math.PI,0);
		assertEquals(3 * Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testMove() throws IllegalArgumentException {
		Ship ship = new Ship(new Vector(100, 100), new Vector(50, -50), 20, 0,0);
		World world = new World (new Size(500, 500));
		world.addEntity(ship);
		world.evolve(1, null);
		Vector position = ship.getPosition();
		assertNotNull(position);
		assertEquals(150, position.getXComponent(), EPSILON);
		assertEquals(50, position.getYComponent(), EPSILON);
	}
	
	@Test
	public void testTurn() throws IllegalArgumentException {
		// Testing turn
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, Math.PI,0);
		ship.turn(Math.PI);
		double orientation = ship.getOrientation();
		assertEquals(2 * Math.PI, orientation, EPSILON);
		ship.turn(Math.PI/2);
		assertEquals(2 * Math.PI + Math.PI/2, ship.getOrientation(), EPSILON);
	}
	
	@Deprecated
	public void testThrust() throws IllegalArgumentException {
		// Testen thrust
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, 0,0);
		ship.thrust(3);
		Vector velocity = ship.getVelocity();
		assertEquals(53, velocity.getXComponent(), EPSILON);
		assertEquals(50, velocity.getYComponent(), EPSILON);	
	}
	
	@Deprecated
	public void testThrustIllegal() throws IllegalArgumentException {
		// Testen thrust
		Ship ship = new Ship(new Vector(200, 200), new Vector(50, 50), 11, 0,0);
		ship.thrust(300000);
		assertEquals(300000, ship.getVelocity().getXComponent(), EPSILON);
		assertEquals(0, ship.getVelocity().getYComponent(), EPSILON);
	}
		
	@Test
	public void testDistanceBetweenOverlap() throws IllegalArgumentException {
		// Testing the distance between
		// First test = overlapping ships
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(50, 50), 11,0,0);
		Ship ship2 = new Ship(new Vector(10, 10), new Vector(50, 50), 11, 0,0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(-7.8578, distance, EPSILON);
		// Testing overlap
		boolean overlap = ship2.overlap(ship2);
		assertTrue(overlap);
		
	}
	
	@Test
	public void testDistanceBetweenSameShip() throws IllegalArgumentException {
		// Testing to see what method returns when called on same ship
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(50, 50), 11,0,0);
		double distance2 = ship1.getDistanceBetween(ship1);
		assertEquals(0, distance2, EPSILON);			
	}
		
	@Test
	public void testDistanceBetweenValid() throws IllegalArgumentException {
		// Testing for a positive value
		Ship ship1 = new Ship(new Vector(0, 0), new Vector(50, 50), 11,0,0);
		Ship ship2 = new Ship(new Vector(50, 50), new Vector(50, 50), 11, 0,0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(48.7106, distance, EPSILON);
	}
	
	@Test
	public void testTimeToCollison() throws IllegalArgumentException {
		Ship ship1 = new Ship(new Vector(-100, 0), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(1100, 0), new Vector(-100, 0), 100, 0,0);
		assertEquals(10, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testTimeToCollisonInfinity() throws IllegalArgumentException {
		Ship ship1 = new Ship(new Vector(-100, 0), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(1100, 0), new Vector(100, 0), 100, 0,0);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionRight() throws IllegalArgumentException {
		// From rights
		Ship ship1 = new Ship(new Vector(-100, 0), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(1100, 0), new Vector(-100, 0), 100, 0,0);
		Vector colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos.getXComponent(), EPSILON);
		assertEquals(0, colPos.getYComponent(), EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionUnder() throws IllegalArgumentException {
		// From under
		Ship ship1 = new Ship(new Vector(0, 100), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(0, -1100), new Vector(0, 100), 100, 0,0);
		Vector colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos.getXComponent(), EPSILON);
		assertEquals(0, colPos.getYComponent(), EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionLeft() throws IllegalArgumentException {
		// From left
		Ship ship1 = new Ship(new Vector(100, 0), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(-1100, 0), new Vector(100, 0), 100, 0,0);
		Vector colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos.getXComponent(), EPSILON);
		assertEquals(0, colPos.getYComponent(), EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionAbove() throws IllegalArgumentException {
		// From above
		Ship ship1 = new Ship(new Vector(0, -100), new Vector(0, 0), 100, 0,0);
		Ship ship2 = new Ship(new Vector(0, 1100), new Vector(0, -100), 100, 0,0);
		Vector colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos.getXComponent(), EPSILON);
		assertEquals(0, colPos.getYComponent(), EPSILON);
	}
	
	
}
