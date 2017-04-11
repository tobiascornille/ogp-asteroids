package asteroids.model;

import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity{

	
	/**
	 * 
	 */
	public Bullet() {
		super(2);
	}
	
	/**
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 */
	public Bullet (Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity, radius);	
	}
	
	/**
	 * Check whether this bullet can have the given radius as its radius.
	 *  
	 * @param  	radius
	 *         	The radius to check.
	 * @return	True if the radius is larger than 1.
	 *     	| 	result == radius > 1
	 */
	@Override
	public boolean canHaveAsRadius(double radius) {
		return radius > 1;
	}
	
	/**
	 * Return the density of this bullet. 
	 */
	@Basic @Raw @Immutable
	public double getDensity() {
		return density;
	}
	
	/**
	 * Return the mass of this bullet.
	 */
	@Basic @Raw @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Variable registering the mass of this bullet.
	 */
	private final double mass = 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();

	/**
	 * Variable registering the density of this bullet.
	 */
	private static final double density = 7.8 * Math.pow(10, 12);
	
	@Override
	public void terminate() {
		if (!isTerminated()) {  
			// Remove the bullet from its ship
			 this.setShip(null); 
			 if (this.getShip().hasAsBullet(this))
				 this.getShip().removeBullet(this);
			 // Remove the bullet from its world
			 this.setWorld(null); 
			 this.getWorld().removeEntity(this);
			 
			 this.isTerminated = true;
		}
	}


	public boolean isValidShip(Ship ship) {
		// TODO add capacity later on
		return (this.getShip() != ship);
	}
    
    private void setShip(Ship ship) {
		 this.ship = ship;
	 }
	 
	private Ship ship; 
	
	public Ship getShip() {
		return this.ship;
	}
	
	public boolean checkOverlap() {
		Set<Entity> entities = this.getWorld().getEntities();
	    for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    if (this.overlap(entity) && entity != this && entity != this.getShip())
			    	if (entity instanceof Bullet && ((Bullet) entity).getShip() == this.getShip())
			    		continue;
			    	else	
			    		return false;
	    }
		return true;
	}
	
	// Testing git client ubuntu

}
