package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.model.Ship;

public interface ToShipExpression {
    
	Ship evaluate(Program program);
}
