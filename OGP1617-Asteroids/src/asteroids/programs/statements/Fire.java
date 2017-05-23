package asteroids.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.Self;

public class Fire extends ActionStatement {
	
	public Fire(SourceLocation location) {
		super(location);
		
	}
	
	@Override
	public void evaluate(Program program) {
		Self self = new Self();
		Ship ship = self.evaluate(program);
		ship.fireBullet();
	}
	
	//TODO get the program that is running, and then the ship owning that program

}
