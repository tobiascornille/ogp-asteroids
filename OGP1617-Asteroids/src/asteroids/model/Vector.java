package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of vectors involving an x component and a y component.
 * 
 * @invar	Each vector can have its x component as x component.
 *       | 	canHaveAsXComponent(this.getXComponent())
 * @invar  	Each vector can have its y component as y component.
 *       | 	canHaveAsYComponent(this.getYComponent())
 * 
 * @version 1.2
 * @author 	Simon Merckx and Tobias Cornille
 *
 */
@Value
public class Vector implements Comparable<Vector>{

	/**
	 * Initialize this new vector with given x component and y component.
	 * 
	 * @param  	xComponent
	 *         	The x component for this new vector.
	 * @param	yComponent
	 * 			The y component for this new vector.
	 * @post   	The x component of this new vector is equal to the given
	 *         	x component.
	 *       | 	new.getXComponent() == xComponent
	 * @post   	The y component of this new vector is equal to the given
	 *         	y component.
	 *       | 	new.getYComponent() == yComponent
	 * @throws 	IllegalArgumentException
	 *         	This new vector cannot have the given x component as its x component.
	 *       | 	! canHaveAsXComponent(this.getXComponent())
	 * @throws 	IllegalArgumentException
	 *         	This new vector cannot have the given y component as its y component.
	 *       | 	! canHaveAsYComponent(this.getYComponent())
	 */
	public Vector(double xComponent, double yComponent) throws IllegalArgumentException {
		if (! canHaveAsXComponent(xComponent) || ! canHaveAsYComponent(yComponent)) 
			throw new IllegalArgumentException();
		this.xComponent = xComponent;
		this.yComponent = yComponent;
	}
	
	/**
	 * Variable referencing the null vector.
	 * 
	 * @return	The vector NULL_VECTOR is equal to a vector
	 * 			initialized with x component 0 and with 
	 * 			y component 0.	
	 * 		|	NULL_VECTOR.equals(new Vector(0,0))
	 */
	public final static Vector NULL_VECTOR = new Vector(0, 0);
	
	/**
	 * Return the x component of this vector.
	 */
	@Basic @Raw @Immutable
	public double getXComponent() {
		return this.xComponent;
	}
	
	/**
	 * Check whether this vector can have the given x component as its x component.
	 *  
	 * @param	xComponent
	 *         	The x component to check.
	 * @return 	True if and only if the given x component is not a NaN.
	 *       | 	result == ! Double.isNaN(xComponent)
	*/
	@Raw
	public boolean canHaveAsXComponent(double xComponent) {
		return ! Double.isNaN(xComponent);
	}
	
	/**
	 * Variable registering the x component of this vector.
	 */
	private final double xComponent;
	
	/**
	 * Return the y component of this vector.
	 */
	@Basic @Raw @Immutable
	public double getYComponent() {
		return this.yComponent;
	}
	
	/**
	 * Check whether this vector can have the given y component as its y component.
	 *  
	 * @param  	yComponent
	 *         	The y component to check.
	 * @return 	True if and only if the given y component is not a NaN.
	 *       | 	result == ! Double.isNaN(yComponent)
	*/
	@Raw
	public boolean canHaveAsYComponent(double yComponent) {
		return ! Double.isNaN(yComponent);
	}
	
	/**
	 * Variable registering the y component of this vector.
	 */
	private final double yComponent;
	
	/**
	 * Returns the magnitude of this vector.
	 * 
	 * @return	The square root of the sum of the squares of the x component 
	 * 			and the y component of this vector.
	 * 		|	result == Math.sqrt(Math.pow(getXComponent(), 2) + Math.pow(getYComponent(), 2))
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.getXComponent(), 2) + Math.pow(this.getYComponent(), 2));
	}
	
	/**
	 * Compute the dot product of this vector and the other vector.
	 * 
	 * @param 	other
	 * 			The other vector.
	 * @return	The sum of the products of the x components and the 
	 * 			y components of this vector and the other vector.
	 * 		|	result == (this.getXComponent() * other.getXComponent()) 
	 * 		|	+ (this.getYComponent() * other.getYComponent())
	 */
	public double dot(Vector other) {
		return (this.getXComponent() * other.getXComponent()) + (this.getYComponent() * other.getYComponent());
	}
	
	/**
	 * Compute the product of this vector with the given factor.
	 * 
	 * @param 	factor
	 * 			The factor to multiply with.
	 * @return	The x component of the resulting vector is equal to the product 
	 * 			of the x component of this vector and the factor.
	 * 			The y component of the resulting vector is equal to the product 
	 * 			of the y component of this vector and the factor.
	 * 		|	result.getXComponent() == 
	 * 		|		this.getXComponent() * factor
	 * 		|	result.getYComponent() == 
	 * 		|		this.getYComponent() * factor
	 * @throws	IllegalArgumentException
	 * 			The factor is NaN.
	 * 		|	Double.isNaN(factor)
	 */
	public Vector times(double factor) throws IllegalArgumentException {
		if (Double.isNaN(factor)) 
			throw new IllegalArgumentException();
		return new Vector(this.getXComponent() * factor, this.getYComponent() * factor);
	}
	
	/**
	 * Compute the vector sum of this vector and the other vector.
	 * 
	 * @param 	other
	 * 			The other vector to add.
	 * @return	The x component of the resulting vector is equal to the sum 
	 * 			of the x components of this vector and the other vector.
	 *			The y component of the resulting vector is equal to the sum 
	 * 			of the y components of this vector and the other vector.
	 * 		|	result.getXComponent() ==
	 * 		|		this.getXComponent() + other.getXComponent()
	 * 		|	result.getYComponent() ==
	 * 		|		this.getYComponent() + other.getYComponent()
	 */
	public Vector add(Vector other){
		return new Vector(this.getXComponent() + other.getXComponent(), 
				this.getYComponent() + other.getYComponent());
	}
	
	/**
	 * Compare this vector with the other vector.
	 * 
	 * @param	other
	 * 			The other vector to compare with.
	 */
	@Override
	public int compareTo(Vector other) {
		return (int) (this.getMagnitude() - other.getMagnitude());
	}

}
