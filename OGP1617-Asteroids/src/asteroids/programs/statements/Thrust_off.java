package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.programs.expressions.Self;

public class Thrust_off extends ActionStatement {
	
	@Override
	public Object evaluate(Program program) {
		Self self = new Self();
		Ship ship =   self.evaluate(program);
		ship.thrustOn();
		return null;
	}
	
}
