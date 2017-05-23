package asteroids.programs;

import java.util.HashMap;
import java.util.Map;

import asteroids.programs.expressions.BasicExpression;

public class MyFunction {
	
	public Map<String, BasicExpression> getLocalVariables() {
		return localVariables;
	}

	public void addLocalVariable(String name, BasicExpression expression) {
		this.getLocalVariables().put(name, expression);
	}

	private Map<String, BasicExpression> localVariables = new HashMap<>();
}
