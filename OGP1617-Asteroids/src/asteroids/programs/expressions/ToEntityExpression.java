package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public interface ToEntityExpression {
    
	Entity evaluate(Program program);
}
