package asteroids.programs.expressions;

import asteroids.model.Program;

public interface ToBooleanExpression extends BasicExpression {
    Boolean evaluate(Program program);
}
