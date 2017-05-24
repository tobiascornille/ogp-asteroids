package asteroids.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.MyFunction;
import asteroids.programs.MyStatement;
import asteroids.programs.expressions.BasicExpression;

public class Program {

	public Program(List<MyFunction> functions, MyStatement main) {
		for (MyFunction function: functions) {
			this.addFunction(function.getName(), function);
		}
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

    public Map<String, MyFunction> getFunctions() {
        return functions;
    }

    public void addFunction(String name, MyFunction function) {
    	this.getFunctions().put(name, function);
	}

    private Map<String, MyFunction> functions = new HashMap<>();

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

	public void addTime(double time) {
		this.time += time;
	}

	private double time = 0;
	
	public Map<String, Object> getGlobalVariables() {
		return globalVariables;
	}

	public void addGlobalVariable(String name, Object object) {
		this.getGlobalVariables().put(name, object);
	}

	private Map<String, Object> globalVariables = new HashMap<>();
	
	public void setIsExecuted(Boolean bool) {
		this.isExecuted = bool;
	}
	
	public boolean isExecuted() {
		return this.isExecuted;
	}
	
	private boolean isExecuted = false;
	
	public void firstExecution(){
		this.isFirstExecution = false;
	}
	
	public boolean isfirstExecution() {
		return this.isFirstExecution;
	}
	
	private boolean isFirstExecution = true;
	
	public MyFunction getExecutingFunction() {
		return this.executingFunctions.peek();
	}

	public void setExecutingFunction(MyFunction function) {
		this.executingFunctions.push(function);
	}
	
	public void removeExecutingFunction() {
		this.executingFunctions.pop();
	}
	
	private Deque<MyFunction> executingFunctions = new ArrayDeque<>();
	
	public boolean inWhile() {
		return !inWhile.isEmpty();
	}

	public void setInWhile() {
		this.inWhile.push("e");
	}

	public void setOutOfWhile() {
		this.inWhile.pop();
	}

	private Deque<String> inWhile = new ArrayDeque<>();
	
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Object getReturnObject() {
		return returnObject;
	}
	
	private Object returnObject = null;

	public boolean inFunction() {
		return this.getExecutingFunction() != null;
	}
	
	public SourceLocation getGoalLocation() {
		return goalLocation;
	}

	public void setGoalLocation(SourceLocation goalLocation) {
		this.goalLocation = goalLocation;
	}

	private SourceLocation goalLocation = null;
	
}
