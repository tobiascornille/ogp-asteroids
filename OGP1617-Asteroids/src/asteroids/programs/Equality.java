package asteroids.programs;

public class Equality<E extends MyExpression, F extends MyExpression> extends BinaryExpression implements ToBooleanExpression {

    public Equality(E leftExpression, F rightExpression){
        super(leftExpression, rightExpression);
    }

    public Boolean evaluate() {
        if (! this.getLeftExpression().evaluate().getClass().equals(this.getRightExpression().evaluate().getClass()))
            return false;
        return this.getLeftExpression().evaluate().equals(this.getRightExpression().evaluate());
    }
}
