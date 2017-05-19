package asteroids.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
* @invar  The totalDistanceTraveled of each planetoid must be a valid totalDistanceTraveled for any
*         planetoid.
*       | isValidTotalDistanceTraveled(getTotalDistanceTraveled())
*/
public class Planetoid extends MinorPlanet {
	
	/**
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 * @param mass
	 * @throws IllegalArgumentException
	 */
    public Planetoid(Vector position, Vector velocity, double radius, double totalDistanceTraveled) throws IllegalArgumentException {
		super(position, velocity, radius, 0);
		this.setTotalDistanceTraveled(totalDistanceTraveled);
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
	 *       | result == 
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
		//TODO throw exception
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
	 * 
	 */
	private void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * 
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
