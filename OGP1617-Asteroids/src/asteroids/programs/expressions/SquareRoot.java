package asteroids.programs.expressions;

public class SquareRoot extends UnaryExpression implements ToDoubleExpression {
    public SquareRoot(ToDoubleExpression expression) {
        super(expression);
    }

    public Double evaluate() {
        return Math.sqrt((double) this.getExpression().evaluate());
    }
}

