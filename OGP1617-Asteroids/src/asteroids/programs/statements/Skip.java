package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Skip extends ActionStatement {

	public Skip(SourceLocation location) {
		super(location);
	}
	
	public void evaluate(Program program) {
		resolveAction(program);
	}

}
