package asteroids.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class OwnTests {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();		
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
		
		
	@Test  (expected = ModelException.class)
	public void testCreateShipIllegalRadius() throws ModelException {
			// Testing for illegal radius
			Ship ship = facade.createShip(200, 200, 50, 50, 9, Math.PI);
			double[] velocity = ship.getVelocity();
			assertEquals(50, velocity[0], EPSILON);
			assertEquals(50, velocity[1], EPSILON);
			
	}
	
	@Test
	public void testCreateShipTurn() throws ModelException {
		// Testing turn
		Ship ship = facade.createShip(200, 200, 50, 50, 11, Math.PI);
		ship.turn(Math.PI);
		double orientation = ship.getOrientation();
		assertEquals(2 * Math.PI, orientation, EPSILON);
		ship.turn(Math.PI/2);
		assertEquals(2 * Math.PI, orientation, EPSILON);
	}
	
	@Test
	public void testCreateShipThrust() throws ModelException {
		// Testen thrust
		Ship ship = facade.createShip(200, 200, 50, 50, 11, 0);
		ship.thrust(3);
		double[] velocity = ship.getVelocity();
		assertEquals(53, velocity[0], EPSILON);
		assertEquals(50, velocity[1], EPSILON);
		
	}
	
	@Test
	public void testDistanceBetween() throws ModelException {
		// Testing the distance between
		// First test = overlapping ships
		Ship ship1 = facade.createShip(0, 0, 50, 50, 11,0);
		Ship ship2 = facade.createShip(10, 10, 50, 50, 11, 0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(-7.8578, distance, EPSILON);
		// Testing overlap
		boolean overlap = ship2.overlap(ship2);
		assertTrue(overlap);
		// Second test = same ship
		double distance2 = ship1.getDistanceBetween(ship1);
		assertEquals(0, distance2, EPSILON);
		
	}
	
	@Test
	public void testDistanceBetween2() throws ModelException {
		// Testing for a positive value
		Ship ship1 = facade.createShip(0, 0, 50, 50, 11,0);
		Ship ship2 = facade.createShip(50, 50, 50, 50, 11, 0);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(48.7106, distance, EPSILON);
	}
	
	
	
	
	

}
