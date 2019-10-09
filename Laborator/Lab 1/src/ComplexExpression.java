import java.util.Vector;

public abstract class ComplexExpression {
    Vector<Operation> operations;
    Vector<Complex> args;

    public ComplexExpression(Vector<Operation> operations, Vector<Complex> args) {
        this.operations = operations;
        this.args = args;
    }

    public abstract Complex executeOneOperation();

    public abstract Complex execute();
}
