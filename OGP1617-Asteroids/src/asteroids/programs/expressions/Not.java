package asteroids.programs.expressions;

import asteroids.model.Program;

public class Not extends UnaryExpression<BasicExpression> implements ToBooleanExpression {
    public Not(ToBooleanExpression expression) {
        super(expression);
    }

    public Not(NameExpression expression) {
        super(expression);
    }

    public Boolean evaluate(Program program) {
        return ! (boolean) this.getExpression().evaluate(program);
    }
}