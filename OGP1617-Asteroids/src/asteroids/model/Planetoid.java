package asteroids.model;

public class Planetoid extends MinorPlanet {
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
}
