package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetY extends UnaryExpression<ToEntityExpression> implements ToDoubleExpression {

    public GetY(ToEntityExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return this.getExpression().evaluate(program).getPosition().getYComponent();
    }
}