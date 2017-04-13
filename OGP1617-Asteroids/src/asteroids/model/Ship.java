
package asteroids.model;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ships involving a position, velocity, radius and orientation.
 * 
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 * @version 1.1
 * @invar 	The orientation of each ship must be a valid orientation for any 
 * 			ship.
 * 		|	isValidOrientation(getOrientation())
 * @invar   Each ship must have proper bullets.
 *        | hasProperBullets()
 * @invar  The mass of each ship must be a valid mass for any
 *         ship.
 *       | isValidMass(getMass())
 * @invar  The density of each ship must be a valid density for any
 *         ship.
 *       | isValidDensity(getDensity())
 *
 */
public class Ship extends Entity{
	
	/**
	 * Initialize this new ship as a non terminated ship
	 * with default values and no bullets yet.
	 * 
	 * @effect	The radius of this new ship is set to 11.
	 * 		|	this.radius = 11
	 */
	public Ship() {	
		super(11);
	}
	
	/**
	 * Initialize this new ship as a non terminated ship with given 
	 * X coordinate of the position, given Y coordinate of the position, 
	 * given X component of the velocity, given Y component of the 
	 * velocity and given orientation, and with no bullets yet.
	 * 
	 * @param 	position
	 * 			The position of this new ship. 	  
	 * @param	velocity
	 * 			The velocity of this new ship.
	 * @param	radius
	 * 			The radius of this new ship.
	 * @param 	orientation
	 * 			The orientation of this new ship.
	 * @pre     The given orientation must be a valid orientation for any
	 *         	ship.
	 *      | 	isValidOrientation(orientation)	
	 * @post   	This new ship has no bullets yet.
	 *      | 	new.getNbBullets() == 0
	 * @effect	The position of this new ship is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new ship is set to the given velocity.
	 * 		|   this.setVelocity(velocity)
	 * @effect  The orientation of this new ship is set to the given orientation.
	 *      |   this.setOrientation(orientation)  
	 */	
	public Ship (Vector position, Vector velocity, double radius, double orientation, double mass) throws IllegalArgumentException {
		super(position, velocity, radius);
		this.setOrientation(orientation);
		this.setMass(mass);
	}
	
	/**
	 * Return the orientation of this ship.
	 * 
	 * @return	Returns the orientation of this ship.
	 * 		|	result == this.orientation
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Turn the ship by adding a given angle to the current orientation.
	 * 
	 * @param 	angle
	 * 		  	The given angle.
	 * @pre		The given angle plus the current orientation has to be valid.
	 * 	  	  | isValidOrientation(this.getOrientation() + angle)
	 * @post 	The new orientation of the ship is the old orientation plus the angle.
	 * 	      | new.getOrientation() == this.getOrientation() + angle;
	 */
	public void turn(double angle) {
		assert isValidOrientation(this.getOrientation() + angle);
		this.setOrientation(this.getOrientation() + angle);		
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any ship.
	 *  
	 * @param  	orientation
	 *         	The orientation to check.
	 * @return	True if the orientation is between 0 and 2 pi.
	 *     	| 	result == (orientation >= 0) && (orientation <= 2 * Math.PI)
	 */
	private static boolean isValidOrientation(double orientation) {
		return (orientation >= 0) && (orientation <= 2 * Math.PI);
	}
	
	/**
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param	orientation
	 * 			The new orientation for this ship.
	 * @pre    	The given orientation must be a valid orientation for any
	 *         	ship.
	 *      | 	isValidOrientation(orientation)
	 * @post   	The orientation of this ship is equal to the given
	 *         	orientation.
	 *     	| 	new.getOrientation() == orientation
	 */
	@Raw
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	} 
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation = 0;
	
	/**
	 * Check whether this ship can have the given radius as its radius.
	 *  
	 * @param  	radius
	 *         	The radius to check.
	 * @return	True if the radius is larger than 10.
	 *     	| 	result == (radius > 10)
	 */
	public boolean canHaveAsRadius(double radius) {
		return radius > 10;
	}

	/**
	 * Return the set collecting all the bullets
	 * of this ship.
	 * @return
	 * 		|	@see implementation
	 */
	@Basic
	public Deque<Bullet> getBullets() {
		return this.bullets;
	}	
	
	/**
	 * Check whether this ship has the given bullet as one of its
	 * bullets.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 */
	@Basic
	@Raw
	public boolean hasAsBullet(@Raw Bullet bullet) {
		return bullets.contains(bullet);
	}

	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 * @return True if and only if the given bullet is effective
	 *         and that bullet is a valid bullet for a ship.
	 *       | result ==
	 *       |   (bullet != null) &&
	 *       |   Bullet.isValidShip(this)
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null) && (bullet.isValidShip(this));
	}

	/**
	 * Check whether this ship has proper bullets attached to it.
	 * 
	 * @return True if and only if this ship can have each of the
	 *         bullets attached to it as one of its bullets,
	 *         and if each of these bullets references this ship as
	 *         the ship to which they are attached.
	 *       | for each bullet in Bullet:
	 *       |   if (hasAsBullet(bullet))
	 *       |     then canHaveAsBullet(bullet) &&
	 *       |          (bullet.getShip() == this)
	 */
	public boolean hasProperBullets() {
		for (Bullet bullet : bullets) {
			if (!canHaveAsBullet(bullet))
				return false;
			if (bullet.getShip() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of bullets associated with this ship.
	 *
	 * @return  The total number of bullets collected in this ship.
	 *        | result ==
	 *        |   card({bullet:Bullet | hasAsBullet({bullet)})
	 */
	public int getNbBullets() {
		return bullets.size();
	}

	/**
	 * Load the given bullet to this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be loaded.
	 * @throws IllegalArgumentException
	 * 		 | bullet == null
	 * @throws IllegalArgumentException
	 * 		 | bullet.getShip() != null
	 * @post   This ship has the given bullet as one of its bullets.
	 *       | new.hasAsBullet(bullet)
	 */
	public void loadBullet(@Raw Bullet bullet) throws IllegalArgumentException {
		if (bullet == null || bullet.getShip() != null) 
			throw new IllegalArgumentException();
		bullet.setShip(this);
		bullets.add(bullet);
	}
	
	/**
	 * Load the given set of bullets to this ship.
	 * 
	 * @param  bullets
	 *         The set of bullets to be loaded.
	 * @post   This ship has each of the given bullet as one of its bullets.
	 *       | @see implementation
	 */
	public void loadBullets(Set<Bullet> bullets) {
		for (Bullet bullet: bullets) {
			loadBullet(bullet);
		}
	}

	/**
	 * Remove the given bullet from the set of bullets of this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be removed.
	 * @throws IllegalArgumentException
	 *       | ! this.hasAsBullet(bullet)
	 * @throws IllegalArgumentException
	 *       | bullet.getShip() != this
	 * @post   This ship no longer has the given bullet as
	 *         one of its bullets.
	 *       | ! new.hasAsBullet(bullet)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (! this.hasAsBullet(bullet) || bullet.getShip() != this) 
			throw new IllegalArgumentException();
		bullet.setShip(null);
		bullets.remove(bullet);
	}
	
	public void fireBullet() {
		// if there are no bullets left, or if the ship is not in a world, no bullets are fired
		if ((this.getNbBullets() > 0) && (this.getWorld() != null)){
			Bullet bullet = this.getBullets().peek();
			// Remove the bullet from this ship
			this.removeBullet(bullet);
			
			double newX = this.getPosition().getXComponent() + (Math.cos(this.getOrientation()) * (this.getRadius() + bullet.getRadius()));
			double newY = this.getPosition().getYComponent() + (Math.sin(this.getOrientation()) * (this.getRadius() + bullet.getRadius()));
			Vector newPosition = new Vector(newX, newY);
			
			bullet.setPosition(newPosition);
			bullet.setVelocity(this.getVelocity().normalise().times(250));
			this.getWorld().addEntity(bullet);
			
			if (! bullet.checkOverlapInWorld(this.getWorld())) {
				//resolve collision
			}
			if (! bullet.liesWithinBoundsWorld(bullet.getWorld()))
				bullet.terminate();
		}
	}
	
	/**
	 * Variable referencing a set collecting all the bullets
	 * of this ship.
	 * 
	 * @invar  The referenced set is effective.
	 *       | bullets != null
	 * @invar  Each bullet registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each bullet in bullets:
	 *       |   ( (bullet != null) &&
	 *       |     (! bullet.isTerminated()) )
	 */
	private final Deque<Bullet> bullets = new ArrayDeque<>();
	
	/**
	 * Set the density of this ship to the given density.
	 * 
	 * @param  density
	 *         The new density for this ship.
	 * @post   If the given density is a valid density for any ship,
	 *         the density of this new ship is equal to the given
	 *         density.
	 *       | if (isValidDensity(density))
	 *       |   then new.getDensity() == density
	 */
	@Raw
	public void setDensity(double density) {
		if (isValidDensity(density))
			this.density = density;
	}
	
	/**
	 * Return the density of this ship.
	 */
	@Basic @Raw
	public double getDensity() {
		return this.density;
	}
		
	/**
	 * Check whether the given density is a valid density for
	 * any ship.
	 *  
	 * @param  density
	 *         The density to check.
	 * @return 
	 *       | result == (density >= 1.42 * Math.pow(10, 12))
	*/
	public static boolean isValidDensity(double density) {
		return (density >= 1.42 * Math.pow(10, 12));
	}
	
	private double density = 1.42 * Math.pow(10, 12);
	
	/**
	 * Return the mass of this ship.
	 */
	@Basic @Raw
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for
	 * any ship.
	 *  
	 * @param  mass
	 *         The mass to check.
	 * @return 
	 *       | result == (mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE)
	*/
	public boolean isValidMass(double mass) {
		return (mass >= 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE);
	}
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param  mass
	 *         The new mass for this ship.
	 * @post   If the given mass is a valid mass for any ship,
	 *         the mass of this new ship is equal to the given
	 *         mass.
	 *       | if (isValidMass(mass))
	 *       |   then new.getMass() == mass
	 */
	@Raw
	public void setMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			this.mass = 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
	}
	
	/**
	 * Variable registering the mass of this ship.
	 */
	private double mass = 0;
		
	/**
	 * Change the velocity of this ship based on the velocity, 
	 * the orientation, the acceleration and the given time.
	 * 
	 * @param 	dt
	 * 		  	The given time.
	 * @post	The new velocity of the ship is derived by adding the acceleration 
	 * 			times the cosinus or sinus of the current orientation times the time
	 * 		 	to the old velocity respectively.
	 * 	   	| 	new.getVelocity().getXComponent() 
	 * 		|		== this.getVelocity().getXComponent() + (this.getAcceleration() * Math.cos(this.getOrientation()))
	 * 	   	| 	new.getVelocity().getYComponent()
	 * 		|		== this.getVelocity().getYComponent() + (this.getAcceleration() * Math.sin(this.getOrientation()))
	 */
	public void thrust(double dt) {
		Vector vectorAmount = new Vector(this.getAcceleration() * Math.cos(this.getOrientation()), this.getAcceleration() * Math.sin(this.getOrientation()));
		Vector newVelocity = this.getVelocity().add(vectorAmount.times(dt));					
		this.setVelocity(newVelocity);	
	}
	
	public boolean getThrusterState(){
		return this.thruster.getState();
	}
	
	/**
	 * Enable the thruster
	 */
	public void thrustOn() {
		this.thruster = new Thruster(this.thruster.getForce(), true);
	}
	
	/**
	 * Disable the thruster
	 */
	public void thrustOff() {
		this.thruster = new Thruster(this.thruster.getForce(), false);
	}
	
	/**
	 * Calculate the acceleration of this ship.
	 * @return 	The force of the thruster divided by the mass of this ship,
	 * 			if the thruster is enabled.
	 * @return 	0 if the thruster is disabled.
	 */
	public double getAcceleration() {
		if (this.getThrusterState())
			return thruster.getForce() / this.getMass();
		return 0;
	}
	
	/**
	 * Variable registering the thruster of this ship.
	 */
	private Thruster thruster = Thruster.DEFAULT;
	

	
	@Override
	public void terminate() {
		if (!isTerminated()) {
			// We avoid ConcurrentModificationException by using an iterator
			 for (Iterator<Bullet> i = bullets.iterator(); i.hasNext();) {
			    Bullet bullet = i.next();
			    bullet.terminate();    
			 }
			 World world = this.getWorld();
			 this.setWorld(null); 
			 world.removeEntity(this);
			 this.isTerminated = true;
		 }
		
	}
	/**
	 * Checks whether a ship overlaps with any entities in it's world.
	 * 
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
}
