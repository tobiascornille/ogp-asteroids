package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Skip extends ActionStatement {

	public Skip(SourceLocation location) {
		super(location);
	}
	
	@Override
	public void evaluate(Program program) {
		resolveAction(program);
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
