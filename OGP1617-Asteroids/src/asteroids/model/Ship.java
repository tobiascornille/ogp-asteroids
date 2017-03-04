
package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;

/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  The orientation of each Spaceship must be a valid orientation for any
 *         Spaceship.
 *       | isValidOrientation(getOrientation())
 *
 */
public class Ship {
	
	// ctrl + space voor suggesties
	
	/**
	 * Initialize this new spaceship with given X coordinate of the position, given Y coordinate of the position, 
	 * given X component of the velocity, given Y component of the velocity and given orientation.
	 * 
	 * @param x
	 * 		  The X coordinate of the position of this new ship. 	  
	 * @param y
	 *        The Y coordinate of the position of this new ship.
	 * @param xVelocity
	 * 		  the X component of the velocity of this new ship.
	 * @param yVelocity
	 *        the Y component of the velocity of this new ship.
	 * @param orientation
	 * 		  The orientation of this new ship.
	 * 
	 * 
	 */
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws ModelException {
		this.setPosition(x, y);
		this.setVelocity(xVelocity, yVelocity);
		this.setOrientation(orientation);
		
	}
	
	/**
	 * Return the orientation of this Spaceship.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any Spaceship.
	 *  
	 * @param  orientation
	 *         The orientation to check.
	 * @return 
	 *       | result == (orientation >= 0) && (orientation <= Math.PI)
	*/
	public static boolean isValidOrientation(double orientation) {
		return (orientation >= 0) && (orientation <= Math.PI);
	}
	
	/**
	 * Set the orientation of this Spaceship to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this Spaceship.
	 * @pre    The given orientation must be a valid orientation for any
	 *         Spaceship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this Spaceship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	} 
	
	/**
	 * Variable registering the orientation of this Spaceship.
	 */
	private double orientation;
	
}
