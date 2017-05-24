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
		program.setInWhile();
		while ((boolean) this.getCondition().evaluate(program))
			try {
				this.getBody().evaluate(program);
			} catch (BreakException e) {
				if (program.inFunction())
					program.removeExecutingFunction();
				program.setOutOfWhile();
				return;
			}
		program.setOutOfWhile();
	}
	
	@Override
	public Boolean goToGoalLocation(Program program) {
		Boolean found = false;
		if (! this.getSourceLocation().equals(program.getGoalLocation()))
			while ((boolean) this.getCondition().evaluate(program)) {
				if (!found)
					found = this.getBody().goToGoalLocation(program);
				else
					this.getBody().evaluate(program);
			}
		else {
			found = true;
			this.evaluate(program);
		}
		return found;
	}	

}
