package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of minor planets involving a position, velocity, radius.
 */
public class MinorPlanet extends Entity {
    public MinorPlanet(double radius) throws IllegalArgumentException {
        super(radius);
    }

    @Override
    public boolean checkOverlapInWorld(World world) {
        return false;
    }

    @Override
    public void terminate() {

    }


    //TODO: implement this shit
} 