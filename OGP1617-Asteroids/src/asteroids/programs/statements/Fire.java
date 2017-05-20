package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.programs.MyExpression;

public class Fire extends ActionStatement {
	
	public Fire() {	
		
	}
	
	@Override
	public Object evaluate(Program program) {
		Ship ship = new Self.evaluate(program);
		ship.fireBullet();
		return null;
	}
	
	//TODO get the program that is running, and then the ship owning that program

}
