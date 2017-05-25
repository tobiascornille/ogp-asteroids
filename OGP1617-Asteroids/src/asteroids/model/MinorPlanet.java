package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of minor planets involving a position, velocity, radius.
 * 
 * @invar Each minorplanet must have a valid mass.
 * 		 | this.isValidMass(this.getMass())
 * @version 1.0
 * @author Simon Merckx and Tobias Cornille.
 *         We both study informatics (1ba).
 *         Private repo on https://github.com/tobiascornille/Asteroids
 *         Please send us an email with your account info so we can add you as a contributer.
 */
public abstract class MinorPlanet extends Entity {
	/**
	 * 
	 * Initialize this new minorplanet with given 
	 * vector of the position, given vector of the velocity ,
	 * given radius and given mass.
	 * 
	 * @param position
	 * 		  The position of this new minorplanet.
	 * @param velocity
	 * 		  The velocity of this new minorplanet
	 * @param radius
	 * 		  The radius of this new minorplanet.
	 * @param mass
	 * 		  The mass of this new minorplanet.
	 * @effect	The position of this new minorplanet is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new minorplanet is set to the given velocity.
	 * 		|   this.setVelocity(velocity)
	 * @effect  The radius of this new minorplanet is set to the given radius.
	 *      |   this.setRadius(radius)
	 * @effect The mass this new plantoid is set to the given mass.
	 * 		| 	this.setMass(mass)  
	 * @throws IllegalArgumentException
	 */
    public MinorPlanet(Vector position, Vector velocity, double radius, double mass) throws IllegalArgumentException {
		super(position, velocity, radius, mass);
	}

	/**
     * Check whether any minor planet can have the given radius as its radius.
     *
     * @param  	radius
     *         	The radius to check.
     * @return	True if the radius is at least 5.
     *     	| 	result == (radius >= 5)
     */
    @Override
    public boolean canHaveAsRadius(double radius) {
        return radius >= 5;
    }

    /**
     * Check whether the given mass is a valid mass for
     * this minor planet.
     *
     * @param  	mass
     *         	The mass to check.
     * @return
     *      |	result == (mass == 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity()
     *      |               && mass < Double.MAX_VALUE)
     */
    @Override
    public boolean isValidMass(double mass) {
        return mass == 4.0/3.0 * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity() && mass < Double.MAX_VALUE;
    }
    
    /**
     * Terminates this minorplanet.
     * 
     * @post 
     * 	  |@see implementation
     */
    public void terminate() {
		if (!isTerminated()) {
			if (this.getWorld() != null)
				this.getWorld().removeEntity(this);
			
			this.isTerminated = true;
		}
    }
    
    /**
     * Resolve the collision of a minorplanet with another entity.
     * 
     * @param entity
     * 		  The entity that will collide with this minorplanet.
     * @post
     * 	  |@see implementation
     */
    void objectCollision(Entity entity) {
		if (entity instanceof Bullet) {
			entity.objectCollision(this);
		}
		else if (entity instanceof Ship){
			entity.objectCollision(this);
		}
		else if (entity instanceof MinorPlanet) {
			this.bounceOff(entity);
		}
	}
} 