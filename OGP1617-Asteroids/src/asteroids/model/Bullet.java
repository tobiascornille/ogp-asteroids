package asteroids.model;

import java.util.Iterator;

public class Bullet extends Entity{

	
	/**
	 * 
	 */
	public Bullet() {
		super(2);
	}
	
	/**
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 * @throws IllegalArgumentException
	 */
	public Bullet (Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity, radius);	
	}

	@Override
	public boolean canHaveAsRadius(double radius) {
		return (! Double.isNaN(radius)) && (radius > 1);
	}
	
	public double getDensity() {
		return this.density;
	}
	
	private final double density = 7.8 * Math.pow(10, 12);
	 
	private final double mass = this.getMass();
	
	
	@Override
	public void terminate() {
		if (!isTerminated()) {  
			// Remove the bullet from its ship
			 this.setShip(null); 
			 if (this.getShip().hasAsBullet(this))
				 this.getShip().removeBullet(this);
			 // Remove the bullet from its world
			 this.setWorld(null); 
			 this.getWorld().removeEntity(this);
			 
			 this.isTerminated = true;
		}
	}

	private void setShip(Object object) {
		// TODO Auto-generated method stub
		
	}

	public static boolean isValidShip(Ship ship) {
		// TODO Auto-generated method stub
		return false;
	}

	public Ship getShip() {
		
		return null;
	}

}
