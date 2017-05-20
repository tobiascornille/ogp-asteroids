package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;

public class Thrust_on extends ActionStatement {
	
	@Override
	public Object evaluate(Program program) {
		Ship ship = new Self.evaluate(program);
		ship.thrustOff();
		return null;
	}
}
