package asteroids.programs.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public class Return extends MyStatement {
	
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
		Object value = this.getExpression().evaluate(program);
		return value;
	}
	
}
