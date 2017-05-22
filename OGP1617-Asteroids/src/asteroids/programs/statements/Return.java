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
		this.value = new ArrayList<>();
		
	}
	
	private MyExpression getExpression() {
		return this.expression;
	}
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	//TODO check if the location given is in a function body!!!
	
	public List<Object> evaluate(Program program) {
		this.addValue(getExpression().evaluate(program));
		return this.getValue();
	}
	
	private List<Object> value;

	protected void addValue(Object value) {
		this.value.add(value);
	}
	
	protected List<Object> getValue() {
		return this.value;
	}
}
