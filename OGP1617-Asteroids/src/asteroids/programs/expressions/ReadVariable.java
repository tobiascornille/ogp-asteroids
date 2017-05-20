package asteroids.programs.expressions;

import asteroids.model.Program;

public class ReadVariable extends NameExpression {
    public ReadVariable(String parameterName) {
        super(parameterName);
    }

    public Object evaluate(Program program) {
        return null; //TODO: fix after having implemented functions
    }
}
