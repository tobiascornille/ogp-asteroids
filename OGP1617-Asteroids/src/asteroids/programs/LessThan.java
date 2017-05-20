package asteroids.programs;

public class LessThan extends BinaryExpression implements ToBooleanExpression {

    public LessThan(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate() {
        return (double) this.getLeftExpression().evaluate() < (double) this.getRightExpression().evaluate();
    }
}
