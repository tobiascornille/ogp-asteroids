package asteroids.programs.expressions;

import asteroids.model.Program;

public class Addition extends BinaryExpression<ToDoubleExpression, ToDoubleExpression> implements ToDoubleExpression {

    public Addition(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Double evaluate(Program program) {
        return this.getLeftExpression().evaluate(program) + this.getRightExpression().evaluate(program);
    }
}
