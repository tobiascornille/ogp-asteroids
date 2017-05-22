package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyStatement;

public abstract class ActionStatement extends MyStatement {
	
	public ActionStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public List<Object> evaluate(Program program) {
		return null;
		
	}
	

}
