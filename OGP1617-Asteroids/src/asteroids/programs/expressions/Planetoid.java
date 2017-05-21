package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.model.Program;

public class Planetoid implements ToEntityExpression {
    public Entity evaluate(Program program) {
        try {
            return program.getShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.Planetoid"), program.getShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}