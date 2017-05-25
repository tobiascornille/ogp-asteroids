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
		Object evaluated = this.getExpression().evaluate(program);
		if (evaluated == null)
			return;
		
		if (function != null) {
			if (function.getLocalVariables().get(name) != null) {
				Object existingValue = function.getLocalVariables().get(name);
				
				if (existingValue.getClass().equals(evaluated.getClass()))
					function.addLocalVariable(name, evaluated);
				else
					throw new IllegalStatementException();
			}
			else
				function.addLocalVariable(this.getName(), this.getExpression().evaluate(program));	
		}
		else if (program.getFunctions().containsKey(name))
			throw new IllegalStatementException();
		
		else {
			if (program.getGlobalVariables().get(name) != null) {
				Object existingValue = program.getGlobalVariables().get(name);
				
				if (existingValue.getClass().equals(evaluated.getClass()))
					program.addGlobalVariable(name, evaluated);
				else
					throw new IllegalStatementException();
			}
			else
				program.addGlobalVariable(name, evaluated);
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
