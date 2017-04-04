package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of thrusters involving a force and a state.
 * 
 * @invar	Each thruster can have its force as force.
 *       | 	canHaveAsForce(this.getForce())
 * @invar  	Each thruster can have its state as state.
 *       | 	canHaveAsState(this.getState())
 * 
 * @version 1.2
 * @author 	Simon Merckx and Tobias Cornille
 *
 */
@Value
public class Thruster implements Comparable<Thruster>{

	/**
	 * Initialize this new thruster with given force and state.
	 * 
	 * @param  	force
	 *         	The force of this new thruster.
	 * @param	state
	 * 			The state of this new thruster.
	 * @post   	The force of this new thruster is equal to the given
	 *         	force.
	 *       | 	new.getForce() == force
	 * @post   	The state of this new thruster is equal to the given
	 *         	state.
	 *       | 	new.getState() == state
	 * @throws 	IllegalArgumentException
	 *         	This new thruster cannot have the given force as its force.
	 *       | 	! canHaveAsForce(this.getForce())
	 */
	public Thruster(double force, boolean state) throws IllegalArgumentException {
		if (! canHaveAsForce(force))
			throw new IllegalArgumentException();
		this.force = force;
		this.state = state;
	}
	
	/**
	 * Variable referencing the default thruster.
	 * 
	 * @return	The thruster DEFAULT is equal to a thruster
	 * 			initialized with force 1.1 * Math.pow(10, 21)
	 * 			and false enabled state.
	 * 		|	DEFAULT.equals(new Thruster(1.1 * Math.pow(10, 21), false))
	 */
	public final static Thruster DEFAULT = new Thruster(1.1 * Math.pow(10, 21), false);
	
	/**
	 * Return the force of this thruster.
	 */
	@Basic @Raw @Immutable
	public double getForce() {
		return this.force;
	}
	
	/**
	 * Check whether this thruster can have the given force as its force.
	 *  
	 * @param	force
	 *         	The force to check.
	 * @return 	True if and only if the given force is not a NaN.
	 *       | 	result == ! Double.isNaN(force)
	*/
	@Raw
	public boolean canHaveAsForce(double force) {
		return ! Double.isNaN(force);
	}
	
	/**
	 * Variable registering the force of this thruster.
	 */
	private final double force;
	
	/**
	 * Return the state of this thruster.
	 */
	@Basic @Raw @Immutable
	public boolean getState() {
		return this.state;
	}
	
	/**
	 * Variable registering the state of this thruster.
	 */
	private final boolean state;
	
	/**
	 * Compare this thruster with the other thruster.
	 * 
	 * @param	other
	 * 			The other thruster to compare with.
	 * @return	The result is equal to the difference of the force
	 * 			of this thruster and the force of the other thruster.
	 * 		|	result == this.getForce() - other.getForce()
	 * @throws	ClassCastException
	 * 			The other thruster is not effective.
	 * 		|	other == null
	 * @throws	This thruster and the other thruster have different states.
	 *		|	this.getState() != other.getState()
	 */
	@Override
	public int compareTo(Thruster other) throws ClassCastException{
		if (other == null)
			throw new ClassCastException();
		if (this.getState() != other.getState())
			throw new ClassCastException();
		return (int) (this.getForce() - other.getForce());
	}
	
	/**
	 * Check whether this thruster is equal to the given object.
	 * 
	 * @param 	other
	 * 			The other object to compare with.
	 * @return	True if and only if the given object is effective,
	 * 			if this thruster and the given object belong to the 
	 * 			same class, and if this thruster and the other object
	 * 			interpreted as a thruster have equal forces and
	 * 			equal states.
	 * 		|	result ==
	 * 		|		( 	(other != null)
	 * 		|			&& (this.getClass() == other.getClass())
	 * 		|			&& (this.getForce() == (Thruster other).getForce())
	 * 		|			&& (this.getState() == (Thruster other).getState())	)
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Thruster otherThruster = (Thruster) other;
		return (this.getForce() == otherThruster.getForce()) && 
				(this.getState() == otherThruster.getState());
	}
	
	/**
	 * Return the hash code for this thruster.
	 */
	@Override
	public int hashCode(){
		return (int) this.getForce();
	}
}
