package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class ThrustOn extends ActionStatement {
	
	public ThrustOn(SourceLocation location) {
		super(location);
	}

	@Override
	public void evaluate(Program program) {
		Self self = new Self();
		Ship ship = self.evaluate(program);
		ship.thrustOn();
	}
}
