package asteroids.tests;
import static org.junit.Assert.assertEquals;


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
		// Cheks if velocity's stay on 0
		Ship ship = facade.createShip(60000000, 600000000, 299000, 299000, 520, Math.PI);
		double[] velocity = ship.getVelocity();
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
		
	}
		
		
	@Test  (expected = ModelException.class)
	public void testCreateShipIllegalRadius() throws ModelException {
			// Testing for illegal radius
			Ship ship2 = facade.createShip(200, 200, 50, 50, 9, Math.PI);
			double[] velocity = ship2.getVelocity();
			assertEquals(50, velocity[0], EPSILON);
			assertEquals(50, velocity[1], EPSILON);
			
	}
	
	
	
	
	
	

}
