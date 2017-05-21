package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyStatement;

public abstract class ActionStatement extends MyStatement {
	
	public ActionStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public Object evaluate(Program program) {
		return null;
		
	}
	

}
