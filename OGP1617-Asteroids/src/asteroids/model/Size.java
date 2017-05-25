package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of sizes, which are ordered pairs.
 * 
 * @invar	Each size can have its x component as x component.
 *       | 	canHaveAsXComponent(this.getXComponent())
 * @invar  	Each size can have its y component as y component.
 *       | 	canHaveAsYComponent(this.getYComponent())
 * 
 * @version 1.0
 * @author 	Simon Merckx and Tobias Cornille
 *
 */
@Value
public class Size extends OrderedPair implements Comparable<Size>{

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
	 */
	public Size(double xComponent, double yComponent) {
		super(xComponent, yComponent);
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
	 * Returns the area of this size.
	 * 
	 * @return	The product of the x component and the y component.
	 * 		|	result == getXComponent() * getYComponent()
	 */
	public double getArea() {
		return getXComponent() * getYComponent();
	}
	
	/**
	 * Compare this size with the other size.
	 * 
	 * @param	other
	 * 			The other size to compare with.
	 * @return	The result is equal to the difference of the magnitude
	 * 			of this size and the magnitude of the other size.
	 * 		| 	result == this.getArea() - other.getArea()
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
