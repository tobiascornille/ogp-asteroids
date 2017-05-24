package asteroids.programs.expressions;

import asteroids.model.Program;

public class ReadParameter extends NameExpression {
    public ReadParameter(String parameterName) {
        super(parameterName);
    }

    public Object evaluate(Program program) {
        int i = Character.getNumericValue(this.getName().charAt(1)) - 1;
        return program.getExecutingFunction().getParameters().get(i);
    }
}
