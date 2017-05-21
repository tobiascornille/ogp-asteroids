package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;

public abstract class ReturnStatement extends ActionStatement {
	
	public ReturnStatement(SourceLocation location) {
		super(location);
	}

	private Object value;
	
	public Object returnValue() {
		return this.getValue();
		
	}
	
	protected void setValue(Object value) {
		this.value = value;
	}
	
	protected Object getValue() {
		return this.value;
	}
	
	
	
}
