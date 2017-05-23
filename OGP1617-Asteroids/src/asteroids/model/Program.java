package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.programs.MyFunction;
import asteroids.programs.MyStatement;
import asteroids.programs.expressions.BasicExpression;

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
    
    public double getTime() {
		return this.time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	private double time = 0;
	
	public Map<String, BasicExpression> getGlobalVariables() {
		return globalVariables;
	}

	public void addGlobalVariable(String name, BasicExpression expression) {
		this.getGlobalVariables().put(name, expression);
	}

	private Map<String, BasicExpression> globalVariables = new HashMap<>();
	
	public void setIsExecuted(Boolean bool) {
		this.isExecuted = bool;
	}
	
	public boolean isExecuted() {
		return this.isExecuted;
	}
	
	private boolean isExecuted = false;
	
	public MyFunction getExecutingFunction() {
		return this.executingFunction;
	}

	public void setExecutingFunction(MyFunction function) {
		this.executingFunction = function;
	}

	private MyFunction executingFunction = null;
}
