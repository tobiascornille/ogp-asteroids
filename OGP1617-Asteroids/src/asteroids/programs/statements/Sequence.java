package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyStatement;

public class Sequence extends MyStatement {
	
	public Sequence (List<MyStatement> statements, SourceLocation location) {
		super(location);
		this.setStatements(statements);
	}
	
	public List<MyStatement> getStatements() {
		return statements;
	}

	public void setStatements(List<MyStatement> statements) {
		this.statements = statements;
	}

	private List<MyStatement> statements;
	
	public Object evaluate(Program program) {
		
		for (MyStatement e: this.getStatements()) {
			e.evaluate(program);
		}
		
		return null;

	}
	
}
