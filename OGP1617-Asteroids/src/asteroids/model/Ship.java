
package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;

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
 */
public class Ship extends Entity{
	
	/**
	 * Initialize this new ship with default values.
	 * 
	 * @effect	The radius of this new ship is set to 11.
	 * 		|	this.radius = 11
	 */
	public Ship() {	
		super(11);
	}
	
	/**
	 * Initialize this new ship with given X coordinate of the position, given Y coordinate of the position, 
	 * given X component of the velocity, given Y component of the velocity and given orientation.
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
		
		if (! isValidVelocity(newVelocity)) {
			newVelocity = newVelocity.normalise().times(C);
		}
					
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
	protected boolean canHaveAsRadius(double radius) {
		return (! Double.isNaN(radius)) && (radius > 10);
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}
}
