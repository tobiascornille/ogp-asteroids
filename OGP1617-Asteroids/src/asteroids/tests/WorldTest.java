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


public class WorldTest {

	@Test
	public void testReturnEntityGivenPosition() {
		World world = new World(new Size(100,100));
		Ship ship = new Ship(new Vector(50,50), new Vector(1,1), 11, 0, 0);
		world.addEntity(ship);
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(50,50))));
		world.evolve(1);
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(51,51))));
	}
	
	
	

}
