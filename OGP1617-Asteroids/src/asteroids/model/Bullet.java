package asteroids.model;

import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity{

	
	/**
	 * @invar  The ship of each bullet must be a valid ship for any
	 *         bullet.
	 *       | isValidShip(getShip())
	 * @invar  The source ship of each bullet must be a valid source ship for this
	 *         bullet.
	 *       | isValidSourceShip(getSourceShip())
	 */
	public Bullet() {
		super(2);
		this.mass = 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
	}
	
	/**
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 */
	public Bullet (Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity, radius);
		this.mass =  4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
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
	private final double mass;

	/**
	 * Variable registering the density of this bullet.
	 */
	private static final double density = 7.8 * Math.pow(10, 12);
	
	@Override
	public void terminate() {
		if (!isTerminated()) {
			if (this.getShip() != null) {
				Ship ship = this.getShip();
				ship.removeBullet(this);
			}
			if (this.getSourceShip() != null) {
				this.setSourceShip(null);
				World world = this.getWorld();
			 	this.setWorld(null); 
			 	world.removeEntity(this);
			}
			
			this.isTerminated = true;
		}
	}
	
	/**
	 * Return the ship of this bullet.
	 */
	@Basic @Raw
	public Ship getShip() {
		return this.ship;
	}
	
	/**
	 * Check whether the given ship is a valid ship for
	 * this bullet.
	 *  
	 * @param  	ship
	 *         	The ship to check.
	 * @return
	 * 		|	if (ship == null)
	 * 		|		result == true
	 * @return 	
	 *      | 	result == (this.getShip() == null) && (this.inShip(ship))
	 */
	public boolean isValidShip(Ship ship) {
		if (ship == null)
			return true;
		return (this.getShip() == null) && (this.isInShip(ship));
	}
	
	/**
	 * Set the ship of this bullet to the given ship.
	 * 
	 * @param  ship
	 *         The new ship for this bullet.
	 * @post   The ship of this new bullet is equal to
	 *         the given ship.
	 *       | new.getShip() == ship
	 * @throws IllegalArgumentException
	 *         The given ship is not a valid ship for any
	 *         bullet.
	 *       | ! isValidShip(getShip())
	 */
	@Raw
	public void setShip(Ship ship) throws IllegalArgumentException {
		if (! this.isValidShip(ship)) throw new IllegalArgumentException();
		this.ship = ship;
	}
	
	/**
	 * Variable registering the ship of this bullet.
	 */
	private Ship ship;
	
	/**
	 * Return the source ship of this bullet.
	 */
	@Basic @Raw
	public Ship getSourceShip() {
		return this.sourceShip;
	}
	
	/**
	 * Check whether the given source ship is a valid source ship for
	 * this bullet.
	 *  
	 * @param  source ship
	 *         The source ship to check.
	 * @return 
	 *       | result == (this.getShip() == null)
	*/
	public boolean isValidSourceShip(Ship sourceShip) {
		return this.getShip() == null;
	}
	
	/**
	 * Set the source ship of this bullet to the given source ship.
	 * 
	 * @param  sourceShip
	 *         The new source ship for this bullet.
	 * @post   The source ship of this new bullet is equal to
	 *         the given source ship.
	 *       | new.getSourceShip() == sourceShip
	 * @throws IllegalArgumentException
	 *         The given source ship is not a valid source ship for any
	 *         bullet.
	 *       | ! isValidSourceShip(getSourceShip())
	 */
	@Raw
	public void setSourceShip(Ship sourceShip) throws IllegalArgumentException {
		if (! isValidSourceShip(sourceShip))
			throw new IllegalArgumentException();
		this.sourceShip = sourceShip;
	}
	
	/**
	 * Variable registering the source ship of this bullet.
	 */
	private Ship sourceShip;
	
	/**
	 * Check whether this bullet lies fully in the given ship.
	 *  
	 * @param  ship
	 *         The ship wherein the bullet could be.
	 * @return 
	 *       | @see implementation
	 */
	public boolean isInShip(Ship ship) {
		double a = this.getPosition().getXComponent() - ship.getPosition().getXComponent();
		double b = this.getPosition().getYComponent() - ship.getPosition().getYComponent();
		double c = ship.getRadius() - (0.99 * this.getRadius());
		
		// calculation using the Pythagorean theorem
		return Math.pow(a, 2) + Math.pow(b, 2) <= Math.pow(c, 2);
	}
	
	/**
	 * 
	 */
	public boolean checkOverlapInWorld(World world) {	
		
		Set<Entity> entities = world.getEntities();
	    for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			    Entity entity = i.next();   
			    if (this.overlap(entity)) 
			    	return true;	        		   
	    }
		return false;
	}
	
	/**
	 * 
	 */
	public Entity getOverlappingEntityInWorld(World world) {	
		
		Set<Entity> entities = world.getEntities();
	    for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			    Entity entity = i.next();   
			    if (this.overlap(entity)) 
			    	return entity;	        		   
	    }
		return null;
	}
}
