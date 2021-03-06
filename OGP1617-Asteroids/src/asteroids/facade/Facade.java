package asteroids.facade;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.part3.programs.IProgramFactory;
import asteroids.programs.ProgramFactory;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade {

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
			return ship.getPosition().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
			return ship.getVelocity().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		try {
			ship.turn(angle);
		} catch (AssertionError e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}
	
	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			Vector collisionPosition = ship1.getCollisionPosition(ship2);
			if (collisionPosition != null)
				return collisionPosition.toDouble();
			else return null;
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(new Vector(x, y), new Vector(xVelocity, yVelocity), radius, direction, mass);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		} catch (AssertionError e) {
			throw new ModelException(e);
		 }
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getThrusterState();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active)
			ship.thrustOn();
		else
			ship.thrustOff();

	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(new Vector(x, y), new Vector(xVelocity, yVelocity), radius);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		try {
			bullet.terminate();
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		try {
			return bullet.getPosition().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		try {
			return bullet.getVelocity().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSourceShip();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(new Size(width, height));
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		try {
			return world.getSize().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return (Set<? extends Ship>) world.getEntitiesOfType(Ship.class);
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return (Set<? extends Bullet>) world.getEntitiesOfType(Bullet.class);
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntity(ship);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeEntity(ship);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntity(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeEntity(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return new HashSet<Bullet>(ship.getBullets());
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.loadBullet(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullets((Set<Bullet>) bullets);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getTimeToCollisionBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		Vector collision;
		try {
			collision = ((Entity)object).getCollisionBoundaryPosition();
		} catch (Exception e) {
			throw new ModelException(e);
		}
		if (collision == null)
			return null;
		else {
			try {
				return collision.toDouble();
			} catch (IllegalArgumentException e) {
				throw new ModelException(e);
			}
		}
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity)entity1).getTimeToCollision((Entity) entity2);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			Vector collisionPosition = ((Entity) entity1).getCollisionPosition((Entity) entity2);
			if (collisionPosition == null)
				return null;
			return collisionPosition.toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		Vector collision = world.getPositionNextCollision();
		if (collision == null)
			return null;
		else {
			try {
				return collision.toDouble();
			} catch (IllegalArgumentException e) {
				throw new ModelException(e);
			}
		}
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.returnEntityGivenPosition(new Vector (x,y));
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return (Set<? extends Asteroid>) world.getEntitiesOfType(Asteroid.class);
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.addEntity(asteroid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.removeEntity(asteroid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return (Set<? extends Planetoid>) world.getEntitiesOfType(Planetoid.class);
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.addEntity(planetoid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.removeEntity(planetoid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Asteroid(new Vector(x, y), new Vector(xVelocity, yVelocity), radius);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getPosition().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getVelocity().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		try {
			return new Planetoid(new Vector(x, y), new Vector(xVelocity, yVelocity), radius, totalTraveledDistance);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getPosition().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getVelocity().toDouble();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTotalDistanceTraveled();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		try {
			ship.loadProgram(program);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		try {
			return ship.executeProgram(dt);
		} catch (AssertionError e) {
			throw new ModelException(e);
		} catch (Exception e) {
		throw new ModelException(e);
	}
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		try {
			return new ProgramFactory();
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
	
}
