package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.programs.MyExpression;

public class Self extends MyExpression implements ToShipExpression {
	
    public Ship evaluate(Program program) {
        return program.getExecutingShip();
    }
}
