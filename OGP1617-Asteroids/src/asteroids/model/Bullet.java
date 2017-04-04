package asteroids.model;

import java.util.Iterator;

public class Bullet extends Entity{

	public Bullet(double radius) {
		super(radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canHaveAsRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
