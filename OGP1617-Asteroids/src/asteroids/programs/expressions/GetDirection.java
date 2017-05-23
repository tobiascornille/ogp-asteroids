package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetDirection extends UnaryExpression<ToShipExpression> implements ToDoubleExpression {

    public GetDirection() {
        super(new Self());
    }

    public Double evaluate(Program program) {
        return ((asteroids.model.Ship) this.getExpression().evaluate(program)).getOrientation();
    }
}