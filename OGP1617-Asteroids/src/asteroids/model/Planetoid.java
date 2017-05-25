package asteroids.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
* @invar The totalDistanceTraveled of each planetoid must be a valid totalDistanceTraveled for any
*        planetoid.
*       | isValidTotalDistanceTraveled(getTotalDistanceTraveled())
* @invar Each asteroid must have a valid density.
* 		| isValidDensity(this.getDensity)
* @version 1.0
* @author Simon Merckx and Tobias Cornille.
*         We both study informatics (1ba).
*         Private repo on https://github.com/tobiascornille/Asteroids
*         Please send us an email with your account info so we can add you as a contributer.
*/
public class Planetoid extends MinorPlanet {
	
	/**
	 * Initialize this new planetoid with given 
	 * vector of the position, given vector of the velocity ,
	 * given radius and given total distance traveled.
	 * 
	 * @param position
	 * 		  The position of this new planetoid.
	 * @param velocity
	 * 		  The velocity of this new planetoid.
	 * @param radius
	 * 		  The radius of this new planetoid.
	 * @param totalDistanceTraveled
	 * 		  The total distance traveled of this new planetoid.
	 * @effect	The position of this new planetoid is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new planetoid is set to the given velocity.
	 * 		|   this.setVelocity(velocity)
	 * @effect  The radius of this new planetoid is set to the given radius.
	 *      |   this.setRadius(radius)
	 * @effect The total distance traveled of this new plantoid is set to the given totalDistanceTraveled.
	 * 		| 	this.setTotalDistanceTraveled(totalDistanceTraveled)  
	 * @throws IllegalArgumentException
	 */
    public Planetoid(Vector position, Vector velocity, double radius, double totalDistanceTraveled) throws IllegalArgumentException {
		super(position, velocity, radius, 0);
		this.setTotalDistanceTraveled(totalDistanceTraveled);
		this.setRadius(this.getRadius() - 0.000001 * this.getTotalDistanceTraveled());
	}

	/**
     * Check whether the given density is a valid density for
     * any planetoid.
     *
     * @param	density
     *         	The density to check.
     * @return
     *      |   @see implementation
     */
    @Override
    public boolean isValidDensity(double density) {
        return density == 0.917E12;
    }

    /**
     * Return the default density for any planetoid.
     *
     * @return	The default density for any planetoid.
     * 		|	result == 0.917E12
     */
    @Override
    public double getDefaultDensity() {
        return 0.917E12;
    }

	/**
	 * Return the totalDistanceTraveled of this planetoid.
	 */
	@Basic @Raw
	public double getTotalDistanceTraveled() {
		return this.totalDistanceTraveled;
	}
	
	/**
	 * Check whether the given totalDistanceTraveled is a valid totalDistanceTraveled for
	 * any planetoid.
	 *  
	 * @param  totalDistanceTraveled
	 *         The totalDistanceTraveled to check.
	 * @return 
	 *       | result == (this.getRadius() - 0.000001 * this.getTotalDistanceTraveled()) > 5
	*/
	public boolean isValidTotalDistanceTraveled(double totalDistanceTraveled) {
		return ((this.getRadius() - (0.000001 * this.getTotalDistanceTraveled())) > 5);
	}
	
	/**
	 * Set the totalDistanceTraveled of this planetoid to the given totalDistanceTraveled.
	 * 
	 * @param  totalDistanceTraveled
	 *         The new totalDistanceTraveled for this planetoid.
	 * @pre    The given totalDistanceTraveled must be a valid totalDistanceTraveled for any
	 *         planetoid.
	 *       | isValidTotalDistanceTraveled(totalDistanceTraveled)
	 * @post   The totalDistanceTraveled of this planetoid is equal to the given
	 *         totalDistanceTraveled.
	 *       | new.getTotalDistanceTraveled() == totalDistanceTraveled
	 */
	@Raw
	public void setTotalDistanceTraveled(double totalDistanceTraveled) throws IllegalArgumentException  {
		if (!isValidTotalDistanceTraveled(totalDistanceTraveled))
				this.terminate();
		else
			this.totalDistanceTraveled = totalDistanceTraveled;
	}
	
	/**
	 * Variable registering the totalDistanceTraveled of this planetoid.
	 */
	private double totalDistanceTraveled = 0;
	
	/**
	 * 
	 */
	@Override
	public void terminate() {
		if (!isTerminated()) {
			World world = this.getWorld();
			if (world != null) {
				world.removeEntity(this);
				if (this.getRadius() >= 30)
					this.spawnAsteroids(world);
			}
			this.isTerminated = true;
		}
	}
	
	/**
	 * 
	 * @param world
	 */
	private void spawnAsteroids(World world) {
		Random randomNumber = new Random();
		double velocityDirection = randomNumber.nextDouble() * 2 * Math.PI;
		double speed = this.getVelocity().getMagnitude();
		
		Vector velocity = new Vector(Math.cos(velocityDirection), Math.sin(velocityDirection)).times(speed);
		Vector otherVelocity = velocity.times(-1);
		Vector position = this.getPosition().subtract(new Vector(this.getRadius() / 2, 0));
		Vector otherPosition = this.getPosition().add(new Vector(this.getRadius() / 2, 0));
		double radius = this.getRadius() / 2;
		
		Asteroid asteroid = new Asteroid(position, velocity, radius);
		Asteroid otherAsteroid = new Asteroid(otherPosition, otherVelocity, radius);
		
		world.addEntity(asteroid);
		world.addEntity(otherAsteroid);
	}
	
	/**
	 *  Set the radius of this planetoid to the given radius.
	 *  
	 * @param radius
	 *  	  The new radius for this planetoid.
	 * @post  The radius of this new planetoid is equal to
	 *        the given radius.
	 *      | 	new.getRadius() == radius
	 */
	private void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * Change the position of the planetoid based on the current position, velocity and time duration dt.
	 * Change the radius of the planetoid based on the current radius and the total distance traveled.
	 *
	 * @param 	dt
	 *		  	The given time duration.
	 * @post	The new position of the planetoid is the old position 
	 * 			of the planetoid plus the displacement of the planetoid.
	 *		|	new.getPosition().equals 
	 *		|		this.getPosition().add(this.getVelocity().times(dt))   
	 * @post 	The new radius of the planetoid is the old radius of the 
	 * 		 	planetoid minus 0.000001 * the total distance traveled
	 * 		 	of the planetoid.
	 * 		| new.getRadius == 
	 * 		|     this.getRadius - (0.000001 * this.getTotalDistanceTraveled())
	 * @throws	IllegalArgumentException
	 * 			If the time dt is less than 0.
	 * 		|	dt &lt; 0
	 */
	@Override
	 void move(double dt) throws IllegalArgumentException {
		
		if (dt < 0) throw new IllegalArgumentException();
		Vector newPosition = this.getPosition().add(this.getVelocity().times(dt)); 
		double distance = newPosition.getDistanceBetween(this.getPosition());
		this.setTotalDistanceTraveled(this.getTotalDistanceTraveled() + distance);
		double newRadius = this.getRadius() - (0.000001 * this.getTotalDistanceTraveled());
		if (!this.isTerminated) {
			this.setRadius(newRadius);
			this.setPosition(newPosition); 
		}	     			
	}
}
