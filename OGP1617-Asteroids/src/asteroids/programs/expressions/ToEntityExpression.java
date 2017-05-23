package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public interface ToEntityExpression extends BasicExpression {
    
	Entity evaluate(Program program);
}
