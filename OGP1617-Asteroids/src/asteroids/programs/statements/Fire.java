package asteroids.programs.statements;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class Fire extends ActionStatement {
	
	public Fire(SourceLocation location) {
		super(location);
	}
	
	public void evaluate(Program program) {
		resolveAction(program);
		program.getExecutingShip().fireBullet();
	}
	
	//TODO get the program that is running, and then the ship owning that program

}
