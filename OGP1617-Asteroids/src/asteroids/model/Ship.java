
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
 * @invar  The position of each spaceship must be a valid position for any
 *         spaceship.
 *       | isValidPosition(getPosition())  
 * @invar  The velocity of each spaceship must be a valid velocity for any
 *         spaceship.
 *       | isValidVelocity(getVelocity())
 *       
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
	 * Return the position of this spaceship.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any spaceship.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPosition(double[] position) {
		return true;
		// Not sure if the position can be invalid
	}
	
	/**
	 * Set the position of this spaceship to the given position.
	 * 
	 * @param  x
	 *         The new x coordinate of the position for this spaceship.
	 * @param  y
	 *         The new y coordinate of the position for this spaceship.
	 * @post   The position of this new spaceship is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws ModelException
	 *         The given position is not a valid position for any
	 *         spaceship.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(double x, double y) throws ModelException {
		if (! isValidPosition(new double[] {x,y})) throw new ModelException();
		//Hier nog een verbetering aangebracht
		this.position = new double[] {x,y};
	}
	
	/**
	 * Variable registering the position of this spaceship.
	 */
	private double[] position;
	
	/**
	 * Return the velocity of this spaceship.
	 */
	@Basic @Raw
	public double[] getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any spaceship.
	 *  
	 * @param  velocity
	 *         The velocity to check.
	 * @return 
	 *       | result == Math.sqrt(velocity[0]*velocity[0] + velocity[1]*velocity[1]) <= C
	*/
	public static boolean isValidVelocity(double[] velocity) {
		return (Math.sqrt(velocity[0]*velocity[0] + velocity[1]*velocity[1]) <= C);
	}
	
	/**
	 * Set the velocity of this spaceship to the given velocity.
	 * 
	 * @param  velocity
	 *         The new velocity for this spaceship.
	 * @post   If the given velocity is a valid velocity for any spaceship,
	 *         the velocity of this new spaceship is equal to the given
	 *         velocity.
	 *       | if (isValidVelocity(velocity))
	 *       |   then new.getVelocity() == velocity
	 */
	@Raw
	public void setVelocity(double xVelocity, double yVelocity) {
		if (isValidVelocity(new double[] {xVelocity, yVelocity}))	
			// Slim om dit te doen??
			// misschien beter om isValidVelocity ook met twee parameters te laten werken?
			this.velocity = new double[] {xVelocity, yVelocity};
	}
	
	/**
	 * Variable registering the velocity of this spaceship.
	 */
	private double[] velocity;
	private static final double C = 300000;


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
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	} 
	
	/**
	 * Variable registering the orientation of this Spaceship.
	 */
	private double orientation;
	
}
