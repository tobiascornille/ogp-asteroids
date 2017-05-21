package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Any extends MyExpression implements ToEntityExpression {
	
    public Entity evaluate(Program program) {
        for (Entity entity: program.getExecutingShip().getWorld().getEntities())
            return entity;

        return null;
    }
}
