package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public abstract class NameExpression extends MyExpression
        implements ToBooleanExpression, ToDoubleExpression, ToEntityExpression, ToShipExpression{
    NameExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private final String name;

    public abstract Object evaluate(Program program);
}
