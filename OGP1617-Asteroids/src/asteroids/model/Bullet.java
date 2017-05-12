package asteroids.model;

import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of bullets, which are entities, involving a ship, a source ship and a collision counter.
 * 
 * @invar  The ship of each bullet must be a valid ship for any
 *         bullet.
 *       | isValidShip(getShip())
 * @invar  The source ship of each bullet must be a valid source ship for this
 *         bullet.
 *       | isValidSourceShip(getSourceShip())
 * @invar  The collision counter of each bullet must be a valid collision counter for any
 *         bullet.
 *       | isValidCollisionCounter(getCollisionCounter())	
 * @version 1.0
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 */
public class Bullet extends Entity{
	
	/**
	 * Initialize this new bullet.
	 */
	public Bullet() {
		super(2);
		double mass = 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
		this.setMass(mass);
	}
	
	/**
	 * Initialize this new bullet with given position, velocity and radius.
	 * 
	 * @param position
	 * 		|	The position of this new bullet.
	 * @param velocity
	 * 		|	The velocity of this new bullet.
	 * @param radius
	 * 		|	The radius of this new bullet.
	 * @effect	The position of this new bullet is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new bullet is set to the given velocity.
	 * 		|   this.setVelocity(velocity)
	 * @post  The radius of this new bullet is the given radius.
	 * 		|   this.getRadius() = radius
	 */
	public Bullet (Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		//double mass = 
		super(position, velocity, radius);
		double mass =  4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
		this.setMass(mass);
	}
	
	/**
	 * Terminates this bullet.
	 * 
	 * @post	
	 * 		|	@see implementation
	 */
	@Override
	public void terminate() {
		if (!isTerminated()) {
			if (this.getShip() != null) {
				this.getShip().removeBullet(this);
			}
			if (this.getWorld() != null) {
				this.setSourceShip(null);
				this.getWorld().removeEntity(this);
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
	private Ship ship = null;
	
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
	protected void setSourceShip(Ship sourceShip) throws IllegalArgumentException {
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
	 * Return true if there is an entity overlapping with this bullet in the given world. 
	 * 
	 * @param world
	 * 		  The world to check for overlapping entities.
	 * 
	 * @return
	 * 		 | @see implementation 
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
	 * Return the overlapping entity of this bullet in the given world, if one exists. 
	 * If there is no overlapping entity then null will be returned.
	 * 
	 * @param world
	 * 		  The world to check for overlapping entities.
	 * 
	 * @return
	 * 		 | @see implementation
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
	
	/**
	 * Return the collision counter of this bullet.
	 */
	@Basic @Raw
	public double getCollisionCounter() {
		return this.collisionCounter;
	}
	
	/**
	 * Check whether the given collision counter is a valid collision counter for
	 * any bullet.
	 *  
	 * @param  collision counter
	 *         The collision counter to check.
	 * @return 
	 *       | result == (collisionCounter <= 3)
	*/
	public static boolean isValidCollisionCounter(double collisionCounter) {
		return (collisionCounter <= 3);
	}
	
	/**
	 * Set the collision counter of this bullet to the given collision counter.
	 * 
	 * @param  collisionCounter
	 *         The new collision counter for this bullet.
	 * @post   The collision counter of this new bullet is equal to
	 *         the given collision counter.
	 *       | new.getCollisionCounter() == collisionCounter
	 * @throws IllegalArgumentException
	 *         The given collision counter is not a valid collision counter for any
	 *         bullet.
	 *       | ! isValidCollisionCounter(getCollisionCounter())
	 */
	@Raw
	private void setCollisionCounter(double collisionCounter) 
			throws IllegalArgumentException {
		if (! isValidCollisionCounter(collisionCounter))
			throw new IllegalArgumentException();
		this.collisionCounter = collisionCounter;
	}
	
	/**
	 * Increment the collision counter of this bullet.	
	 */
	public void incrementCollisionCounter() {
		setCollisionCounter(this.getCollisionCounter() + 1);
	}
	
	/**
	 * Variable registering the collision counter of this bullet.
	 */
	private double collisionCounter = 0;
		
	}
