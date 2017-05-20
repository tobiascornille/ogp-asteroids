package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.programs.MyExpression;


public class Print extends ReturnStatement {
	
	private MyExpression expression;
	
	
	public Print (MyExpression expression ){
		this.expression = expression;
	}
	
	private MyExpression getExpression() {
		return this.expression;
	}
	
	@Override
	public Object evaluate(Program program) {	
		this.setValue(this.getExpression().evaluate(program));;
		System.out.println(this.getValue().toString());
		return this.returnValue();
	}

	
	
}
