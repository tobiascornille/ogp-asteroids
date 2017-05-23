package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyExpression;
import asteroids.programs.expressions.Self;

public class Turn extends ActionStatement {

	public Turn(MyExpression angle, SourceLocation location) {
		super(location);
		this.setAngle(angle);
	}
	
	public MyExpression getAngle() {
		return angle;
	}

	public void setAngle(MyExpression angle) {
		this.angle = angle;
	}

	private MyExpression angle;
	
	public void Evaluate(Program program) {
		Self self = new Self();
		Ship ship = self.evaluate(program);
		ship.turn((double) this.getAngle().evaluate(program));
		
	}
	
}
