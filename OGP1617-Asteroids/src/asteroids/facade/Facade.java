package asteroids.facade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.Size;
import asteroids.model.Vector;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade {

	@Override
	public Ship createShip() throws ModelException {
		return new Ship();
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition().toDouble();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity().toDouble();
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
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
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
		ship.thrustOn();
		
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
		bullet.terminate();
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition().toDouble();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocity().toDouble();
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
		return world.getSize().toDouble();
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getWorldShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getWorldBullets();
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
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
			return ((Entity)entity1).getCollisionPosition((Entity) entity2).toDouble();
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
		return world.getPositionNextCollision().toDouble();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt);
		} catch (IllegalArgumentException e) {
			//throw new ModelException(e);
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
	
}
