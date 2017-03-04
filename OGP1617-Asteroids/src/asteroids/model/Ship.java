
package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;

/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  The orientation of each ship must be a valid orientation for any
 *         ship.
 *       | isValidOrientation(getOrientation())
 * @invar  The position of each ship must be a valid position for any
 *         ship.
 *       | isValidPosition(getPosition())  
 * @invar  The velocity of each ship must be a valid velocity for any
 *         ship.
 *       | isValidVelocity(getVelocity())
 * @invar  Each ship can have its radius as radius.
 *       | canHaveAsRadius(this.getRadius())	      
 *       
 */
public class Ship {
	
	// ctrl + space voor suggesties
	
	/**
	 * Initialize this new ship with default values.
	 * 
	 */
	public Ship() {	
		this.radius = 11;
	}
	
	/**
	 * Initialize this new ship with given X coordinate of the position, given Y coordinate of the position, 
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
		if (! canHaveAsRadius(radius)) throw new ModelException();
		this.radius = radius;
	}
	
	/**
	 * Return the position of this ship.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPosition(double x, double y) {
		return true;	// position can't be invalid in an unbounded space
	}
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param  x
	 *         The new x coordinate of the position for this ship.
	 * @param  y
	 *         The new y coordinate of the position for this ship.
	 * @post   The position of this new ship is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws ModelException
	 *         The given position is not a valid position for any
	 *         ship.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(double x, double y) throws ModelException {
		if (! isValidPosition(x, y)) throw new ModelException();
		this.position = new double[] {x, y};
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private double[] position;
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic @Raw
	public double[] getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any ship.
	 *  
	 * @param  velocity
	 *         The velocity to check.
	 * @return 
	 *       | result == (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C)
	 */
	public static boolean isValidVelocity(double xVelocity, double yVelocity) {
		return (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C);
	}
	
	public static double getSpeed(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
	}
	
	/**
	 * Set the velocity of this ship to the given velocity.
	 * 
	 * @param  xVelocity
	 *         The new X component of the velocity of this ship.
	 * @param  yVelocity
	 *         The new Y component of the velocity of this ship.   
	 * @post   If the given velocity is a valid velocity for any ship,
	 *         the velocity of this new ship is equal to the given
	 *         velocity.
	 *       | if (isValidVelocity(xVelocity, yVelocity))
	 *       |   then new.getVelocity() == velocity
	 */
	@Raw
	public void setVelocity(double xVelocity, double yVelocity) {
		if (isValidVelocity(xVelocity, yVelocity))	
			this.velocity = new double[] {xVelocity, yVelocity};
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private double[] velocity;
	
	/**
	 * Constant registering the speed of light.
	 */
	private static final double C = 300000;
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check whether this ship can have the given radius as its radius.
	 *  
	 * @param  radius
	 *         The radius to check.
	 * @return 
	 *       | result == radius > 10
	*/
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return radius > 10;
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
		
	/**
	 * Return the orientation of this ship.
	 * 
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any ship.
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
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this ship.
	 * @pre    The given orientation must be a valid orientation for any
	 *         ship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this ship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	@Raw
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	} 
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
		
}
