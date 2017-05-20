package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetRadius extends UnaryExpression<ToEntityExpression> implements ToDoubleExpression {

    public GetRadius(ToEntityExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return this.getExpression().evaluate(program).getRadius();
    }
}