package asteroids.programs.statements;

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
	public Object evaluate() {	
		this.setValue(this.getExpression().evaluate());;
		System.out.println(this.getValue().toString());
		return this.returnValue();
	}

	
	
}
