
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
 * @version 1.0
 * @invar 	The orientation of each ship must be a valid orientation for any 
 * 			ship.
 * 		|	isValidOrientation(getOrientation())
 * @invar	The position of each ship must be a valid position for any 
 * 			ship.
 * 		|	isValidPosition(getPosition())  
 * @invar	The velocity of each ship must be a valid velocity for any 
 * 			ship.
 * 		|	isValidVelocity(getVelocity())
 * @invar	Each ship can have its radius as radius.
 * 		|	canHaveAsRadius(this.getRadius())
 */
public class Ship {
	
	/**
	 * Initialize this new ship with default values.
	 * 
	 * @effect	The radius of this new ship is set to 11.
	 * 		|	this.radius = 11
	 */
	public Ship() {	
		this.radius = 11;
	}
	
	/**
	 * Initialize this new ship with given X coordinate of the position, given Y coordinate of the position, 
	 * given X component of the velocity, given Y component of the velocity and given orientation.
	 * 
	 * @param 	x
	 * 			The X coordinate of the position of this new ship. 	  
	 * @param 	y
	 * 			The Y coordinate of the position of this new ship.
	 * @param	xVelocity
	 * 			the X component of the velocity of this new ship.
	 * @param 	yVelocity
	 * 			the Y component of the velocity of this new ship.
	 * @param	radius
	 * 			The radius of this new ship.
	 * @param 	orientation
	 * 			The orientation of this new ship.
	 * @throws  IllegalArgumentException
	 * 			If the radius is invalid.
	 * 		|	! canHaveAsRadius(radius)
	 * @pre     The given orientation must be a valid orientation for any
	 *         	ship.
	 *      | 	isValidOrientation(orientation)	
	 * @effect	The position of this new ship is set to the given x, y coordinates.  
	 * 		|	this.setPosition(x, y)
	 * @effect  The velocity of this new ship is set to the given xVelocity, yVelocity values.
	 * 		|   this.setVelocity(xVelocity, yVelocity)
	 * @effect  The orientation of this new ship is set to the given orientation.
	 *      |   this.setOrientation(orientation)    
	 * @effect  The radius of this new ship is set to the given radius.
	 * 		|	this.radius = radius     
	 */	
	public Ship (double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalArgumentException {
		this.setPosition(x, y);
		this.setVelocity(xVelocity, yVelocity);	
		this.setOrientation(orientation);
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException();
		this.radius = radius;
	}
	
	/**
	 * Return the position of this ship.
	 * 
	 * @return	Returns the position of this ship.
	 * 		|	result == this.position
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Change the position of the ship based on the current position, velocity and time duration dt.
	 *
	 * @param	dt
	 *			The given time duration.
	 * @post	The new position of the ship is the old position of the ship plus the displacement of the ship.
	 *		|	new.getPosition()[0] == this.getPosition()[0] + (this.getVelocity()[0] * dt)         
	 *		|	new.getPosition()[1] == this.getPosition()[1] + (this.getVelocity()[1] * dt)
	 * throws	IllegalArgumentException
	 * 			If the time dt is less than 0 or NaN.
	 * 		|	(dt &lt; 0) || (Double.isNaN(dt))
	 */
	public void move(double dt) throws IllegalArgumentException {
		if ((dt < 0) || (Double.isNaN(dt))) throw new IllegalArgumentException();
		double newX = this.getPosition()[0] + (this.getVelocity()[0] * dt); 
		double newY = this.getPosition()[1] + (this.getVelocity()[1] * dt);
		this.setPosition(newX, newY);         			
	}
	
	/**
	 * Returns the distance between two ships.
	 * The distance may be negative if both ships overlap.
	 * The distance between a ship and itself is zero.
	 *  
	 * @param 	ship
	 * 		  	The other ship.
	 * @return 	If both ships are the same, 0 is returned.
	 * 			Otherwise, the distance between the two ships 
	 * 			with coordinates (x1, y1) and (x2, y2) respectively 
	 * 			and their radii is returned.
	 * 		|	if (this == ship)
	 * 		|		then result == 0
	 * 		|	else result == (Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) - r1 - r2)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	ship == null
	 */
	public double getDistanceBetween(Ship ship) throws IllegalArgumentException {
		if (ship == null) throw new IllegalArgumentException();
		if (this == ship) return 0;
		double x1 = this.getPosition()[0];
		double x2 = ship.getPosition()[0];
		double y1 = this.getPosition()[1];
		double y2 = ship.getPosition()[1];
		return (Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) - this.getRadius() - ship.getRadius()); 		
	}
	
	/**
	 * Returns true if and only if 2 ships overlap.
	 * A ship always overlaps with itself.
	 * 
	 * @param 	ship
	 * 		  	The other ship.
	 * @return 	Returns true if and only if 2 ships overlap.
	 * 		| 	result == (this.getDistanceBetween(ship) &lt;= 0)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	ship == null
	 */
	public boolean overlap (Ship ship) throws IllegalArgumentException {
		if (ship == null) throw new IllegalArgumentException();
		return (this.getDistanceBetween(ship) <= 0);
	}
		
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
	 *  
	 * @param	x
	 *         	The x coordinate to check.
	 * @param	y
	 *         	The y coordinate to check.
	 * @return 	Returns true if neither x nor y are NaN.
	 *     	| 	result == (! Double.isNaN(x) && ! Double.isNaN(y))
	 */
	private static boolean isValidPosition(double x, double y) {
		return (! Double.isNaN(x)) && (! Double.isNaN(y));
	}
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param  	x
	 *         	The new x coordinate of the position for this ship.
	 * @param  	y
	 *         	The new y coordinate of the position for this ship.
	 * @post   	The position of this new ship is equal to
	 *         	the given position.
	 *      | 	new.getPosition() == position
	 * @throws 	IllegalArgumentException
	 *         	The given position is not a valid position for any
	 *         	ship.
	 *     	| 	! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(double x, double y) throws IllegalArgumentException {
		if (!isValidPosition(x, y)) throw new IllegalArgumentException();
		this.position = new double[] {x, y};
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private double[] position = {0,0};
	
	/**
	 * Return the velocity of this ship.
	 * 
	 * @return	Returns the velocity of this ship.
	 * 		|	result == this.velocity
	 */
	@Basic @Raw
	public double[] getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Change the velocity of the ship based on the current velocity, the current orientation, and on a given amount.
	 * 
	 * @param 	amount
	 * 		  	The given amount.
	 * @post	The new velocity of the ship is derived by adding the amount times the cosinus or sinus of the current orientation
	 * 		 	to the old xVelocity and yVelocity, respectively.
	 * 	   	| 	new.getVelocity()[0] == this.getVelocity()[0] + (amount * Math.cos(this.getOrientation()))
	 * 	   	| 	new.getVelocity()[1] == this.getVelocity()[1] + (amount * Math.sin(this.getOrientation()))
	 * 	   	| 	if (! isValidVelocity(newXVelocity, newYVelocity))
	 * 	   	|		then newXVelocity = C * Math.cos(this.getOrientation())
	 *	   	|		     newYVelocity = C * Math.sin(this.getOrientation())
	 */
	public void thrust(double amount) {
		if (amount < 0) amount = 0;
		double newXVelocity = this.getVelocity()[0] + (amount * Math.cos(this.getOrientation()));
		double newYVelocity = this.getVelocity()[1] + (amount * Math.sin(this.getOrientation()));
		 
		if (! isValidVelocity(newXVelocity, newYVelocity)) {
			newXVelocity = C * Math.cos(this.getOrientation());
			newYVelocity = C * Math.sin(this.getOrientation());
		}
					
		this.setVelocity(newXVelocity, newYVelocity);	
	}
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any ship.
	 *  
	 * @param	velocity
	 *         	The velocity to check.
	 * @return 	Returns true if neither xVelocity nor yVelocity are NaN, and if the absolute value of the speed is less than C.
	 *     	| 	if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity))
	 *     	|		then result == false
	 *     	|	else result == (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C)
	 */
	private static boolean isValidVelocity(double xVelocity, double yVelocity) {
		if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity))
			return false;
		return (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C);
	}
	
	/**
	 * Return the speed of this ship.
	 * 
	 * @param	xVelocity
	 * 			The X component of the velocity of this ship.
	 * @param	yVelocity
	 * 			The Y component of the velocity of this ship.
	 * @return	Returns the square root of the sum of the squares of the x and y component of the velocity.
	 * 		|	result == Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	private static double getSpeed(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}
	
	/**
	 * Set the velocity of this ship to the given velocity.
	 * 
	 * @param  	xVelocity
	 *         	The new X component of the velocity of this ship.
	 * @param  	yVelocity
	 *         	The new Y component of the velocity of this ship.   
	 * @post   	If the given velocity is a valid velocity for any ship,
	 *         	the velocity of this new ship is equal to the given
	 *         	velocity.
	 *     	| 	if (isValidVelocity(xVelocity, yVelocity))
	 *      |   	then new.getVelocity() == velocity
	 */
	@Raw
	private void setVelocity(double xVelocity, double yVelocity) {
		// If the velocity is not valid, then the velocity is left unchanged.
		if (isValidVelocity(xVelocity, yVelocity))	
			this.velocity = new double[] {xVelocity, yVelocity};
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private double[] velocity = {0,0};
	
	/**
	 * Constant registering the speed of light.
	 */
	private static final double C = 300000;
	
	/**
	 * Return the radius of this ship.
	 * 
	 * @return	Returns the radius of this ship.
	 * 		|	result == this.radius
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check whether this ship can have the given radius as its radius.
	 *  
	 * @param  	radius
	 *         	The radius to check.
	 * @return	Returns true if the radius is not NaN and if the radius is larger than 10.
	 *     	| 	result == (! Double.isNaN(radius)) && (radius > 10)
	 */
	@Raw
	private boolean canHaveAsRadius(double radius) {
		return (! Double.isNaN(radius)) && (radius > 10);
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
		
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
	 * Returns when, if ever, two ships will collide.
	 * Returns Double.POSITIVE_INFINITY if the ships never collide.
	 * This method does not apply to ships that overlap. 
	 * 
	 * @param 	ship
	 * 		  	The other ship.
	 * @return  Returns Double.POSITIVE_INFINITY if the ships never collide.
	 * 			Else, returns the time to the moment when the ships collide.
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null pointer as argument
	 * 			or if this method is invoked with two overlapping ships.
	 * 		|	(ship == null) || (this.overlap(ship))
	 */
	public double getTimeToCollision(Ship ship) throws IllegalArgumentException {
		if ((ship == null) || (this.overlap(ship))) throw new IllegalArgumentException();
		
		double dvx = ship.getVelocity()[0] - this.getVelocity()[0];
		double dvy = ship.getVelocity()[1] - this.getVelocity()[1];
		
		double dx = ship.getPosition()[0] - this.getPosition()[0];
		double dy = ship.getPosition()[1] - this.getPosition()[1];
		
		double dvdr = ((dvx * dx) + (dvy * dy));
		double dvdv = Math.pow(dvx, 2) + Math.pow(dvy, 2);
		double drdr = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		double s = this.getRadius() + ship.getRadius();
		double d = Math.pow(dvdr, 2) - (dvdv * (drdr - Math.pow(s, 2))); 
		
		if (((dvx * dx) + (dvy * dy)) >= 0)
			return Double.POSITIVE_INFINITY;
		else if (d <= 0)
			return Double.POSITIVE_INFINITY;
		else
			return (-(dvdr + Math.sqrt(d))/dvdv);		
	}
	
	/**
	 * Returns where, if ever, two ships will collide.
	 * Returns null if the ships never collide.
	 * 
	 * @param 	ship
	 * 			The other ship
	 * @return	Returns null if the ships never collide, else returns the collision position.
	 * 		|	if (this.getTimeToCollision(ship) == Double.POSITIVE_INFINITY)
	 *		|		then return null
	 *		|	else
	 *		|		return collisionPosition
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null pointer as argument.
	 * 		|	(ship == null)
	 */
	public double[] getCollisionPosition(Ship ship) throws IllegalArgumentException {
		if (ship == null) throw new IllegalArgumentException();
		
		double dt = this.getTimeToCollision(ship);
		if (dt == Double.POSITIVE_INFINITY)
			return null;
		
		double X1 = this.getPosition()[0] + (this.getVelocity()[0] * dt); 
		double Y1 = this.getPosition()[1] + (this.getVelocity()[1] * dt);
		
		double X2 = ship.getPosition()[0] + (ship.getVelocity()[0] * dt); 
		double Y2 = ship.getPosition()[1] + (ship.getVelocity()[1] * dt);
		
		double theta = Math.atan((Y2-Y1)/(X2-X1));
		
		double x;
		double y;
		
		if (X1 < X2) {
			x = X1 + (this.getRadius() * Math.cos(theta));
			y = Y1 + (this.getRadius() * Math.sin(theta));
		}
		else {
			x = X1 + (this.getRadius() * -Math.cos(theta));
			y = Y1 + (this.getRadius() * -Math.sin(theta));
		}
	
		return new double[] {x, y};
	}
}
