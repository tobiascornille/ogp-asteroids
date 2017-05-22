package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public class While extends ConditionStatement{
	
	public While (MyExpression condition, MyStatement body, SourceLocation location) {
		super(condition, body, location);
	}
	
	public Object evaluate(Program program) {
		
		while ((boolean) this.getCondition().evaluate(program))
			this.getBody().evaluate(program);
		
		return null;
	}

}
