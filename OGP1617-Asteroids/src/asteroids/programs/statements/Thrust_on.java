package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class Thrust_on extends ActionStatement {
	
	public Thrust_on(SourceLocation location) {
		super(location);
	}

	@Override
	public Object evaluate(Program program) {
		Self self = new Self();
		Ship ship = self.evaluate(program);
		ship.thrustOff();
		return null;
	}
}
