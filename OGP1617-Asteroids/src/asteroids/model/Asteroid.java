package asteroids.model;

/**
 * A class of asteroids, which are minor planets.
 * 
 * @invar 	Each asteroid must have a valid density.
 * 		| 	isValidDensity(this.getDensity)
 * @version 1.0
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 */
public class Asteroid extends MinorPlanet{
	
	/**
	 * Initialize this new Asteroid with given 
	 * vector of the position, given vector of the velocity and
	 * given radius.
	 * 
	 * @param position
	 * 		  The position of this new asteroid.
	 * @param velocity
	 * 	      The velocity of this new asteroid.
	 * @param radius
	 * 	      The radius of this new asteroid.
	 * @effect The position of this new asteroid is set to the given position.  
	 * 		|	this.setPosition(position)
	 * @effect  The velocity of this new asteroid is set to the given velocity.
	 * 		|   this.setVelocity(velocity)
	 * @effect  The radius of this new asteroid is set to the given radius.
	 *      |   this.setRadius(radius)
	 * @throws IllegalArgumentException
	 */
    public Asteroid(Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity, radius, 0);
	}

	/**
     * Check whether the given density is a valid density for
     * any asteroid.
     *
     * @param	density
     *         	The density to check.
     * @return
     *      |   @see implementation
     */
    @Override
    public boolean isValidDensity(double density) {
        return density == 2.65E12;
    }

    /**
     * Return the default density for any asteroid.
     *
     * @return	The default density for any asteroid.
     * 		|	result == 2.65E12
     */
    @Override
    public double getDefaultDensity() {
        return 2.65E12;
    }
}
