package asteroids.programs.statements;

public abstract class ReturnStatement extends ActionStatement {
	
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
