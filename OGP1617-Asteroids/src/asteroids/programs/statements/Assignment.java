package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public class Assignment extends MyStatement  {
	
	public Assignment(String name, MyExpression value, SourceLocation location) {
		super(location);
		this.name = name;
		this.expression = value;
	}
	
	private String name;
	private MyExpression expression;
	
	@Override
	public Object evaluate(Program porgram) {
		// TODO return shit
		
		return null;
	}
}
