package asteroids.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.*;

public class ProgramFactory implements IProgramFactory<MyExpression, MyStatement, MyFunction, Program> {

	@Override
	public Program createProgram(List<MyFunction> functions, MyStatement main) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyFunction createFunctionDefinition(String functionName, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createAssignmentStatement(String variableName, MyExpression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createWhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createReturnStatement(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createIfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createPrintStatement(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createSequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable(variableName);
	}

	@Override
	public MyExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ReadParameter(parameterName);
	}

	@Override
	public MyExpression createFunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionCall(functionName, actualArgs);
	}

	@Override
	public MyExpression createChangeSignExpression(MyExpression expression, SourceLocation sourceLocation) {
		return new ChangeSign((ToDoubleExpression) expression);
	}

	@Override
	public MyExpression createNotExpression(MyExpression expression, SourceLocation sourceLocation) {
		return new Not((ToBooleanExpression) expression);
	}

	@Override
	public MyExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new Literal(value);
	}

	@Override
	public MyExpression createNullExpression(SourceLocation location) {
		return new Null();
	}

	@Override
	public MyExpression createSelfExpression(SourceLocation location) {
		return new Self();
	}

	@Override
	public MyExpression createShipExpression(SourceLocation location) {
		return new Ship();
	}

	@Override
	public MyExpression createAsteroidExpression(SourceLocation location) {
		return new Asteroid();
	}

	@Override
	public MyExpression createPlanetoidExpression(SourceLocation location) {
		return new Planetoid();
	}

	@Override
	public MyExpression createBulletExpression(SourceLocation location) {
		return new Bullet();
	}

	@Override
	public MyExpression createPlanetExpression(SourceLocation location) {
		return new MinorPlanet();
	}

	@Override
	public MyExpression createAnyExpression(SourceLocation location) {
		return new Any();
	}

	@Override
	public MyExpression createGetXExpression(MyExpression e, SourceLocation location) {
		return new GetX((ToEntityExpression) e);
	}

	@Override
	public MyExpression createGetYExpression(MyExpression e, SourceLocation location) {
		return new GetY((ToEntityExpression) e);
	}

	@Override
	public MyExpression createGetVXExpression(MyExpression e, SourceLocation location) {
		return new GetVX((ToEntityExpression) e);
	}

	@Override
	public MyExpression createGetVYExpression(MyExpression e, SourceLocation location) {
		return new GetVY((ToEntityExpression) e);
	}

	@Override
	public MyExpression createGetRadiusExpression(MyExpression e, SourceLocation location) {
		return new GetRadius((ToEntityExpression) e);
	}

	@Override
	public MyExpression createLessThanExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new LessThan((ToDoubleExpression) e1, (ToDoubleExpression) e2);
	}

	@Override
	public MyExpression createEqualityExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new Equality (e1, e2);
	}

	@Override
	public MyExpression createAdditionExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new Addition((ToDoubleExpression) e1, (ToDoubleExpression) e2);
	}

	@Override
	public MyExpression createMultiplicationExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new Multiplication((ToDoubleExpression) e1, (ToDoubleExpression) e2);
	}

	@Override
	public MyExpression createSqrtExpression(MyExpression e, SourceLocation location) {
		return new SquareRoot((ToDoubleExpression) e);
	}

	@Override
	public MyExpression createGetDirectionExpression(SourceLocation location) {
		return new GetDirection();
	}

	@Override
	public MyStatement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createTurnStatement(MyExpression angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

}
