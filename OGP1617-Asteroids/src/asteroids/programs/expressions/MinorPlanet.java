package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class MinorPlanet extends MyExpression implements ToEntityExpression {
    public Entity evaluate(Program program) {
        try {
            return program.getExecutingShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.MinorPlanet"), program.getExecutingShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
