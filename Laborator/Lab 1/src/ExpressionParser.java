//import java.;

import java.util.Vector;

public class ExpressionParser {

    private static boolean isAdditionSign(String expr) {
        return expr.equals("+") || expr.equals("-");
    }

    private static boolean isMultiplicationSign(String expr) {
        return expr.equals("*") || expr.equals("/");
    }

    private static boolean isSign(String expr) {
        return isAdditionSign(expr) || isMultiplicationSign(expr);
    }

    private static Operation getOperationBySign(String sign) {
        if (sign.equals("+")) return Operation.ADDITION;
        else if (sign.equals("-")) return Operation.SUBTRACTION;
        else if (sign.equals("*")) return Operation.MULTIPLICATION;
        else return Operation.DIVISION;
    }

    private static void validate(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            if (isSign(args[i])) {
                if (i == 0 || i == args.length - 1)
                    throw new Exception("Sign must be preceded by a number");
                if (isSign(args[i+1]))
                    throw new Exception("Cannot have consecutive signs");
            } else {
                Complex.parseComplex(args[i]);
            }
        }
    }


    private static ComplexExpression processArguments(String[] args) {
        Vector<Operation> addOperations = new Vector<Operation>();
        Vector<Operation> multiplyOperations = new Vector<Operation>();
        Vector<Complex> addOperands = new Vector<Complex>();
        Vector<Complex> multiplyOperands = new Vector<Complex>();

        Complex number;
        for (int i = 0; i < args.length; i++) {
            if (isAdditionSign(args[i])) {
                number = ExpressionFactory.getInstance()
                        .createExpression(multiplyOperations, multiplyOperands).execute();
                multiplyOperations.clear();
                multiplyOperands.clear();
                addOperations.add(getOperationBySign(args[i]));
                addOperands.add(number);
            } else if (isMultiplicationSign(args[i])) {
                multiplyOperations.add(getOperationBySign(args[i]));
            } else {
                multiplyOperands.add(Complex.parseComplex(args[i]));
            }
        }
        addOperands.add(
                ExpressionFactory.getInstance()
                    .createExpression(multiplyOperations, multiplyOperands).execute());

        return ExpressionFactory.getInstance().createExpression(addOperations, addOperands);
    }

    public static ComplexExpression parse(String[] args) {
        ExpressionFactory factory = ExpressionFactory.getInstance();
        try {
            ExpressionParser.validate(args);
        } catch (Exception e) {
            System.out.println("Error at validating expression: " + e.getMessage());
        }

        return processArguments(args);
    }
}
