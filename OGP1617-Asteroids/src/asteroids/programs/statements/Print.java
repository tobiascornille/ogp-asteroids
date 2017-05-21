package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;


public class Print extends ReturnStatement {
	
	private MyExpression expression;
	
	
	public Print (MyExpression expression, SourceLocation location ){
		super(location);
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
