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
	 * @param 	position
	 * 			The position of this new entity. 	  
	 * @param 	velocity
	 * 			The velocity of this new entity.
	 * @param	radius
	 * 			The radius of this new entity.
	 * @throws  IllegalArgumentException
	 * 			If the radius is invalid.
	 * 		|	! canHaveAsRadius(radius)
	 * @effect	The position of this new entity is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new entity is set to the given velocity.
	 * 		|   this.setVelocity(velocity) 
	 * @effect  The radius of this new ship is set to the given radius.
	 * 		|	this.setRadius(radius) 
	 */	
	public Entity (Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		this.setPosition(position);
		this.setVelocity(velocity);
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException();
		this.radius = radius;
	}
	
	/**
	 * Return the position of this entity.
	 * 
	 * @return	The position of this entity.
	 * 		|	result == this.position
	 */
	@Basic @Raw
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any entity.
	 *  
	 * @param	position
	 *         	The position to check.
	 * @return 	True.
	 *     	| 	result == true
	 */
	public static boolean isValidPosition(Vector position) {
		//	TODO: check new assignment
		return true;
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
	 * @param 	other
	 * 		  	The other entity.
	 * @return 	0, if both entities are the same.
	 * 		|	if (this == entity)
	 * 		|		then result == 0
	 * @return	The distance between the two entities with coordinates (x1, y1) 
	 * 			and (x2, y2) respectively and their radii is returned.
	 * 		|	result == (Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) - r1 - r2)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	entity == null
	 */
	public double getDistanceBetween(Entity other) throws IllegalArgumentException {
		if (other == null) throw new IllegalArgumentException();
		if (this == other) return 0;
		Vector differenceVector = other.getPosition().subtract(this.getPosition());
				
		return (Math.sqrt(differenceVector.dot(differenceVector)) - this.getRadius() - other.getRadius()); 		
	}
	
	/**
	 * Returns true if and only if 2 entities overlap.
	 * A entity always overlaps with itself.
	 * 
	 * @param 	other
	 * 		  	The other entity.
	 * @return 	Returns true if and only if 2 entities overlap.
	 * 		| 	result == (this.getDistanceBetween(entity) &lt;= 0)
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null as argument. 
	 * 		|	entity == null
	 */
	public boolean overlap (Entity other) throws IllegalArgumentException {
		if (other == null) throw new IllegalArgumentException();
		return (this.getDistanceBetween(other) <= 0);
	}
	
	/**
	 * Set the position of this entity to the given position.
	 * 
	 * @param  	position
	 *         	The new position for this entity.
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
	 * @return 	True if the absolute value of the speed, the magnitude 
	 * 			of the velocity vector, is less than C.
	 *     	|	result == velocity.getMagnitude() <= C
	 */
	public static boolean isValidVelocity(Vector velocity) {
		return velocity.getMagnitude() <= C;
	}
	
	/**
	 * Set the velocity of this entity to the given velocity.
	 * 
	 * @param  	velocity
	 *         	The new velocity of this entity.
	 * @post   	If the given velocity is a valid velocity for any entity,
	 *         	the velocity of this new entity is equal to the given
	 *         	velocity.
	 *     	| 	if (isValidVelocity(velocity))
	 *      |   	then new.getVelocity() == velocity
	 * @post   	If the given velocity is not a valid velocity for any entity,
	 *         	the velocity of this new entity is equal to the maximum
	 *			velocity, while retaining the correct velocity angle.
	 *     	| 	if (! isValidVelocity(velocity))
	 *      |   	then new.getVelocity() == velocity.normalise().times(C)
	 */
	@Raw
	protected void setVelocity(Vector velocity) {
		if (! isValidVelocity(velocity))	
			velocity = velocity.normalise().times(C);
		this.velocity = velocity;
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
	 * @return	The radius of this entity.
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
	 * @return  Double.POSITIVE_INFINITY if the entities never collide.
	 * 		|	if (this.getCollisionPosition(other) == null)
	 * 		|		result == Double.POSTIVE_INFINITY
	 * @return	The time the entity needs to reach the collision position.
	 * 		|	while (new.getPosition != this.getCollisionPosition(other)) {
	 * 		|		time += dt
	 * 		|		this.move(dt)
	 * 		|	}
	 * 		|	result == time
	 * @throws 	IllegalArgumentException
	 * 		   	This method is invoked with null pointer as argument.
	 * 		|	other == null
	 * @throws 	IllegalArgumentException
	 * 			This method is invoked with two overlapping entities.
	 * 		|	this.overlap(other)
	 */
	public double getTimeToCollision(Entity other) throws IllegalArgumentException {
		if ((other == null) || (this.overlap(other))) throw new IllegalArgumentException();
		
		Vector dv = other.getVelocity().subtract(this.getVelocity());
		Vector dr = other.getPosition().subtract(this.getPosition());
		
		double sigma = this.getRadius() + other.getRadius();
		double d = Math.pow(dv.dot(dr), 2) - (dv.dot(dv) * (dr.dot(dr) - Math.pow(sigma, 2))); 
		
		if (dv.dot(dr) >= 0)
			return Double.POSITIVE_INFINITY;
		else if (d <= 0)
			return Double.POSITIVE_INFINITY;
		else
			return (-(dv.dot(dr) + Math.sqrt(d))/dv.dot(dv));		
	}
	
	/**
	 * Return where, if ever, two entities will collide.
	 * Return null if the entities never collide.
	 * 
	 * @param 	other
	 * 			The other entity
	 * @return	Null if the ships never collide.
	 * 		|	if (this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY)
	 *		|		then return null
	 * @return	The position where the distance between the entities is equal
	 * 			to the sum of their radii.
	 * 		|	if (this.getDistanceBetween(other) == this.getRadius() + other.getRadius())
	 *		|		return position
	 * @throws 	IllegalArgumentException
	 * 		   	If this method is invoked with null pointer as argument.
	 * 		|	other == null
	 */
	public Vector getCollisionPosition(Entity other) throws IllegalArgumentException {
		if (other == null) throw new IllegalArgumentException();
		
		double dt = this.getTimeToCollision(other);
		if (dt == Double.POSITIVE_INFINITY)
			return null;
		
		Vector newThisPosition = this.getPosition().add(this.getVelocity().times(dt));
		Vector newOtherPosition = other.getPosition().add(other.getVelocity().times(dt));
		
		Vector difference;
		Vector collisionPosition;
		
		if (newThisPosition.compareTo(newOtherPosition) < 0) {
			difference = newOtherPosition.subtract(newThisPosition);
			collisionPosition = newThisPosition.add(difference.times(this.getRadius() / difference.getMagnitude()));
		}
		else {
			difference = newThisPosition.subtract(newOtherPosition);
			collisionPosition = newOtherPosition.add(difference.times(other.getRadius() / difference.getMagnitude()));
		}
		
		return collisionPosition;
		
	}

	public boolean isValidWorld(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return this.world;
	}
	
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
	 protected boolean isTerminated = false;
	 
	 private World world; 
	 
	 public void setWorld(World world) {
		 //TODO maak deftig
		 this.world = world;
	 }
	 
	 public double getMass() {
		 return 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
	 }
	  
	 protected abstract double getDensity();
	  
	 public abstract void terminate();
	 
}
