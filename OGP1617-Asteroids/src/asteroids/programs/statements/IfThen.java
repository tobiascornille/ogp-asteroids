package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public class IfThen extends ConditionStatement {

	public IfThen(MyExpression condition, MyStatement body,  MyStatement elseBody, SourceLocation location) {
		super(condition, body, location);
		this.setElseBody(elseBody);
	}
	
	private  MyStatement elseBody;
	
	public void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
	}
	
	public MyStatement getElseBody() {
		return this.elseBody;
	}
	
	public Object evaluate(Program program) {
		if ((boolean) this.getCondition().evaluate(program))
			this.getBody().evaluate(program);
		
		else if (this.getElseBody() != null) 
			this.getElseBody().evaluate(program);
		
		return null;
	}
}
