package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.programs.MyExpression;
import asteroids.programs.expressions.Self;

public class Fire extends ActionStatement {
	
	public Fire() {	
		
	}
	
	@Override
	public Object evaluate(Program program) {
		Self self = new Self();
		Ship ship =   self.evaluate(program);
		ship.fireBullet();
		return null;
	}
	
	//TODO get the program that is running, and then the ship owning that program

}
