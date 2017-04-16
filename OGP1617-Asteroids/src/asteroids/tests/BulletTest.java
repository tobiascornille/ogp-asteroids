package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;

public class BulletTest {

	private static final double EPSILON = 0.0001;
	
	@Test
	public void testCreateBullet() {
		Bullet bullet = new Bullet(new Vector(50, 50), new Vector(0,0), 2);
		assertEquals(50, bullet.getPosition().getXComponent(), EPSILON);
		assertEquals(0, bullet.getCollisionCounter(), EPSILON);
		assertEquals(4/3 * Math.PI * Math.pow(2, 3) * 7.8 * Math.pow(10, 12), bullet.getMass(), EPSILON);
		assertFalse(bullet.isTerminated());
		assertNull(bullet.getShip());
	}
	
	@Test
	public void testLoadBullet() {
		// TODO is  it okay that the bullet has to be at the correct position?
		Bullet bullet = new Bullet(new Vector(50, 50), new Vector(0,0), 2);
		Ship ship = new Ship(new Vector(50, 50), new Vector(0, 0), 11, 0, 0);
		ship.loadBullet(bullet);
		assertTrue(ship == bullet.getShip());
	}
	
	@Test 
	public void testTerminateBullet() {
		Bullet bullet = new Bullet(new Vector(50,50), new Vector(0,0), 2);
		bullet.terminate();
		assertTrue(bullet.isTerminated());
	}
	
	@Test 
	public void testInShipFalse() {
		Bullet bullet = new Bullet(new Vector(20, 20), new Vector(0,0), 2);
		Ship ship = new Ship(new Vector(50, 50), new Vector(0, 0), 11, 0, 0);
		assertFalse(bullet.isInShip(ship));
	}
	
	@Test 
	public void testInShipTrue() {
		Bullet bullet = new Bullet(new Vector(40, 32), new Vector(0,0), 2);
		Ship ship = new Ship (new Vector(40, 40), new Vector(0, 0), 11, 0, 0);
		assertTrue(bullet.isInShip(ship));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOverlapInWorldAndReturn() {
		Bullet bullet = new Bullet(new Vector(40, 32), new Vector(0,0), 2);
		Bullet bullet2 = new Bullet(new Vector(40, 32), new Vector(0,0), 2);
		World world = new World(new Size(100,100));
		world.addEntity(bullet);
		world.addEntity(bullet2);
		assertNull(bullet.getOverlappingEntityInWorld(world));	
	}
	

}
