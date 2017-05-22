package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Bullet extends MyExpression implements ToEntityExpression {
    public Entity evaluate(Program program) {
    	return program.getExecutingShip().getWorld().getBulletFromShip(program.getExecutingShip());
    }
}
