package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.model.Ship;

public class Self implements ToShipExpression {
	
    public Ship evaluate(Program program) {
        return program.getShip();
    }
}
