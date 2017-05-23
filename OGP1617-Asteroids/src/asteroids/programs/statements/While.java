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
	
	public void evaluate(Program program) {
		program.setInWhile(true);
		while ((boolean) this.getCondition().evaluate(program))
			try {
				this.getBody().evaluate(program);
			} catch (BreakException e) {
				program.setInWhile(false);
				return;
			}
			
		program.setInWhile(false);
	}

}
