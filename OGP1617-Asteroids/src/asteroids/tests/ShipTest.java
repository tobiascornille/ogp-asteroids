package asteroids.tests;

import static org.junit.Assert.*;


import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.Vector;

public class ShipTest {
	
	@Test
	public void testLoadBullet() {
		// TODO is  it okay that the bullet has to be at the correct position?
		Bullet bullet = new Bullet(new Vector(50, 50), new Vector(0,0), 2);
		Ship ship = new Ship(new Vector(50, 50), new Vector(0, 0), 11, 0, 0);
		ship.loadBullet(bullet);
		assertTrue(ship == bullet.getShip());
	}

}
