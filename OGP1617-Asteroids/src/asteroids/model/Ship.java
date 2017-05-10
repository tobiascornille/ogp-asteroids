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
 * @invar  The total mass of each ship must be a valid total mass for that
 *         ship.
 *       | isValidTotalMass(getTotalMass())
 * @version 2.0
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 */
public class Ship extends Entity{
	
	/**
	 * Initialize this new ship as a non terminated ship
	 * with no bullets yet.
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
		this.setTotalMass(mass);
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
	 * Return the total mass of this ship.
	 */
	@Basic @Raw
	public double getTotalMass() {
		return this.totalMass;
	}
	
	/**
	 * Check whether the given total mass is a valid total mass for
	 * this ship.
	 *  
	 * @param  totalMass
	 *         The total mass to check.
	 * @return 
	 *       | result >= this.getMass()
	*/
	public boolean isValidTotalMass(double totalMass) {
		return totalMass >= this.getMass();
	}
	
	/**
	 * Set the total mass of this ship to the given total mass.
	 * 
	 * @param  totalMass
	 *         The new total mass for this ship.
	 * @post   If the given total mass is a valid total mass for any ship,
	 *         the total mass of this new ship is equal to the given
	 *         total mass.
	 *       | if (isValidTotalMass(totalMass))
	 *       |   then new.getTotalMass() == totalMass
	 */
	@Raw
	public void setTotalMass(double totalMass) {
		if (isValidTotalMass(totalMass))
			this.totalMass = totalMass;
	}
	
	/**
	 * Variable registering the total mass of this ship.
	 */
	private double totalMass = 0;

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
		this.setTotalMass(this.getTotalMass() + bullet.getMass());
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
		this.setTotalMass(this.getTotalMass() - bullet.getMass());
	}
	
	public void fireBullet() {
		// if there are no bullets left, or if the ship is not in a world, no bullets are fired
		if ((this.getNbBullets() > 0) && (this.getWorld() != null)){
			Bullet bullet = this.getBullets().peek();
			// Remove the bullet from this ship
			this.removeBullet(bullet);
			bullet.setSourceShip(this);
			
			double newX = this.getPosition().getXComponent() + (Math.cos(this.getOrientation()) * ( this.getRadius() +  bullet.getRadius()));
			double newY = this.getPosition().getYComponent() + (Math.sin(this.getOrientation()) * (this.getRadius() +  bullet.getRadius()));
			Vector newPosition = new Vector(newX, newY);
			
			bullet.setPosition(newPosition);
			bullet.setVelocity(new Vector(250 * Math.cos(this.getOrientation()), 250 * Math.sin(this.getOrientation())));
			try {
				this.getWorld().addEntity(bullet);
			} catch (IllegalArgumentException e) {
				
				if (bullet.checkOverlapInWorld(this.getWorld())) {
					this.getWorld().objectCollision(this, bullet.getOverlappingEntityInWorld(this.getWorld()));
				}
				
				else
					bullet.terminate();
			}	
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
		Vector vectorAngle = new Vector(Math.cos(this.getOrientation()), Math.sin(this.getOrientation()));
		Vector newVelocity = this.getVelocity().add(vectorAngle.times(this.getAcceleration()).times(dt));					
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
			return thruster.getForce() / this.getTotalMass();
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
			 world.removeEntity(this);
			 this.isTerminated = true;
		 }
		
	}
	/**
	 * Checks whether a ship overlaps with any entities in its world.
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

