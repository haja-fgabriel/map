import java.util.Vector;

public class ExpressionFactory {
    private static ExpressionFactory instance;
    private ExpressionFactory() {}

    public ComplexExpression createExpression(Vector<Operation> operations, Vector<Complex> args) {
        if (operations.size() == 0)
            return new AdditionExpression(operations, args);

        if (operations.firstElement().equals(Operation.ADDITION) || operations.firstElement().equals(Operation.SUBTRACTION))
            return new AdditionExpression(operations, args);
        return new MultiplicationExpression(operations, args);
    }

    public static ExpressionFactory getInstance() {
        if (instance == null)
            instance  = new ExpressionFactory();
        return instance;
    }
}
