package asteroids.programs.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyFunction;
import asteroids.programs.MyStatement;
import asteroids.programs.expressions.BasicExpression;

public class Assignment extends BasicStatement  {
	
	public Assignment(String name, BasicExpression expression, SourceLocation location) {
		super(location);
		this.setName(name);
		this.setExpression(expression);
	}
	
	private String name;
	private BasicExpression expression;
	
	@Override
	public void evaluate(Program program) throws IllegalStatementException {
		MyFunction function = program.getExecutingFunction();
		String name = this.getName();
		
		if (function != null) {
			if (function.getLocalVariables().containsKey(name)) {
				if (function.getLocalVariables().get(name).getClass().equals(this.getExpression().evaluate(program).getClass()))
					function.addLocalVariable(this.getName(), this.getExpression().evaluate(program));
				else
					throw new IllegalStatementException();
			}
			else
				function.addLocalVariable(this.getName(), this.getExpression().evaluate(program));	
		}
		else if (program.getFunctions().containsKey(this.getName()))
			throw new IllegalStatementException();
		
		else {
			if (program.getGlobalVariables().containsKey(name)) {
				//TODO: add null checks
				if (program.getGlobalVariables().get(name).getClass().equals(this.getExpression().evaluate(program).getClass()))
					program.addGlobalVariable(this.getName(), this.getExpression().evaluate(program));
				else
					throw new IllegalStatementException();
			}
			else
				program.addGlobalVariable(this.getName(), this.getExpression().evaluate(program));
		}
	}
	
	@Override
	public Boolean goToGoalLocation(Program program) {
		if (! this.getSourceLocation().equals(program.getGoalLocation()))
			return false;
		else
			this.evaluate(program);
		return true;
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
