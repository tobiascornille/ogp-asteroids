package asteroids.programs.expressions;

import asteroids.model.Program;

public class ReadVariable extends NameExpression {
    public ReadVariable(String variableName) {
        super(variableName);
    }

    public Object evaluate(Program program) {
        return program.getGlobalVariables().get(this.getName()).evaluate(program);
    }
}
