package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;
/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  Each world can have its size as size .
 *       | canHaveAsSize(this.getSize()[0], this.getSize()[1])
 * 
 */
public class World {
	/**
	 * Initialize this new world with given width and height.
	 * 
	 * @param width
	 * 		  The width for this new world.
	 * 
	 * @param height
	 * 		  The height of this new world.
	 * 			
	 * @post  
	 *       | if (canHaveAsSize(width, height))
	 *       |   then new.getSize() == new double[] {width, height}
	 *       |   else new.getWidth() == new double[] {Double.MAX_VALUE, Double.MAX_VALUE}  
	 * 
	 */
	public World (double width, double height) {
		if (canHaveAsSize(width, height)) 
			this.size = new double[] {width, height};
		else
			this.size = new double[] {Double.MAX_VALUE, Double.MAX_VALUE};
	}

	/**
	 * Return the size of this world.
	 */
	@Basic @Raw @Immutable
	public double[] getSize() {
		return this.size;
	}
	
	/**
	 * Check whether this world can have the given size as its size.
	 *  
	 * @param  width
	 *         The width of the size to check.
	 * @param  height
	 *         The height of the size to check.
	 * @return 
	 *       | @see implementation
	 */
	@Raw
	private boolean canHaveAsSize(double width, double height) {
		return ((0 <= width && width <= Double.MAX_VALUE) && (0 <= height && height <= Double.MAX_VALUE));
	}
	
	/**
	 * Variable registering the size of this world.
	 */
	private final double[] size;

	
	}
