package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Literal extends UnaryExpression<Double> implements ToDoubleExpression{

    public Literal(double value) {
        super(value);
    }

    public Double evaluate(Program program) {
        return this.getExpression();
    }
}
