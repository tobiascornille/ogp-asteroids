
package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar VERGEET NIET DE NOMINALE DINGEN TE DOEN KUT
 *
 */
public class Ship {
	
	// ctrl + space voor suggesties
	
	/**
	 * Initialize this new spaceship with given X-position, given Y-position, given X-velocity, 
	 * given Y-velocity and given orientation.
	 * 
	 * @param positionX
	 * 		  The X coordinate of the position of this new ship. 	  
	 * @param positionY
	 *        The Y coordinate of the position of this new ship.
	 * @param velocityX
	 * 		  the X component of the velocity of this new ship.
	 * @param velocityY
	 *        the Y component of the velocity of this new ship.
	 * @param orientation
	 * 		  The orientation of this new ship.
	 * 
	 * 
	 */
	public Ship (double positionX, double positionY, double velocityX, double velocityY, double orientation) {
		
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		this.setVelocityX(velocityX);
		this.setVelocityY(velocityY);
		this.setOrientation(orientation);
		
	}
	
	/**
	 * @invar  The orientation of each Spaceship must be a valid orientation for any
	 *         Spaceship.
	 *       | isValidOrientation(getOrientation())
	 */

	/**
	 * Initialize this new Spaceship with given orientation.
	 * 
	 * @param  orientation
	 *         The orientation for this new Spaceship.
	 * @pre    The given orientation must be a valid orientation for any Spaceship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this new Spaceship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	public Ship(double orientation) {
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
	 *       | result == 
	*/
	public static boolean isValidOrientation(double orientation) {
		return false;
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
	
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;

	
}
