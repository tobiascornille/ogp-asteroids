package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class ChangeSign extends UnaryExpression<BasicExpression> implements ToDoubleExpression {
    public ChangeSign(ToDoubleExpression expression) {
        super(expression);
    }

    public ChangeSign(NameExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return - (double) this.getExpression().evaluate(program);
    }
}
