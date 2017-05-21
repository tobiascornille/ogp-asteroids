package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Ship extends MyExpression implements ToShipExpression {
    public asteroids.model.Ship evaluate(Program program) {
        try {
            return program.getExecutingShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.Ship"), program.getExecutingShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
