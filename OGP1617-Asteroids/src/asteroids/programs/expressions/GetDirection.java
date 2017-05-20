package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetDirection extends UnaryExpression<ToShipExpression> implements ToDoubleExpression {

    public GetDirection(ToShipExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return this.getExpression().evaluate(program).getOrientation();
    }
}