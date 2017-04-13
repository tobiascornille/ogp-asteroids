package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of sizes involving an x component and a y component.
 * 
 * @invar	Each size can have its x component as x component.
 *       | 	canHaveAsXComponent(this.getXComponent())
 * @invar  	Each size can have its y component as y component.
 *       | 	canHaveAsYComponent(this.getYComponent())
 * 
 * @version 1.2
 * @author 	Simon Merckx and Tobias Cornille
 *
 */
@Value
public class Size implements Comparable<Size>{

	/**
	 * Initialize this new size with given x component and y component.
	 * 
	 * @param  	xComponent
	 *         	The x component for this new size.
	 * @param	yComponent
	 * 			The y component for this new size.
	 * @post   	The x component of this new size is equal to the given
	 *         	x component.
	 *       | 	new.getXComponent() == xComponent
	 * @post   	The y component of this new size is equal to the given
	 *         	y component.
	 *       | 	new.getYComponent() == yComponent
	 * @throws 	IllegalArgumentException
	 *         	This new size cannot have the given x component as its x component.
	 *       | 	! canHaveAsXComponent(this.getXComponent())
	 * @throws 	IllegalArgumentException
	 *         	This new size cannot have the given y component as its y component.
	 *       | 	! canHaveAsYComponent(this.getYComponent())
	 */
	public Size(double xComponent, double yComponent) throws IllegalArgumentException {
		if (! canHaveAsXComponent(xComponent) || ! canHaveAsYComponent(yComponent)) 
			throw new IllegalArgumentException();
		this.xComponent = xComponent;
		this.yComponent = yComponent;
	}
	
	/**
	 * Variable referencing the default size.
	 * 
	 * @return	The size DEFAULT_SIZE is equal to a size
	 * 			initialized with x component 1000 and with 
	 * 			y component 1000.	
	 * 		|	DEFAULT_SIZE.equals(new Size(1000, 1000))
	 */
	public final static Size DEFAULT_SIZE = new Size(1000, 1000);
	
	/**
	 * Variable referencing the maximum size.
	 * 
	 * @return	The size MAX_SIZE is equal to a size
	 * 			initialized with x component Double.MAX_VALUE and with 
	 * 			y component Double.MAX_VALUE.	
	 * 		|	MAX_SIZE.equals(new Size(Double.MAX_VALUE, Double.MAX_VALUE))
	 */
	public final static Size MAX_SIZE = new Size(Double.MAX_VALUE, Double.MAX_VALUE);
	
	/**
	 * Variable referencing the minimum size.
	 * 
	 * @return	The size MIN_SIZE is equal to a size
	 * 			initialized with x component 0 and with 
	 * 			y component 0.	
	 * 		|	MIN_SIZE.equals(new Size(0, 0))
	 */
	public final static Size MIN_SIZE = new Size(0, 0);
	
	/**
	 * Return the x component of this size.
	 */
	@Basic @Raw @Immutable
	public double getXComponent() {
		return this.xComponent;
	}
	
	/**
	 * Check whether this size can have the given x component as its x component.
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
	 * Variable registering the x component of this size.
	 */
	private final double xComponent;
	
	/**
	 * Return the y component of this size.
	 */
	@Basic @Raw @Immutable
	public double getYComponent() {
		return this.yComponent;
	}
	
	/**
	 * Check whether this size can have the given y component as its y component.
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
	 * Variable registering the y component of this size.
	 */
	private final double yComponent;
	
	/**
	 * Returns the area of this size.
	 * 
	 * @return	The product of the x component and the y component.
	 * 		|	result == getXComponent() * getYComponent()
	 */
	public double getArea() {
		return getXComponent() * getYComponent();
	}
	
	/**
	 * Casts this size to an array of doubles.
	 * 
	 * @return	The first field of the resulting array is equal to
	 * 			the x component of his size.
	 * 			The second field of the resulting array is equal to
	 * 			the y component of his size.
	 * 		|	result[0] == this.getXComponent()
	 * 		|	result[1] == this.getYComponent()
	 */
	public double[] toDouble() throws IllegalArgumentException{
		return new double[] {this.getXComponent(), this.getYComponent()};
	}
	
	/**
	 * Compare this size with the other size.
	 * 
	 * @param	other
	 * 			The other size to compare with.
	 * @return	The result is equal to the difference of the magnitude
	 * 			of this size and the magnitude of the other size.
	 * 		| 	result == this.getMagnitude() - other.getMagnitude()
	 */
	@Override
	public int compareTo(Size other) {
		return (int) (this.getArea() - other.getArea());
	}
	
	/**
	 * Check whether this size is equal to the given object.
	 * 
	 * @param 	other
	 * 			The other object to compare with.
	 * @return	True if and only if the given object is effective,
	 * 			if this size and the given object belong to the 
	 * 			same class, and if this size and the other object
	 * 			interpreted as a size have equal x components and
	 * 			equal y components.
	 * 		|	result ==
	 * 		|		( 	(other != null)
	 * 		|			&& (this.getClass() == other.getClass())
	 * 		|			&& (this.getXComponent() == (Size other).getXComponent())
	 * 		|			&& (this.getYComponent() == (Size other).getYComponent())	)
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Size otherSize = (Size) other;
		return (this.getXComponent() == otherSize.getXComponent()) && 
				(this.getYComponent() == otherSize.getYComponent());
	}
	
	/**
	 * Return the hash code for this size.
	 */
	@Override
	public int hashCode(){
		return (int) this.getArea();
	}
}
