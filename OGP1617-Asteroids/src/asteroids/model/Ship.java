
package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	public Ship (Vector position, Vector velocity, double radius, double orientation) throws IllegalArgumentException {
		super(position, velocity, radius);
		this.setOrientation(orientation);
	}
	
	/**
	 * Change the velocity of the ship based on the current velocity, the current orientation, and on a given amount.
	 * 
	 * @param 	amount
	 * 		  	The given amount.
	 * @post	UPDATE NEEDED
	 * 			The new velocity of the ship is derived by adding the amount times the cosinus or sinus of the current orientation
	 * 		 	to the old xVelocity and yVelocity, respectively.
	 * 	   	| 	new.getVelocity()[0] == this.getVelocity()[0] + (amount * Math.cos(this.getOrientation()))
	 * 	   	| 	new.getVelocity()[1] == this.getVelocity()[1] + (amount * Math.sin(this.getOrientation()))
	 * 	   	| 	if (! isValidVelocity(newXVelocity, newYVelocity))
	 * 	   	|		then newXVelocity = C * Math.cos(this.getOrientation())
	 *	   	|		     newYVelocity = C * Math.sin(this.getOrientation())
	 */
	public void thrust(double amount) {
		if (amount < 0) amount = 0;
		
		Vector vectorAmount = new Vector(amount * Math.cos(this.getOrientation()), amount * Math.sin(this.getOrientation()));
		Vector newVelocity = this.getVelocity().add(vectorAmount);					
		this.setVelocity(newVelocity);	
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
	 * @return	Returns true if the orientation is not NaN and if the orientation is between 0 and 2 pi.
	 *     	| 	result == (! Double.isNaN(orientation)) && (orientation >= 0) && (orientation <= 2 * Math.PI)
	 */
	private static boolean isValidOrientation(double orientation) {
		return (! Double.isNaN(orientation)) && (orientation >= 0) && (orientation <= 2 * Math.PI);
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
	 * Check whether this entity can have the given radius as its radius.
	 *  
	 * @param  	radius
	 *         	The radius to check.
	 * @return	Returns true if the radius is not NaN and if the radius is larger than 10.
	 *     	| 	result == (! Double.isNaN(radius)) && (radius > 10)
	 */
	public boolean canHaveAsRadius(double radius) {
		return (! Double.isNaN(radius)) && (radius > 10);
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
		return (bullet != null) && (Bullet.isValidShip(this));
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
	 * Add the given bullet to the set of bullets of this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be added.
	 * @throws IllegalArgumentException
	 * 		 | bullet == null
	 * @throws IllegalArgumentException
	 * 		 | bullet.getShip() != this
	 * @post   This ship has the given bullet as one of its bullets.
	 *       | new.hasAsBullet(bullet)
	 */
	public void addBullet(@Raw Bullet bullet) throws IllegalArgumentException {
		if (bullet == null || bullet.getShip() != this) throw new IllegalArgumentException();
		bullets.add(bullet);
	}

	/**
	 * Remove the given bullet from the set of bullets of this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be removed.
	 * @throws IllegalArgumentException
	 *       | ! this.hasAsBullet(bullet)
	 * @throws IllegalArgumentException
	 *       | bullet.getShip() != null
	 * @post   This ship no longer has the given bullet as
	 *         one of its bullets.
	 *       | ! new.hasAsBullet(bullet)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (! this.hasAsBullet(bullet) || bullet.getShip() != null) throw new IllegalArgumentException();
		bullets.remove(bullet);
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
	private final Set<Bullet> bullets = new HashSet<Bullet>();

	@Override
	public void terminate() {
		if (!isTerminated()) {
			// We avoid ConcurrentModificationException by using an iterator
			 for (Iterator<Bullet> i = bullets.iterator(); i.hasNext();) {
			    Bullet bullet = i.next();
			    bullet.terminate();    
			 }
			 this.setWorld(null); 
			 this.getWorld().removeEntity(this);
			 this.isTerminated = true;
		 }
		
	}
}
