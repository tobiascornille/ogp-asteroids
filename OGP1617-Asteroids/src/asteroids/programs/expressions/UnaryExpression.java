package asteroids.programs.expressions;

import asteroids.programs.MyExpression;
import be.kuleuven.cs.som.annotate.*;

public abstract class UnaryExpression<E> extends MyExpression {
    UnaryExpression(E expression) {
        this.expression = expression;
    }

    @Basic
    public E getExpression() {
        return this.expression;
    }

    private final E expression;
}
