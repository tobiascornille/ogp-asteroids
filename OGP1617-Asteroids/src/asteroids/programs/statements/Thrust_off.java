package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class Thrust_off extends ActionStatement {
	
	public Thrust_off(SourceLocation location) {
		super(location);
	}

	@Override
	public Object evaluate(Program program) {
		Self self = new Self();
		Ship ship =   self.evaluate(program);
		ship.thrustOn();
		return null;
	}
	
}
