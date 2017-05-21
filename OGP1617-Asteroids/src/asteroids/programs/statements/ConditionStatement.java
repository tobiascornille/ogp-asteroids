package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.MyStatement;

public abstract class ConditionStatement extends MyStatement {
	
	public ConditionStatement(MyExpression condition, MyStatement body, SourceLocation location) {
		super(location);
		this.setCondition(condition);
		this.setBody(body);
	}
	
	private MyExpression condition;
	private MyStatement body;
	
	public void setCondition(MyExpression condition) {
		this.condition = condition;
	}
	
	public MyExpression getCondition() {
		return this.condition;
	}
	
	public void setBody(MyStatement body) {
		this.body = body;
	}
	
	public MyStatement getBody() {
		return this.body;
	}
	
	
}
