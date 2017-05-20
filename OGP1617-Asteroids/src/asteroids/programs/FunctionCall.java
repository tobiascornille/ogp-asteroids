package asteroids.programs;

import java.util.List;

public class FunctionCall extends NameExpression {
    public FunctionCall(String functionName, List<? extends MyExpression> arguments) {
        super(functionName);
        this.arguments = arguments;
    }


    public List<? extends MyExpression> getArguments() {
        return this.arguments;
    }

    private final List<? extends MyExpression> arguments;

    public Object evaluate() {
        functionName.
    }
}
