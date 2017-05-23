package asteroids.programs.expressions;

import asteroids.model.Program;

public class Multiplication extends BinaryExpression<BasicExpression, BasicExpression> implements ToDoubleExpression {

    public Multiplication(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Multiplication(NameExpression leftExpression, NameExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Double evaluate(Program program) {
        return (double) this.getLeftExpression().evaluate(program) * (double) this.getRightExpression().evaluate(program);
    }
}
