package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Equality<E extends MyExpression, F extends MyExpression> extends BinaryExpression<E, F> implements ToBooleanExpression {

    public Equality(E leftExpression, F rightExpression){
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate(Program program) {
        if (! this.getLeftExpression().evaluate(program).getClass().equals(this.getRightExpression().evaluate(program).getClass()))
            return false;
        return this.getLeftExpression().evaluate(program).equals(this.getRightExpression().evaluate(program));
    }
}
