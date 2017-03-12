package asteroids.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Part1OwnTests {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();		
	}
		
	@Test
	public void testCreateShip() throws ModelException {
		// Testing the first constructor
		Ship ship = facade.createShip ();
		double radius = ship.getRadius();
		assertEquals(11, radius , EPSILON);
		double[] velocity = ship.getVelocity();
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
		double[] position = ship.getPosition();
		assertEquals(0, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
		double orientation = ship.getOrientation();
		assertEquals(0, orientation, EPSILON);			
	}
	
	@Test 
	public void testCreateShipIllegalVelocity() throws ModelException {
		// Testing for very big values, and illegal velocity (does not throw)
		// Checks if velocity's stay on 0
		Ship ship = facade.createShip(60000000, 600000000, 299000, 299000, 520, Math.PI);
		double[] velocity = ship.getVelocity();
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
		
	}
	
	@Test
	public void testGetPosition() throws ModelException {
		Ship ship = facade.createShip(200, 200, 50, 50, 11, Math.PI);
		double[] position = ship.getPosition();
		assertEquals(200, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(200,Double.NaN, 10, -10, 20, -Math.PI);
	}
	
	@Test
	public void testGetVelocity() throws ModelException {
		Ship ship = facade.createShip(200, 200, 50, 50, 11, Math.PI);
		double[] velocity = ship.getVelocity();
		assertEquals(50, velocity[0], EPSILON);
		assertEquals(50, velocity[1], EPSILON);
	}
			
	@Test  (expected = ModelException.class)
	public void testCreateShipIllegalRadius() throws ModelException {
			// Testing for illegal radius
			facade.createShip(200, 200, 50, 50, 9, Math.PI);	
	}
	
	@Test
	public void testGetRadius() throws ModelException {
		Ship ship = facade.createShip(200, 200, 50, 50, 20, Math.PI);
		assertEquals(20, ship.getRadius(), EPSILON);
	}
	
	@Test
	public void testGetOrientation() throws ModelException {
		Ship ship = facade.createShip(200, 200, 50, 50, 20, Math.PI);
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testGetIllegalOrientation() throws ModelException {
		Ship ship = facade.createShip(200, 200, 50, 50, 11, 3 * Math.PI);
		assertEquals(3 * Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 50, -50, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(150, position[0], EPSILON);
		assertEquals(50, position[1], EPSILON);
	}
	
	@Test
	public void testTurn() throws ModelException {
		// Testing turn
		Ship ship = facade.createShip(200, 200, 50, 50, 11, Math.PI);
		ship.turn(Math.PI);
		double orientation = ship.getOrientation();
		assertEquals(2 * Math.PI, orientation, EPSILON);
		ship.turn(Math.PI/2);
		assertEquals(2 * Math.PI + Math.PI/2, ship.getOrientation(), EPSILON);
	}
	
	@Test 
	public void testThrust() throws ModelException {
		// Testen thrust
		Ship ship = facade.createShip(200, 200, 50, 50, 11, 0);
		ship.thrust(3);
		double[] velocity = ship.getVelocity();
		assertEquals(53, velocity[0], EPSILON);
		assertEquals(50, velocity[1], EPSILON);	
	}
	
	@Test 
	public void testThrustIllegal() throws ModelException {
		// Testen thrust
		Ship ship = facade.createShip(200, 200, 50, 50, 11, 0);
		ship.thrust(300000);
		assertEquals(300000, ship.getVelocity()[0], EPSILON);
		assertEquals(0, ship.getVelocity()[1], EPSILON);
	}
		
	@Test
	public void testDistanceBetweenOverlap() throws ModelException {
		// Testing the distance between
		// First test = overlapping ships
		Ship ship1 = facade.createShip(0, 0, 50, 50, 11,0);
		Ship ship2 = facade.createShip(10, 10, 50, 50, 11, 0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(-7.8578, distance, EPSILON);
		// Testing overlap
		boolean overlap = ship2.overlap(ship2);
		assertTrue(overlap);
		
	}
	
	@Test
	public void testDistanceBetweenSameShip() throws ModelException {
		// Testing to see what method returns when called on same ship
		Ship ship1 = facade.createShip(0, 0, 50, 50, 11,0);
		double distance2 = ship1.getDistanceBetween(ship1);
		assertEquals(0, distance2, EPSILON);			
	}
		
	@Test
	public void testDistanceBetweenValid() throws ModelException {
		// Testing for a positive value
		Ship ship1 = facade.createShip(0, 0, 50, 50, 11,0);
		Ship ship2 = facade.createShip(50, 50, 50, 50, 11, 0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(48.7106, distance, EPSILON);
	}
	
	@Test
	public void testTimeToCollison() throws ModelException {
		Ship ship1 = facade.createShip(-100, 0, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(1100, 0, -100, 0, 100, 0);
		assertEquals(10, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testTimeToCollisonInfinity() throws ModelException {
		Ship ship1 = facade.createShip(-100, 0, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(1100, 0, 100, 0, 100, 0);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionRight() throws ModelException {
		// From rights
		Ship ship1 = facade.createShip(-100, 0, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(1100, 0, -100, 0, 100, 0);
		double[] colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos[0], EPSILON);
		assertEquals(0, colPos[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionUnder() throws ModelException {
		// From under
		Ship ship1 = facade.createShip(0, 100, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(0, 1100, 0, -100, 100, Math.PI/2);
		double[] colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos[0], EPSILON);
		assertEquals(0, colPos[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionLeft() throws ModelException {
		// From left
		Ship ship1 = facade.createShip(100, 0, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(-1100, 0, 100, 0, 100, 0);
		double[] colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos[0], EPSILON);
		assertEquals(0, colPos[1], EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionAbove() throws ModelException {
		// From above
		Ship ship1 = facade.createShip(0, 100, 0, 0, 100, 0);
		Ship ship2 = facade.createShip(0, 1100, 0, -100, 100, 3 * Math.PI/2);
		double[] colPos = ship1.getCollisionPosition(ship2);
		assertEquals(0, colPos[0], EPSILON);
		assertEquals(0, colPos[1], EPSILON);
	}
	
	
}
