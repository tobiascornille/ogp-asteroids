package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of entities involving a position, velocity, radius and mass.
 * 
 * @invar	The position of each entity must be a valid position for any 
 * 			entity.
 * 		|	isValidPosition(this.getPosition())  
 * @invar	The velocity of each entity must be a valid velocity for any 
 * 			entity.
 * 		|	isValidVelocity(this.getVelocity())
 * @invar	Each entity can have its radius as radius.
 * 		|	canHaveAsRadius(this.getRadius())
 * 
 * @version 1.2
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 */
public abstract class Entity {
	
	/**
	 * Initialize this new entity with given radius.
	 * 
	 * @effect	The radius of this new ship is set to the given radius.
	 * 		|	this.setRadius(radius)
	 */
	public Entity(double radius) {	
		this.radius = radius;
	}
	
	/**
	 * Initialize this new entity with given X coordinate of the position, given Y coordinate of the position, 
	 * given X component of the velocity, given Y component of the velocity and given orientation.
	 * 
	 * @param 	x
	 * 			The X coordinate of the position of this new entity. 	  
	 * @param 	y
	 * 			The Y coordinate of the position of this new entity.
	 * @param	xVelocity
	 * 			the X component of the velocity of this new entity.
	 * @param 	yVelocity
	 * 			the Y component of the velocity of this new entity.
	 * @param	radius
	 * 			The radius of this new entity.
	 * @throws  IllegalArgumentException
	 * 			If the radius is invalid.
	 * 		|	! canHaveAsRadius(radius)
	 * @effect	The position of this new entity is set to the given x, y coordinates.  
	 * 		|	this.setPosition(new Vector(x, y))
	 * @effect  The velocity of this new entity is set to the given xVelocity, yVelocity values.
	 * 		|   this.setVelocity(new Vector(xVelocity, yVelocity)) 
	 * @effect  The radius of this new ship is set to the given radius.
	 * 		|	this.setRadius(radius) 
	 */	
	public Entity (double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		this.setPosition(new Vector(x, y));
		this.setVelocity(new Vector(xVelocity, yVelocity));
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException();
		this.radius = radius;
	}
	
	/**
	 * Return the position of this entity.
	 * 
	 * @return	Returns the position of this entity.
	 * 		|	result == this.position
	 */
	@Basic @Raw
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * Change the position of the entity based on the current position, velocity and time duration dt.
	 *
	 * @param	dt
	 *			The given time duration.
	 * @post	The new position of the entity is the old position 
	 * 			of the entity plus the displacement of the entity.
	 *		|	new.getPosition().equals 
	 *		|		this.getPosition().add(this.getVelocity().times(dt))        
	 * throws	IllegalArgumentException
	 * 			If the time dt is less than 0 or NaN.
	 * 		|	(dt &lt; 0) || (Double.isNaN(dt))
	 */
	public void move(double dt) throws IllegalArgumentException {
		if (dt < 0) throw new IllegalArgumentException();
		Vector newPosition = this.getPosition().add(this.getVelocity().times(dt)); 
		this.setPosition(newPosition);         			
	}
	
	/**
	 * Returns the distance between two entities.
	 * The distance may be negative if both entities overlap.
	 * The distance between a entity and itself is zero.
	 *  
	 * @param 	entity
	 * 		  	The other entity.
	 * @return 	If both entities are the same, 0 is returned.
	 * 			Otherwise, the distance between the two entities 
	 * 			with coordinates (x1, y1) and (x2, y2) respectively 
	 * 			and their radii is returned.
	 * 		|	if (this == entity)
	 * 		|		then result == 0
	 * 		|	else result == (Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) - r1 - r2)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	entity == null
	 */
	public double getDistanceBetween(Entity entity) throws IllegalArgumentException {
		if (entity == null) throw new IllegalArgumentException();
		if (this == entity) return 0;
		double x1 = this.getPosition()[0];
		double x2 = entity.getPosition()[0];
		double y1 = this.getPosition()[1];
		double y2 = entity.getPosition()[1];
		return (Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) - this.getRadius() - entity.getRadius()); 		
	}
	
	/**
	 * Returns true if and only if 2 entities overlap.
	 * A entity always overlaps with itself.
	 * 
	 * @param 	entity
	 * 		  	The other entity.
	 * @return 	Returns true if and only if 2 entities overlap.
	 * 		| 	result == (this.getDistanceBetween(entity) &lt;= 0)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	entity == null
	 */
	public boolean overlap (Entity entity) throws IllegalArgumentException {
		if (entity == null) throw new IllegalArgumentException();
		return (this.getDistanceBetween(entity) <= 0);
	}
		
	/**
	 * Check whether the given position is a valid position for
	 * any entity.
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
	 * Set the position of this entity to the given position.
	 * 
	 * @param  	x
	 *         	The new x coordinate of the position for this entity.
	 * @param  	y
	 *         	The new y coordinate of the position for this entity.
	 * @post   	The position of this new entity is equal to
	 *         	the given position.
	 *      | 	new.getPosition() == position
	 * @throws 	IllegalArgumentException
	 *         	The given position is not a valid position for any
	 *         	entity.
	 *     	| 	! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(Vector position) throws IllegalArgumentException {
		if (!isValidPosition(position)) throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this entity.
	 */
	private Vector position = Vector.NULL_VECTOR;
	
	/**
	 * Return the velocity of this entity.
	 * 
	 * @return	Returns the velocity of this entity.
	 * 		|	result == this.velocity
	 */
	@Basic @Raw
	public Vector getVelocity() {
		return this.velocity;
	}
		
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any entity.
	 *  
	 * @param	velocity
	 *         	The velocity to check.
	 * @return 	Returns true if neither xVelocity nor yVelocity are NaN, and if the absolute value of the speed is less than C.
	 *     	| 	if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity))
	 *     	|		then result == false
	 *     	|	else result == (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C)
	 */
	protected static boolean isValidVelocity(double xVelocity, double yVelocity) {
		if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity))
			return false;
		return (getSpeed(xVelocity, yVelocity) >= -C) && (getSpeed(xVelocity, yVelocity) <= C);
	}
	
	/**
	 * Return the speed of this entity.
	 * 
	 * @param	xVelocity
	 * 			The X component of the velocity of this entity.
	 * @param	yVelocity
	 * 			The Y component of the velocity of this entity.
	 * @return	Returns the square root of the sum of the squares of the x and y component of the velocity.
	 * 		|	result == Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity)
	 */
	private static double getSpeed(double xVelocity, double yVelocity) {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}
	
	/**
	 * Set the velocity of this entity to the given velocity.
	 * 
	 * @param  	xVelocity
	 *         	The new X component of the velocity of this entity.
	 * @param  	yVelocity
	 *         	The new Y component of the velocity of this entity.   
	 * @post   	If the given velocity is a valid velocity for any entity,
	 *         	the velocity of this new entity is equal to the given
	 *         	velocity.
	 *     	| 	if (isValidVelocity(xVelocity, yVelocity))
	 *      |   	then new.getVelocity() == new double[] {xVelocity, yVelocity}
	 */
	@Raw
	protected void setVelocity(double xVelocity, double yVelocity) {
		// If the velocity is not valid, then the velocity is left unchanged.
		if (isValidVelocity(xVelocity, yVelocity))	
			this.velocity = new Vector(xVelocity, yVelocity);
	}
	
	/**
	 * Variable registering the velocity of this entity.
	 */
	private Vector velocity = Vector.NULL_VECTOR;
	
	/**
	 * Constant registering the speed of light.
	 */
	protected static final double C = 300000;
	
	/**
	 * Return the radius of this entity.
	 * 
	 * @return	Returns the radius of this entity.
	 * 		|	result == this.radius
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check whether this entity can have the given radius as its radius.
	 *  
	 * @param  	radius
	 *         	The radius to check.
	 */
	@Raw
	abstract protected boolean canHaveAsRadius(double radius);
	
	/**
	 * Variable registering the radius of this entity.
	 */
	private final double radius;
	
	/**
	 * Return when, if ever, two entities will collide.
	 * Return Double.POSITIVE_INFINITY if the entities never collide.
	 * This method does not apply to entities that overlap. 
	 * 
	 * @param 	entity
	 * 		  	The other entity.
	 * @return  Returns Double.POSITIVE_INFINITY if the entities never collide.
	 * 			Else, returns the time to the moment when the entities collide.
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null pointer as argument
	 * 			or if this method is invoked with two overlapping entities.
	 * 		|	(entity == null) || (this.overlap(entity))
	 */
	public double getTimeToCollision(Entity entity) throws IllegalArgumentException {
		if ((entity == null) || (this.overlap(entity))) throw new IllegalArgumentException();
		
		double dvx = entity.getVelocity()[0] - this.getVelocity()[0];
		double dvy = entity.getVelocity()[1] - this.getVelocity()[1];
		
		double dx = entity.getPosition()[0] - this.getPosition()[0];
		double dy = entity.getPosition()[1] - this.getPosition()[1];
		
		double dvdr = ((dvx * dx) + (dvy * dy));
		double dvdv = Math.pow(dvx, 2) + Math.pow(dvy, 2);
		double drdr = Math.pow(dx, 2) + Math.pow(dy, 2);
		
		double s = this.getRadius() + entity.getRadius();
		double d = Math.pow(dvdr, 2) - (dvdv * (drdr - Math.pow(s, 2))); 
		
		if (((dvx * dx) + (dvy * dy)) >= 0)
			return Double.POSITIVE_INFINITY;
		else if (d <= 0)
			return Double.POSITIVE_INFINITY;
		else
			return (-(dvdr + Math.sqrt(d))/dvdv);		
	}
	
	/**
	 * Return where, if ever, two entities will collide.
	 * Return null if the entities never collide.
	 * 
	 * @param 	entity
	 * 			The other entity
	 * @return	Returns null if the entities never collide, else returns the collision position.
	 * 		|	if (this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY)
	 *		|		then return null
	 *		|	else
	 *		|		return collisionPosition
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null pointer as argument.
	 * 		|	(entity == null)
	 */
	public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException {
		if (entity == null) throw new IllegalArgumentException();
		
		double dt = this.getTimeToCollision(entity);
		if (dt == Double.POSITIVE_INFINITY)
			return null;
		
		double X1 = this.getPosition()[0] + (this.getVelocity()[0] * dt); 
		double Y1 = this.getPosition()[1] + (this.getVelocity()[1] * dt);
		
		double X2 = entity.getPosition()[0] + (entity.getVelocity()[0] * dt); 
		double Y2 = entity.getPosition()[1] + (entity.getVelocity()[1] * dt);
		
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

	public boolean isValidWorld(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Terminate this entity.
	 *
	 * @post   This entity  is terminated.
	 *       | new.isTerminated()
	 */
	 public abstract void terminate();
	 
	 /**
	  * Return a boolean indicating whether or not this entity
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this person is terminated.
	  */
	 private boolean isTerminated = false;
	 
}
