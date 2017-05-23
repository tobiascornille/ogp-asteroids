package asteroids.programs.expressions;

import asteroids.model.Program;

public class SquareRoot extends UnaryExpression<BasicExpression> implements ToDoubleExpression {

    public SquareRoot(ToDoubleExpression expression) {
        super(expression);
    }

    public SquareRoot(NameExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return Math.sqrt((double) this.getExpression().evaluate(program));
    }
}

