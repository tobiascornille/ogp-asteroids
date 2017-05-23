package asteroids.programs.expressions;

import asteroids.model.Program;

public class GetX extends UnaryExpression<ToEntityExpression> implements ToDoubleExpression {

    public GetX(ToEntityExpression expression) {
        super(expression);
    }

    public Double evaluate(Program program) {
        return ((asteroids.model.Entity) this.getExpression().evaluate(program)).getPosition().getXComponent();
    }
}