package asteroids.programs.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;


public class Print extends MyStatement {
	
	private MyExpression expression;
	
	
	public Print (MyExpression expression, SourceLocation location) {
		super(location);
		this.expression = expression;
	}
	
	private MyExpression getExpression() {
		return this.expression;
	}
	
	@Override
	public List<Object> evaluate(Program program) {	
		Object result = this.getExpression().evaluate(program);
		if (result == null) {
			System.out.println(result);
			printed.add(null);
		}
		
		else {
			System.out.println(result.toString());
			printed.add(result);
		}
		return this.getPrinted();
	}
	
	

	
	
}
