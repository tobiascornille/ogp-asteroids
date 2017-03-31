package asteroids.model;

public class Bullet extends Entity{

	public Bullet(double radius) {
		super(radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean canHaveAsRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

}
