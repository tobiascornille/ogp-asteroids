package asteroids.programs.expressions;

import asteroids.model.Program;

public class LessThan extends BinaryExpression<ToDoubleExpression, ToDoubleExpression> implements ToBooleanExpression {

    public LessThan(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super((ToDoubleExpression) leftExpression, (ToDoubleExpression)rightExpression);
    }

    public Boolean evaluate(Program program) {
        return (double) this.getLeftExpression().evaluate(program) < (double) this.getRightExpression().evaluate(program);
    }
}
