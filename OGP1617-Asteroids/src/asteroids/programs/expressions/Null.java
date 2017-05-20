package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Null extends MyExpression {
    
    public Object evaluate(Program program) {
        return null;
    }
}
