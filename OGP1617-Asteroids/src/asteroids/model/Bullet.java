package asteroids.model;

import java.util.Iterator;

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
	 * @return	Returns true if the radius is not NaN and if the radius is larger than 1.
	 *     	| 	result == (! Double.isNaN(radius)) && (radius > 1)
	 */
	@Override
	public boolean canHaveAsRadius(double radius) {
		return (! Double.isNaN(radius)) && (radius > 1);
	}
	
	/**
	 * Return the density of this bullet. 
	 */
	@Basic @Raw @Immutable
	public double getDensity() {
		return this.density;
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
	private final double mass = calculateMass();

	/**
	 * Variable registering the density of this bullet.
	 */
	private final double density = 7.8 * Math.pow(10, 12);
	
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

}
