package asteroids.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class MyStatement {
	
	public MyStatement(SourceLocation location) {
		this.setSourceLocation(location);
	}

	protected Object evaluate(Program program) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	private SourceLocation location;
	
	protected void setSourceLocation(SourceLocation location) {
		this.location = location;
	}
	
	protected SourceLocation getSourceLocation() {
		return this.location;
	}

}
