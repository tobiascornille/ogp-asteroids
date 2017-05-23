package asteroids.programs;

import java.util.HashMap;
import java.util.Map;

import asteroids.programs.expressions.BasicExpression;

public class MyFunction {
	
	public Map<String, Object> getLocalVariables() {
		return localVariables;
	}

	public void addLocalVariable(String name, Object object) {
		this.getLocalVariables().put(name, object);
	}

	private Map<String, Object> localVariables = new HashMap<>();
}
