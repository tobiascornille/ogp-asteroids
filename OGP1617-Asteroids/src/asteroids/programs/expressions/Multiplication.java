package asteroids.programs.expressions;

import asteroids.model.Program;

public class Multiplication extends BinaryExpression<ToDoubleExpression, ToDoubleExpression> implements ToDoubleExpression {

    public Multiplication(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Double evaluate(Program program) {
        return (double) this.getLeftExpression().evaluate(program) * (double) this.getRightExpression().evaluate(program);
    }
}
