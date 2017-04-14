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
	 * Calculates the distance between this vector and the other vector.
	 * 
	 * @param 	other
	 * 			The other vector.
	 * @return	
	 * 		|	result == Math.sqrt( Math.pow(getXComponent() - other.getXComponent(), 2) 
	 * 		|					   + Math.pow(getYComponent() - other.getYComponent(), 2) )
	 */
	public double getDistanceBetween(Vector other) {
		return Math.sqrt( Math.pow(getXComponent() - other.getXComponent(), 2) 
				+ Math.pow(getYComponent() - other.getYComponent(), 2));
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
	 * Compute the vector difference of this vector and the other vector.
	 * 
	 * @param 	other
	 * 			The other vector to subtract.
	 * @return	The x component of the resulting vector is equal to the difference 
	 * 			of the x components of this vector and the other vector.
	 *			The y component of the resulting vector is equal to the difference
	 * 			of the y components of this vector and the other vector.
	 * 		|	result.getXComponent() ==
	 * 		|		this.getXComponent() - other.getXComponent()
	 * 		|	result.getYComponent() ==
	 * 		|		this.getYComponent() - other.getYComponent()
	 */
	public Vector subtract(Vector other){
		return new Vector(this.getXComponent() - other.getXComponent(), 
				this.getYComponent() - other.getYComponent());
	}
	
	
	/**
	 * Normalise this vector, so that the magnitude is 1.
	 * 
	 * @return	The normalised vector is equal to this vector 
	 * 			divided by the magnitude of this vector.
	 * 		|	result == this.times(1/this.getMagnitude());
	 */
	public Vector normalise() {
		return this.times(1/this.getMagnitude());
	}
	
	/**
	 * Casts this vector to an array of doubles.
	 * 
	 * @return	The first field of the resulting array is equal to
	 * 			the x component of his vector.
	 * 			The second field of the resulting array is equal to
	 * 			the y component of his vector.
	 * 		|	result[0] == this.getXComponent()
	 * 		|	result[1] == this.getYComponent()
	 */
	public double[] toDouble() throws IllegalArgumentException{
		return new double[] {this.getXComponent(), this.getYComponent()};
	}
	
	/**
	 * Compare this vector with the other vector.
	 * 
	 * @param	other
	 * 			The other vector to compare with.
	 * @return	The result is equal to the difference of the magnitude
	 * 			of this vector and the magnitude of the other vector.
	 * 		| 	result == this.getMagnitude() - other.getMagnitude()
	 */
	@Override
	public int compareTo(Vector other) {
		return (int) (this.getMagnitude() - other.getMagnitude());
	}
	
	/**
	 * Check whether this vector is equal to the given object.
	 * 
	 * @param 	other
	 * 			The other object to compare with.
	 * @return	True if and only if the given object is effective,
	 * 			if this vector and the given object belong to the 
	 * 			same class, and if this vector and the other object
	 * 			interpreted as a vector have equal x components and
	 * 			equal y components.
	 * 		|	result ==
	 * 		|		( 	(other != null)
	 * 		|			&& (this.getClass() == other.getClass())
	 * 		|			&& (this.getXComponent() == (Vector other).getXComponent())
	 * 		|			&& (this.getYComponent() == (Vector other).getYComponent())	)
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Vector otherVector = (Vector) other;
		return (this.getXComponent() == otherVector.getXComponent()) && 
				(this.getYComponent() == otherVector.getYComponent());
	}
	
	/**
	 * Return the hash code for this vector.
	 */
	@Override
	public int hashCode(){
		return (int) this.getMagnitude();
	}

	@Override
	public String toString() {
		return "Vector [xComponent=" + xComponent + ", yComponent=" + yComponent + "]";
	}
	
}
