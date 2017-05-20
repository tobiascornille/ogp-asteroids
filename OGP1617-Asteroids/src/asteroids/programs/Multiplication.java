package asteroids.programs;

public class Multiplication extends BinaryExpression implements ToDoubleExpression {

    public Multiplication(ToDoubleExpression leftExpression, ToDoubleExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    public Double evaluate() {
        return (double) this.getLeftExpression().evaluate() * (double) this.getRightExpression().evaluate();
    }
}
