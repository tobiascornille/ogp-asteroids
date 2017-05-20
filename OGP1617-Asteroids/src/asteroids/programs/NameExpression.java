package asteroids.programs;

public abstract class NameExpression implements MyExpression {
    NameExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private final String name;
}
