package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;

public class Thrust_off extends ActionStatement {
	
	@Override
	public Object evaluate(Program program) {
		Ship ship = new Self.evaluate(program);
		ship.thrustOn();
		return null;
	}
	
}
