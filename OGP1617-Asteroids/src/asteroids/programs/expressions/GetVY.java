package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetVY extends UnaryExpression<ToEntityExpression> implements ToDoubleExpression {

    public GetVY(ToEntityExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return this.getExpression().evaluate(program).getVelocity().getYComponent();
    }
}