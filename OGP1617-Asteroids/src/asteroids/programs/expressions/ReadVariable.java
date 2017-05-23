package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyFunction;

public class ReadVariable extends NameExpression {
    public ReadVariable(String variableName) {
        super(variableName);
    }

    public Object evaluate(Program program) {
        MyFunction function = program.getExecutingFunction();
        Object value;

        if (function != null) {
            value = function.getLocalVariables().get(this.getName());

            if (value != null)
                return value;
        }

        value = program.getGlobalVariables().get(this.getName());
        if (value == null)
            throw new RuntimeException();
        return value;
    }
}
