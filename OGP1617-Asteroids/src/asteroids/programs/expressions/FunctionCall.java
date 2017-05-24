package asteroids.programs.expressions;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.programs.MyExpression;
import asteroids.programs.MyFunction;

public class FunctionCall extends NameExpression {

    public FunctionCall(String functionName, List<? extends MyExpression> arguments) {
        super(functionName);
        this.arguments = arguments;
    }

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
        
        program.removeExecutingFunction();;
        return program.getReturnObject();
    }
}
