package asteroids.programs;

public class Addition extends BinaryExpression implements ToDoubleExpression {

    public Addition(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Double evaluate() {
        return (double) this.getLeftExpression().evaluate() + (double) this.getRightExpression().evaluate();
    }
}
