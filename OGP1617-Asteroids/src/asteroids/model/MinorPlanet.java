package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of minor planets involving a position, velocity, radius.
 */
public abstract class MinorPlanet extends Entity {
	/**
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 * @param mass
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
    
    
  
} 