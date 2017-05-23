package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FunctionCall extends MyExpression {

    public FunctionCall(String functionName, List<? extends MyExpression> arguments) throws IllegalArgumentException{
        super(functionName);
        this.arguments = arguments;
        Class[] classes = new Class[]{};
        for (int i = 0; i < arguments.size(); i++) {
            classes[i] = this.getArguments().get(i).getClass();
        }
        try {
            this.function = (classes[0]).getMethod(functionName, classes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException();
        }
    }


    public List<? extends MyExpression> getArguments() {
        return this.arguments;
    }

    private final List<? extends MyExpression> arguments;

    public Method getFunction() {
        return function;
    }

    private final Method function;

    public Object evaluate(Program program) {
        try {
            return Program.invoke(this.getArguments()); //TODO: fix
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
