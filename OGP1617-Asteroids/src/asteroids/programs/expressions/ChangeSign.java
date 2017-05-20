package asteroids.programs.expressions;

import asteroids.model.Program;

public class ChangeSign extends UnaryExpression<ToDoubleExpression> implements ToDoubleExpression {
    public ChangeSign(ToDoubleExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return - this.getExpression().evaluate(program);
    }
}
