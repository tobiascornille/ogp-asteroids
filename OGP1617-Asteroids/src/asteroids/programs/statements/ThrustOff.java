package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class ThrustOff extends ActionStatement {
	
	public ThrustOff(SourceLocation location) {
		super(location);
	}

	@Override
	public void evaluate(Program program) {
		resolveAction(program);
		program.getExecutingShip().thrustOff();
	}
	
	@Override
	public Boolean goToGoalLocation(Program program) {
		if (! this.getSourceLocation().equals(program.getGoalLocation()))
			return false;
		else
			this.evaluate(program);
		return true;
	}	
}
