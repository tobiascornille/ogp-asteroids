package asteroids.programs.expressions;

import asteroids.model.Program;

public class Not extends UnaryExpression<ToBooleanExpression> implements ToBooleanExpression {
    public Not(ToBooleanExpression expression) {
        super(expression);
    }

    public Boolean evaluate(Program program) {
        return ! this.getExpression().evaluate(program);
    }
}