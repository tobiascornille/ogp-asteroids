package asteroids.programs.expressions;

import asteroids.model.Program;

public class LessThan extends BinaryExpression<ToDoubleExpression, ToDoubleExpression> implements ToBooleanExpression {

    public LessThan(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate(Program program) {
        return this.getLeftExpression().evaluate(program) < this.getRightExpression().evaluate(program);
    }
}
