package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public class Bullet implements ToEntityExpression {
    public Entity evaluate(Program program) {
        try {
            return program.getShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.Bullet"), program.getShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}