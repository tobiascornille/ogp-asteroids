package asteroids.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class MyExpression {

    public abstract Object evaluate(Program program);
    
	private SourceLocation location;
	
	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}
	
	public SourceLocation getSourceLocation() {
		return this.location;
	}
}
