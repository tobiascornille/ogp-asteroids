package asteroids.programs.expressions;

import asteroids.model.Program;

public class LessThan extends BinaryExpression<BasicExpression, BasicExpression> implements ToBooleanExpression {

    public LessThan(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public LessThan(NameExpression leftExpression, NameExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate(Program program) {
        return (double) this.getLeftExpression().evaluate(program) < (double) this.getRightExpression().evaluate(program);
    }
}
