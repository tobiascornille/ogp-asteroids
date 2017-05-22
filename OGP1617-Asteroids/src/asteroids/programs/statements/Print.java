package asteroids.programs.statements;

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
		this.addValue(this.getExpression().evaluate(program));;
		System.out.println(this.getValue().toString());
		printed.add(this.getValue());
		return this.getPrinted();
	}
	
	private List<Object> value;

	protected void addValue(Object value) {
		this.value.add(value);
	}
	
	protected List<Object> getValue() {
		return this.value;
	}

	
	
}
