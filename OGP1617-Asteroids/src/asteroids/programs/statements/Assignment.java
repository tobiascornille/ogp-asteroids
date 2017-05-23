package asteroids.programs.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;
import asteroids.programs.expressions.BasicExpression;

public class Assignment extends MyStatement  {
	
	public Assignment(String name, BasicExpression expression, SourceLocation location) {
		super(location);
		this.setName(name);
		this.setExpression(expression);
	}
	
	private String name;
	private BasicExpression expression;
	
	
	
	@Override
	public void evaluate(Program program) {
		if (program.getExecutingFunction() != null)
			program.getExecutingFunction().addLocalVariable(this.getName(), this.getExpression().evaluate(program));
		else
			program.addGlobalVariable(this.getName(), this.getExpression().evaluate(program));
	}

	private BasicExpression getExpression() {
		return this.expression;
	}
	
	private void setExpression(BasicExpression expression) {
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
