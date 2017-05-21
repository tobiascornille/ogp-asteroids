package asteroids.programs.expressions;

import asteroids.model.Program;

public class Ship implements ToShipExpression {
    public asteroids.model.Ship evaluate(Program program) {
        try {
            return program.getShip().getWorld().getClosestEntityOfType(Class.forName("asteroids.model.Ship"), program.getShip());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
