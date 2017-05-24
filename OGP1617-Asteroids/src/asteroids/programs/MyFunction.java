package asteroids.programs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.programs.expressions.BasicExpression;
import asteroids.programs.statements.BasicStatement;

public class MyFunction {

	public MyFunction(String name, BasicStatement body){
		this.name = name;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	private final String name;

	public BasicStatement getBody() {
		return body;
	}

	private final BasicStatement body;

	public Map<String, Object> getLocalVariables() {
		return localVariables;
	}

	public void addLocalVariable(String name, Object object) {
		this.getLocalVariables().put(name, object);
	}

	private Map<String, Object> localVariables = new HashMap<>();

	public List<Object> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	private List<Object> parameters;
}
