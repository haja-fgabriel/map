import java.util.Vector;

public class MultiplicationExpression extends ComplexExpression {
    public MultiplicationExpression(Vector<Operation> operations, Vector<Complex> args) {
        super(operations, args);
    }

    @Override
    public Complex execute() {
        Complex number = args.firstElement();
        for (int i = 0; i < args.size() - 1; i++)
            if (operations.elementAt(i) == Operation.MULTIPLICATION)
                number.multiply(args.elementAt(i+1));
            else
                number.divide(args.elementAt(i+1));
        return number;
    }

    @Override
    public Complex executeOneOperation() {
        return null;
    }
}
