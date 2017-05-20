package asteroids.programs.expressions;

public class ChangeSign extends UnaryExpression implements ToDoubleExpression {
    public ChangeSign(ToDoubleExpression expression) {
        super(expression);
    }

    public Double evaluate() {
        return - (double) this.getExpression().evaluate();
    }
}
