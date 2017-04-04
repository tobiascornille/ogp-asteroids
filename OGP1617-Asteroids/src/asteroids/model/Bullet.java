package asteroids.model;

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
