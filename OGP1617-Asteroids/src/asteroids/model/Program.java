package asteroids.model;

import java.util.ArrayList;
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

    public List<Object> getPrinted() {
        return printed;
    }

    public void addPrinted(Object object) {
        this.printed.add(object);
    }

    private List<Object> printed = new ArrayList<>();
}
