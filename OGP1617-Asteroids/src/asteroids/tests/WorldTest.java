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


public class WorldTest {

	@Test
	public void test() {
		World world = new World(100, 100);
		Ship ship = new Ship();
		ship.setWorld(world);
		world.addEntity(ship);
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(0,0))));
		ship.setPosition(new Vector(10,10));
		ship.setPosition(new Vector(0,0));
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(0,0))));
	}

}
