package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyStatement;

public abstract class ActionStatement extends MyStatement {
	
	public ActionStatement(SourceLocation location) {
		super(location);
	}

	void resolveAction(Program program) throws IllegalStatementException {
		if (program.inFunction())
			throw new IllegalStatementException();
		
		if (program.getTime() < 0.2) {
			//TODO
		}
		program.setTime(program.getTime() - 0.2);
	}
	
	public abstract void evaluate(Program program);
}
