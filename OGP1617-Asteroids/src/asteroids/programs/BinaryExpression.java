package asteroids.programs;

import be.kuleuven.cs.som.annotate.*;

public abstract class BinaryExpression extends ComposedExpression {

    @Basic
    public MyExpression getLeftOperand() {
        return leftOperand;
    }

    private final MyExpression leftOperand;

    @Basic
    public MyExpression getRightOperand() {
        return rightOperand;
    }

    private final MyExpression rightOperand;
}
