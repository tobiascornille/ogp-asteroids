package asteroids.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;



public class WorldTest {

	@Test
	public void testReturnEntityGivenPosition() {
		World world = new World(new Size(100,100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(1,1), 11, 0, 0);
		world.addEntity(ship);
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(50,50))));
		world.evolve(1, null);
		assertTrue(ship.equals(world.returnEntityGivenPosition(new Vector(51,51))));
	}
	
	@Test 
	public void testEntityIsInWorld() {
		World world = new World(new Size(100,100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(1,1), 11, 0, 0);
		world.addEntity(ship);
		assertTrue(world.hasAsEntity(ship));
	}
	
	@Test
	public void testNbEntitiesAndRemoveEntity() {
		World world = new World(new Size(100,100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(1,1), 11, 0, 0);
		Ship ship2 = new Ship(new Vector(70, 70), new Vector(1,1), 11, 0, 0);
		world.addEntity(ship);
		world.addEntity(ship2);
		assertEquals(2, world.getNbEntities(), 0.1);
		world.removeEntity(ship);
		assertEquals(1, world.getNbEntities(), 0.1);
	}
	
	@Test
	public void testAddEntity() {
		World world = new World(new Size(100,100));
		Ship ship = new Ship(new Vector(50, 50), new Vector(1,1), 11, 0, 0);
		world.addEntity(ship);
		assertTrue(world.equals(ship.getWorld()));
	}
	
	@Test
	public void testTerminate() {
		World world = new World(new Size(100,100));
		assertFalse(world.isTerminated());
		world.terminate();
		assertTrue(world.isTerminated());		
	}
	
	@Test
	public void testTimeToFirstCollision() {
		World world = new World(new Size(500, 500));
		Ship ship = new Ship(new Vector(50, 50), new Vector(0,0), 11, 0, 0);
		Ship ship2 = new Ship(new Vector(100, 50), new Vector(-1,0), 11, 0, 0);
		world.addEntity(ship);
		world.addEntity(ship2);
		Set<Entity> entities = new HashSet<Entity>();
		entities.add(ship);
		entities.add(ship2);
		assertTrue(entities.equals(world.getEntities()));
		Vector position = ship.getCollisionPosition(ship2);
		assertEquals(position.getXComponent(), world.getPositionNextCollision().getXComponent(),0);
		assertEquals(position.getYComponent(), world.getPositionNextCollision().getYComponent(),0);
		
	}

}
