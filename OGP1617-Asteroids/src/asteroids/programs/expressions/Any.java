package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public class Any implements ToEntityExpression {
	
    public Entity evaluate(Program program) {
        for (Entity entity: program.getShip().getWorld().getEntities())
            return entity;

        return null;
    }
}
