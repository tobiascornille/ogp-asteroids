package asteroids.programs.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public class Assignment extends MyStatement  {
	
	public Assignment(String name, MyExpression value, SourceLocation location) {
		super(location);
		this.setName(name);
		this.setExpression (value);
	}
	
	private String name;
	private MyExpression expression;
	
	
	
	@Override
	public List<Object> evaluate(Program program) {
		Object name = getExpression().evaluate(program);
		List<Object> list = new ArrayList<>();
		list.add(name);
		return this.getPrinted();
	}

	private MyExpression getExpression() {
		return this.expression;
	}
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
