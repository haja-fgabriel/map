public class Main {
    private static void testComplexNumber() {
        String expr = "2+3*i";
        String expr2 = "-2-i*7*34.97";
        String expr3 = "-2-2*i";
        String expr4 = "-2-2";

        System.out.println(Complex.parseComplex(expr));
        System.out.println(Complex.parseComplex(expr2));
        System.out.println(Complex.parseComplex(expr3));
        System.out.println(Complex.parseComplex(expr4));
    }

    private static void testComplexExpression() {
        /*String[] expr = new {"2+3*i", "-2-i*7*34.97", "-2-2*i", "-2-2"};
        ExpressionFactory.getInstance().createExpression(Operation.ADDITION, expr);*/

    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        System.out.println(ExpressionParser.parse(args).execute());

    }

}
