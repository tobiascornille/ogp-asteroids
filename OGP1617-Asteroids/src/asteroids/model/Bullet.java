
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
		double mass = 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
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
		// invalid mass so that the mass will get corrected in the setter
		super(position, velocity, radius, 0);
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
	 * @param  sourceShip
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
	 * @param  collisionCounter
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

	/**
	 * Check whether any bullet can have the given radius as its radius.
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
	 * Check whether this bullet has a valid position in the given world.
	 *
	 * @param 	world
	 * 			The given world.
	 * @return	True if this bullet is in an unbounded space.
	 * 		|	if (world == null && this.getShip() == null)
	 *		|		then result == true;
	 * @return	True if this bullet is in a ship.
	 * 		|	if (world == null && this.isInShip(this.getShip()))
	 *		|		then result == true;
	 * @return	True if this bullet lies within the bounds of the given world
	 *			and this bullet doesn't overlap in the given world.
	 *		|	if (this.liesWithinBoundsWorld(world) && (! this.checkOverlapInWorld(world)))
	 *		|		then result == true;
	 */
	@Override
	public boolean hasValidPositionInWorld(World world) {
		if (world == null) {
			//True if this bullet is in unbounded space
			if (this.getShip() == null)
				return true;

			//True if this bullet is in a ship
			if (this.isInShip(this.getShip()))
				return true;
		}

		else if (this.liesWithinBoundsWorld(world) && (! this.checkOverlapInWorld(world)))
			return true;

		return false;
	}

	/**
	 * Check whether the given density is a valid density for
	 * any bullet.
	 *
	 * @param	density
	 *         	The density to check.
	 * @return
	 *      |   @see implementation
	 */
	@Override
	public boolean isValidDensity(double density) {
		return density == 7.8E12;
	}

	/**
	 * Return the default density for any bullet.
	 *
	 * @return	The default density for any bullet.
	 * 		|	result == 7.8E12
	 */
	@Override
	public double getDefaultDensity() {
		return 7.8E12;
	}

	/**
	 * Check whether the given mass is a valid mass for
	 * this bullet.
	 *
	 * @param  	mass
	 *         	The mass to check.
	 * @return
	 *      |	result == (mass == 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE)
	 */
	@Override
	public boolean isValidMass(double mass) {
		return mass == 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE;
	}

	/**
	 * Resolve the collision between this bullet and another entity.
	 * 
	 * @param	otherEntity
	 * 			The other entity involved in the collision. 
	 * @effect	
	 * 			| if (otherEntity == this.getSourceShip())
	 * 			|	then this.getWorld() = null && otherEntity.getBullets().contains(this)
	 *			| else
	 *			|	then this.isTerminated() && otherEntity.isTerminated()	
	 */
	void objectCollision(Entity otherEntity) {
		if (otherEntity == this.getSourceShip()) {
			this.getWorld().removeEntity(this);
			this.setPosition(otherEntity.getPosition());
			((Ship) otherEntity).loadBullet(this);
		}
		else {
			this.terminate();
			otherEntity.terminate();
		}
	}
}
