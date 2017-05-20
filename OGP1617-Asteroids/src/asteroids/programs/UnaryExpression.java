package asteroids.programs;

import be.kuleuven.cs.som.annotate.*;

public abstract class UnaryExpression<E extends MyExpression> implements MyExpression {
    UnaryExpression(E expression) {
        this.expression = expression;
    }

    @Basic
    public MyExpression getExpression() {
        return this.expression;
    }

    private final E expression;
}
