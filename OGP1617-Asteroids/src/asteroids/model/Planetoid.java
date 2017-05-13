package asteroids.model;

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

    @Override
  	public void terminate() {
  		// TODO Auto-generated method stub
  		
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
	public static boolean isValidTotalDistanceTraveled(double totalDistanceTraveled) {
		return (totalDistanceTraveled >= 0);
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
	public void setTotalDistanceTraveled(double totalDistanceTraveled) {
		assert isValidTotalDistanceTraveled(totalDistanceTraveled);
		this.totalDistanceTraveled = totalDistanceTraveled;
	}
	
	/**
	 * Variable registering the totalDistanceTraveled of this planetoid.
	 */
	private double totalDistanceTraveled = 0;
	
}
