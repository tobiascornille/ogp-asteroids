package asteroids.programs;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class MyStatement {
		
	public MyStatement(SourceLocation location) {
		this.setSourceLocation(location);
	}

	public Object evaluate(Program program) {
		return null;		
	}
	
	private SourceLocation location;
	
	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}
	
	public SourceLocation getSourceLocation() {
		return this.location;
	}
	
	
	

}
