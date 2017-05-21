package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Return extends ReturnStatement {
	
	private MyExpression expression;
	
	public Return (MyExpression expression) {
		this.expression = expression;
		
	}
	
	//TODO check if the location given is in a function body!!!
	
	public Object evaluate(Program program) {
		this.setValue(expression.evaluate(program));
		return this.returnValue();
	}
}
