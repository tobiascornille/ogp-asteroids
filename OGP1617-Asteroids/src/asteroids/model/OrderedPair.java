package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of ordered pairs involving an x component and a y component.
 *
 * @invar	Each ordered pair can have its x component as x component.
 *       | 	canHaveAsXComponent(this.getXComponent())
 * @invar  	Each ordered pair can have its y component as y component.
 *       | 	canHaveAsYComponent(this.getYComponent())
 *
 * @version 1.0
 * @author 	Simon Merckx and Tobias Cornille
 *
 */
@Value
public abstract class OrderedPair {

    /**
     * Initialize this new ordered pair with given x component and y component.
     *
     * @param  	xComponent
     *         	The x component for this new ordered pair.
     * @param	yComponent
     * 			The y component for this new ordered pair.
     * @post   	The x component of this new ordered pair is equal to the given
     *         	x component.
     *       | 	new.getXComponent() == xComponent
     * @post   	The y component of this new ordered pair is equal to the given
     *         	y component.
     *       | 	new.getYComponent() == yComponent
     */
    public OrderedPair(double xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }

    /**
     * Return the x component of this ordered pair.
     */
    @Basic @Raw @Immutable
    public double getXComponent() {
        return this.xComponent;
    }

    /**
     * Variable registering the x component of this ordered pair.
     */
    private final double xComponent;

    /**
     * Return the y component of this ordered pair.
     */
    @Basic @Raw @Immutable
    public double getYComponent() {
        return this.yComponent;
    }

    /**
     * Variable registering the y component of this ordered pair.
     */
    private final double yComponent;

    /**
     * Casts this ordered pair to an array of doubles.
     *
     * @return	The first field of the resulting array is equal to
     * 			the x component of his ordered pair.
     * 			The second field of the resulting array is equal to
     * 			the y component of his ordered pair.
     * 		|	result[0] == this.getXComponent()
     * 		|	result[1] == this.getYComponent()
     */
    public double[] toDouble() throws IllegalArgumentException{
        return new double[] {this.getXComponent(), this.getYComponent()};
    }

    @Override
    public String toString() {
        return "Vector [xComponent=" + xComponent + ", yComponent=" + yComponent + "]";
    }

}
