package asteroids.programs.expressions;

import asteroids.model.Program;
import asteroids.programs.MyExpression;

public class Equality extends BinaryExpression<MyExpression, MyExpression> implements ToBooleanExpression {

    public Equality(MyExpression leftExpression, MyExpression rightExpression){
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate(Program program) {
    	if (this.getLeftExpression().evaluate(program) == null || this.getRightExpression().evaluate(program) == null)
    		return false;
        if (! this.getLeftExpression().evaluate(program).getClass().equals(this.getRightExpression().evaluate(program).getClass()))
            return false;
        return this.getLeftExpression().evaluate(program).equals(this.getRightExpression().evaluate(program));
    }
}
