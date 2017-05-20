package asteroids.programs;

public class Not extends UnaryExpression implements ToBooleanExpression {
    public Not(ToBooleanExpression expression) {
        super(expression);
    }

    public Boolean evaluate() {
        return ! (boolean) this.getExpression().evaluate();
    }
}