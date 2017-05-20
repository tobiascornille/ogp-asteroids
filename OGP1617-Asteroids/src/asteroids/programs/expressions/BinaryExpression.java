package asteroids.programs.expressions;

import asteroids.programs.MyExpression;
import be.kuleuven.cs.som.annotate.*;

public abstract class BinaryExpression<E, F> extends MyExpression {

    BinaryExpression(E leftExpression, F rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Basic
    public E getLeftExpression() {
        return this.leftExpression;
    }

    private final E leftExpression;

    @Basic
    public F getRightExpression() {
        return this.rightExpression;
    }

    private final F rightExpression;
}
