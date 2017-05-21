package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;

public class Return extends ReturnStatement {
	
	private MyExpression expression;
	
	public Return (MyExpression expression, SourceLocation location) {
		super(location);
		this.setExpression (expression);
		
	}
	
	private MyExpression getExpression() {
		return this.expression;
	}
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	//TODO check if the location given is in a function body!!!
	
	public Object evaluate(Program program) {
		this.setValue(getExpression().evaluate(program));
		return this.returnValue();
	}
}
