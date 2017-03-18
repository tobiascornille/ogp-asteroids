package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;
/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * 
 * @invar  Each world can have its width as width .
 *       | canHaveAsWidth(this.getWidth())
 *       
 * @invar  Each world can have its height as height .
 *       | canHaveAsHeight(this.getHeight())
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
	 *       | if (canHaveAsWidth(width))
	 *       |   then new.getWidth() == width
	 *       |   else new.getWidth() == Double.MAX_VALUE
	 *       
	 * @post 
	 *       | if (canHaveAsHeight(height))
	 *       |   then new.getHeight() == height
	 *       |   else new.getHeight() == Double.MAX_VALUE      
	 * 
	 */
	public World (double width, double height) {
		if (! canHaveAsWidth(width)) this.width = Double.MAX_VALUE;
		else
			this.width = width;
		if (! canHaveAsHeight(height)) this.height = Double.MAX_VALUE;
		else
			this.height = height;
	}
		
	/**
	 * Return the width of this world.
	 */
	@Basic @Raw @Immutable
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Check whether this world can have the given width as its width.
	 *  
	 * @param  width
	 *         The width to check.
	 * @return 
	 *       | result == (0 <= width && width >= Double.MAX_VALUE)
	*/
	@Raw
	public boolean canHaveAsWidth(double width) {
		return (0 <= width && width >= Double.MAX_VALUE);
	}
	
	/**
	 * Variable registering the width of this world.
	 */
	private final double width;
	
	/**
	 * Return the height of this world.
	 */
	@Basic @Raw @Immutable
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Check whether this world can have the given height as its height.
	 *  
	 * @param  height
	 *         The height to check.
	 * @return 
	 * 
	 *       | result == (0 <= height && height >= Double.MAX_VALUE)
	*/
	@Raw
	public boolean canHaveAsHeight(double height) {
		return (0 <= height && height >= Double.MAX_VALUE);
	}
	
	/**
	 * Variable registering the height of this world.
	 */
	private final double height;

	}
