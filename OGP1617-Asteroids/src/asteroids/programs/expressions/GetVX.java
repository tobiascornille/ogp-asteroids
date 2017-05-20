package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetVX extends UnaryExpression<ToEntityExpression> implements ToDoubleExpression {

    public GetVX(ToEntityExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return this.getExpression().evaluate(program).getVelocity().getXComponent();
    }
}