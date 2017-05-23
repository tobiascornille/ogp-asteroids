package asteroids.programs;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.statements.BreakException;
import asteroids.programs.statements.IllegalBreakException;

public abstract class MyStatement {
		
	public MyStatement(SourceLocation location) {
		this.setSourceLocation(location);
	}

	public abstract void evaluate(Program program);
	
	private SourceLocation location;
	
	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}
	
	public SourceLocation getSourceLocation() {
		return this.location;
	}
}
