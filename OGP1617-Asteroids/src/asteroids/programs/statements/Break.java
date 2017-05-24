package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyStatement;

public class Break extends BasicStatement {
	
	public Break(SourceLocation location) {
		super(location);
	}
	
	public void evaluate(Program program) throws IllegalBreakException,BreakException {
		if (!program.inWhile())
			throw new IllegalBreakException();
		else
			throw new BreakException();			
	}
}
