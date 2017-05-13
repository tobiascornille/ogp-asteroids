package asteroids.model;

public class Asteroid extends MinorPlanet{
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
