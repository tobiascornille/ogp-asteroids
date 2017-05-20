package asteroids.programs.expressions;

import asteroids.programs.MyExpression;
import be.kuleuven.cs.som.annotate.*;

public abstract class BinaryExpression<E extends MyExpression, F extends MyExpression> implements MyExpression {

    BinaryExpression(E leftExpression, F rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Basic
    public MyExpression getLeftExpression() {
        return this.leftExpression;
    }

    private final E leftExpression;

    @Basic
    public MyExpression getRightExpression() {
        return this.rightExpression;
    }

    private final F rightExpression;
}
