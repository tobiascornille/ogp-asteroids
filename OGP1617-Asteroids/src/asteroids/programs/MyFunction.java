package asteroids.programs;

import java.util.ArrayDeque;
import java.util.Deque;
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
	private Deque<Map<String, Object>> localVariablesStack = new ArrayDeque<>();
	
	public void removeLocalVariables() {
		 localVariablesStack.remove();
	}
	
	public Map<String, Object> getLocalVariables() {
		return localVariablesStack.peek();
	}

	public void addLocalVariable(String name, Object object) {
		this.getLocalVariables().put(name, object);
	}
	
	public void newLocalVariables() {
		localVariablesStack.push(new HashMap<String, Object>());
	}

	public List<Object> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	private List<Object> parameters;
}
