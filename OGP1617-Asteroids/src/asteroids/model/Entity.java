package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Iterator;
import java.util.Set;

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
 * @invar  	The world of each entity must be a valid world for this
 *         	entity.
 *      | 	isValidWorld(getWorld())
 * @version 1.0
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
	public Entity(double radius) throws IllegalArgumentException {	
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException(); 
			this.radius = radius;
		this.setDensity(this.getDefaultDensity());
		this.setMass(4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity()); 

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
	 * @param mass 
	 * @throws  IllegalArgumentException
	 * 			If the radius is invalid.
	 * 		|	! canHaveAsRadius(radius)
	 * @effect	The position of this new entity is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new entity is set to the given velocity.
	 * 		|   this.setVelocity(velocity) 
	 * @effect  The radius of this new entity is set to the given radius.
	 * 		|	this.setRadius(radius) 
	 * @effect	The mass of this new entity is set to the given mass.
	 * 		|	this.setMass(mass)
	 * @effect	The density of this new entity is set to the default value
	 * 			for this entity
	 * 		|	if (this instanceof Ship)
	 *		|		then this.setDensity(1.42 * Math.pow(10, 12)) 
	 *		|	else if (this instanceof Bullet)
	 *		|		then this.setDensity(7.8 * Math.pow(10, 12))
	 */	
	public Entity (Vector position, Vector velocity, double radius, double mass) throws IllegalArgumentException {
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException(); 
			this.radius = radius;
		this.setDensity(this.getDefaultDensity());
		this.setPosition(position);
		this.setVelocity(velocity);
		this.setMass(mass);
	}

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
	public abstract boolean canHaveAsRadius(double radius);

	/**
	 * Variable registering the radius of this entity.
	 */
	protected double radius;
	
	
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
	 * @return 	If this entity doesn't have a world it's position will always be correct.
	 *     	 | 	result == true
	 */
	public static boolean isValidPosition(Vector position) {
		return (!Double.isNaN(position.getXComponent()) && !Double.isNaN(position.getYComponent()));
	}
	
	/**
	 * Check whether this entity has a valid position in the given world.
	 * 
	 * @param 	world
	 * 			The given world.
	 * @return	True if this entity is in an unbounded space.
	 * 		|	if (world == null)
	 *		|		then result == true;
	 * @return	True if this entity lies within the bounds of the given world
	 *			and this entity doesn't overlap in the given world.	
	 *		|	if (this.liesWithinBoundsWorld(world) && (! this.checkOverlapInWorld(world)))
	 *		|		then result == true;
	 */
	public boolean hasValidPositionInWorld(World world) {
		if (world == null)
			return true;

		if (this.liesWithinBoundsWorld(world) && (! this.checkOverlapInWorld(world)))
			return true;
		
		return false;
	}

	/**
	 * Check whether this entity lies within the boundaries of the given world.
	 *  
	 * @param 	world
	 * 			The given world.
	 * @return	True if the X component of the position of this entity
	 * 			is in the range [0, width] and the Y component is in 
	 * 			the range [0, height].
			|	@see implementation
	 */
	public boolean liesWithinBoundsWorld(World world) {
		
		if(	this.getPosition().getXComponent() >= 0.99 * this.getRadius() &&
			this.getPosition().getXComponent() <= world.getSize().getXComponent() - (0.99 * this.getRadius()) &&
			this.getPosition().getYComponent() >= 0.99 * this.getRadius() &&
			this.getPosition().getYComponent() <= world.getSize().getYComponent() - (0.99 * this.getRadius()) )
				return true;
		return false;
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
	 * @throws	IllegalArgumentException
	 * 			If the time dt is less than 0.
	 * 		|	dt &lt; 0
	 */
	 void move(double dt) throws IllegalArgumentException {
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
		if (this == other) return true;
		return (this.getDistanceBetween(other)  <= -0.01 * (this.getRadius() + other.getRadius()));
	}

    /**
     * Return true if there is an entity overlapping with this entity in the given world 
     * that isn't this entity.
     *
     * @param world
     * 		  The world to check for overlapping entities.
     *
     * @return
     * 		 | @see implementation
     */
    public boolean checkOverlapInWorld(World world) {
        Set<Entity> entities = world.getEntities();
        for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
            Entity entity = i.next();
            if (this.overlap(entity) && this != entity)
                return true;
        }
        return false;
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
	protected void setPosition(Vector position) throws IllegalArgumentException {
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
		if (! isValidVelocity(velocity)) {
			if (Double.isNaN(velocity.getXComponent()))
				velocity = new Vector(C, velocity.getYComponent());
			if (Double.isNaN(velocity.getYComponent()))
				velocity = new Vector(velocity.getXComponent(), C);
			velocity = velocity.normalise().times(C);
		}
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
	 * Set the density of this entity to the given density.
	 * 
	 * @param	density
	 *         	The new density for this entity.
	 * @post   	If the given density is a valid density for this entity,
	 *         	the density of this new entity is equal to the given
	 *         	density, otherwise it's equal to the default density.
	 *      | 	if (this.isValidDensity(density))
	 *    	|  		then new.getDensity() == density
	 *      |	else
	 *      |		new.getDensity() == this.getDefaultDensity
	 */
	@Raw
	public void setDensity(double density) {
		if (this.isValidDensity(density))
			this.density = density;
		else
			this.density = this.getDefaultDensity();
	}
	
	/**
	 * Return the density of this entity.
	 */
	@Basic @Raw
	public double getDensity() {
		return this.density;
	}
		
	/**
	 * Check whether the given density is a valid density for
	 * any entity.
	 *  
	 * @param	density
	 *         	The density to check.
	 */
	public abstract boolean isValidDensity(double density);

	/**
	 * Return the default density if this entity.
	 */
	public abstract double getDefaultDensity();

	/**
	 * Variable registering the density of this entity.
	 */
	private double density = 0;

	/**
	 * Return the mass of this entity.
	 */
	@Basic @Raw @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for
	 * this entity.
	 *  
	 * @param  	mass
	 *         	The mass to check.
	 * @return
	 *      |	result == mass >= 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity()
	 *      |			&& mass < Double.MAX_VALUE)
	 */
	public boolean isValidMass(double mass) {
		return mass >= 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE;
	}
	
	/**
	 * Set the mass of this entity to the given mass.
	 * 
	 * @param  	mass
	 *         	The new mass for this entity.
	 * @post   	If the given mass is a valid mass for this entity,
	 *         	the mass of this new entity is equal to the given
	 *         	mass. Else, the mass is set to the minimum mass
	 *         	for this entity.
	 *    	| 	if (isValidMass(mass))
	 *      |   	then new.mass == mass
	 *      |	else
	 *      |		then new.mass ==  4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity()    
	 */
	@Raw
	public void setMass(double mass) {
		if (this.isValidMass(mass))
			this.mass = mass;
		else
			this.mass = 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity();
	}
	
	/**
	 * Variable registering the mass of this bullet.
	 */
	private double mass = 0;

	
	/**
	 * Return the world of this entity.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Check whether the given world is a valid world for
	 * this entity.
	 * 
	 * @return
	 * 		|	if (world == null)
	 * 		|		result == true
	 * @return 	
	 *      | 	result == (this.getWorld() == null) && (this.inWorld(world))
	 */
	public boolean isValidWorld(World world) {
		if (world == null)
			return true;		
		return (this.getWorld() == null) && (this.hasValidPositionInWorld(world));
	}
	
	/**
	 * Set the world of this entity to the given world.
	 * 
	 * @param  world
	 *         The new world for this entity.
	 * @post   The world of this new entity is equal to
	 *         the given world.
	 *       | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *         The given world is not a valid world for any
	 *         entity.
	 *       | ! isValidWorld(getWorld())
	 */
	@Raw
	protected void setWorld(World world) throws IllegalArgumentException {
		if (! isValidWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}
	
	/**
	 * Variable registering the world of this entity.
	 */
	private World world = null;
	
	 /**
	  * Return a boolean indicating whether or not this entity
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this entity is terminated.
	  */
	 protected boolean isTerminated = false;

	 public abstract void terminate();
	
	/**
	 * Return when, if ever, two entities will collide.
	 * Return Double.POSITIVE_INFINITY if the entities never collide.
	 * This method does not apply to entities that overlap. 
	 * 
	 * @param 	other
	 * 		  	The other entity.
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

	/**
	 * Return when, if ever, this entity collides with a boundary.
	 * Return Double.POSITIVE_INFINITY if this entity never collides with a boundary.
	 *
	 * @return	The time this entity needs to reach the collision position.
	 * 		|	while (new.getPosition != this.getCollisionBoundaryPosition()) {
	 * 		|		time += dt
	 * 		|		this.move(dt)
	 * 		|	}
	 * 		|	result == time
	 */
	public double getTimeToCollisionBoundary() {
		if ((this.getWorld() == null) || (this.getVelocity().equals(Vector.NULL_VECTOR)))
			return Double.POSITIVE_INFINITY;
		
		double xi = this.getPosition().getXComponent();
		double yi = this.getPosition().getYComponent();
		
		double vxi = this.getVelocity().getXComponent();
		double vyi = this.getVelocity().getYComponent();
		
		double sigma = this.getRadius();
		
		double xj = this.getWorld().getSize().getXComponent();
		double yj = this.getWorld().getSize().getYComponent();
		
		// Get time to collision with vertical (endless) boundary
		double dt1;
		if (vxi > 0)
			dt1 = (-sigma + xj - xi) / vxi;
		else if (vxi < 0)
			dt1 = (-sigma + xi) / -vxi;
		else 
			dt1 = Double.POSITIVE_INFINITY;
		
		// Get time to collision with the horizontal (endless) boundary
		double dt2 = 0;
		if (vyi > 0)
			dt2 = (-sigma + yj - yi) / vyi;
		else if (vyi < 0)
			dt2 = (-sigma + yi) / -vyi;
		else
			dt2 = Double.POSITIVE_INFINITY;
		
		return Math.min(dt1, dt2);
	}

	/**
	 * Return where, if ever, this entity collides with a boundary.
	 * Return null if the entity never collides with a boundary.
	 *
	 * @return	Null if the ship never collides with a boundary.
	 * 		|	if (this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY)
	 *		|		then return null
	 * @return	The position where the distance between this entity and a boundary
	 * 			is equal the radius of this entity.
	 * 		|	if (this.getDistanceBetween(boundary) == this.getRadius())
	 *		|		return position
	 */
	public Vector getCollisionBoundaryPosition() {
		if ((this.getWorld() == null) || (this.getVelocity().equals(Vector.NULL_VECTOR)))
			return null;
		
		double xi = this.getPosition().getXComponent();
		double yi = this.getPosition().getYComponent();
		
		double vxi = this.getVelocity().getXComponent();
		double vyi = this.getVelocity().getYComponent();
		
		double sigma = this.getRadius();
		
		double xj = this.getWorld().getSize().getXComponent();
		double yj = this.getWorld().getSize().getYComponent();
		
		// Get time to collision with vertical (endless) boundary
		double dt1;
		if (vxi > 0)
			dt1 = (-sigma + xj - xi) / vxi;
		else if (vxi < 0)
			dt1 = (-sigma + xi) / -vxi;
		else 
			dt1 = Double.POSITIVE_INFINITY;
		
		// Get time to collision with the horizontal (endless) boundary
		double dt2 = 0;
		if (vyi > 0)
			dt2 = (-sigma + yj - yi) / vyi;
		else if (vyi < 0)
			dt2 = (-sigma + yi) / -vyi;
		else
			dt2 = Double.POSITIVE_INFINITY;
		
		Vector newPosition;
		
		if (dt1 < dt2) {
			newPosition = this.getPosition().add(this.getVelocity().times(dt1));
			if (vxi > 0)
				return newPosition.add(new Vector(sigma, 0));
			else
				return newPosition.add(new Vector(-sigma, 0));
		}
		else {
			newPosition = this.getPosition().add(this.getVelocity().times(dt2));
			if (vyi > 0)
				return newPosition.add(new Vector(0, sigma));
			else
				return newPosition.add(new Vector(0, -sigma));
		}
	}

	abstract void objectCollision(Entity entity);
	
	void bounceOff(Entity entity) {
		Vector newVelocity;
		Vector newVelocityEntity;

		Vector dv = entity.getVelocity().subtract(this.getVelocity());
		Vector dr = entity.getPosition().subtract(this.getPosition());

		double sigma = this.getRadius() + entity.getRadius();

		double mass = 0;
		double massEntity = 0;
		if (this instanceof Ship && entity instanceof Ship) {
			mass = ((Ship) this).getTotalMass();
			massEntity = ((Ship) entity).getTotalMass();
		}
		else if (this instanceof MinorPlanet && entity instanceof MinorPlanet) {
			mass = this.getMass();
			massEntity = entity.getMass();
		}

		double xi = this.getPosition().getXComponent();
		double yi = this.getPosition().getYComponent();

		double xj = entity.getPosition().getXComponent();
		double yj = entity.getPosition().getYComponent();

		double vxi = this.getVelocity().getXComponent();
		double vyi = this.getVelocity().getYComponent();

		double vxj = entity.getVelocity().getXComponent();
		double vyj = entity.getVelocity().getYComponent();

		double J = (2 * mass * massEntity  * (dv.dot(dr))) / (sigma * (mass + massEntity));

		double JX = (J * (xj - xi)) / sigma;
		double JY = (J * (yj - yi)) / sigma;

		newVelocity = new Vector (vxi + (JX / mass), vyi + (JY / mass));
		newVelocityEntity = new Vector (vxj - (JX / massEntity), vyj - (JY / massEntity));

		this.setVelocity(newVelocity);
		entity.setVelocity(newVelocityEntity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Entity)) return false;

		Entity entity = (Entity) o;

		if (Double.compare(entity.getRadius(), getRadius()) != 0) return false;
		if (Double.compare(entity.getDensity(), getDensity()) != 0) return false;
		if (Double.compare(entity.getMass(), getMass()) != 0) return false;
		if (isTerminated() != entity.isTerminated()) return false;
		if (!getPosition().equals(entity.getPosition())) return false;
		if (!getVelocity().equals(entity.getVelocity())) return false;
		return getWorld() != null ? getWorld().equals(entity.getWorld()) : entity.getWorld() == null;
	}
}

