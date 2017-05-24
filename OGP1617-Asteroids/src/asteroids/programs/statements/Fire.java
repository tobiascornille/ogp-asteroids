package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class Fire extends ActionStatement {
	
	public Fire(SourceLocation location) {
		super(location);
	}
	
	@Override
	public void evaluate(Program program) {
		resolveAction(program);
		program.getExecutingShip().fireBullet();
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
