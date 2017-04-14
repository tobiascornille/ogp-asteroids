package asteroids.model;

public class CollisionPoint extends Entity{

	public CollisionPoint(Vector position) {
		super(position, Vector.NULL_VECTOR, 0);
	}

	@Override
	protected boolean checkOverlapInWorld(World world) {
		return false;
	}

	@Override
	protected boolean canHaveAsRadius(double radius) {
		return true;
	}

	@Override
	public void terminate() {
		
	}

	@Override
	protected double getMass() {
		return 0;
	}

}
