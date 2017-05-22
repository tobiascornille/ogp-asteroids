package asteroids.model;

import java.util.List;

import asteroids.programs.MyFunction;
import asteroids.programs.MyStatement;

public class Program {

	public Program(List<MyFunction> functions, MyStatement main) {
		this.functions = functions;
		this.main = main;
	}
	
	public void setExecutingShip(Ship ship) {
        this.executingShip = ship;
    }
	
    public Ship getExecutingShip() {
        return this.executingShip;
    }
    
    private Ship executingShip;

    public MyStatement getMain() {
        return main;
    }

	private final MyStatement main;

    public List<MyFunction> getFunctions() {
        return functions;
    }

    private final List<MyFunction> functions;
}
