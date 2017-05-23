package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;
import asteroids.programs.MyFunction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FunctionCall extends MyExpression {

    public FunctionCall(String functionName, List<? extends MyExpression> arguments) {
        this.name = functionName;
        this.arguments = arguments;
    }

    public String getName() {
        return this.name;
    }

    private final String name;

    public List<? extends MyExpression> getArguments() {
        return this.arguments;
    }

    private final List<? extends MyExpression> arguments;

    public Object evaluate(Program program) {
        MyFunction function = program.getFunctions().get(this.getName());
        program.setExecutingFunction(function);
        List<Object> parameters = new ArrayList<>();
        for(MyExpression argument: this.getArguments())
            parameters.add(argument.evaluate(program));
        function.setParameters(parameters);
        function.getBody().evaluate(program);
        return program.getReturnObject();
    }
}
