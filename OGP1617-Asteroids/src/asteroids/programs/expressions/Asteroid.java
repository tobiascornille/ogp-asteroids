package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public class Asteroid implements ToEntityExpression {
    public Entity evaluate(Program program) {
        try {
            return program.getShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.Asteroid"), program.getShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
