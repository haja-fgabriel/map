public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Complex number) {
        this.real = number.real;
        this.imaginary = number.imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public void add(Complex number) {
        this.real += number.real;
        this.imaginary += number.imaginary;
    }

    public void subtract(Complex number) {
        this.real -= number.real;
        this.imaginary -= number.imaginary;
    }

    public void multiply(Complex number) {
        double a = this.real;
        double b = this.imaginary;
        double c = number.real;
        double d = number.imaginary;

        this.real = (a * c - b * d);
        this.imaginary = (a * d + b * c);
    }

    public void divide(Complex divisor) {
        Complex localDivisor = new Complex(divisor);

        this.multiply(divisor.conjugate());
        localDivisor.multiply(divisor.conjugate());

        this.real /= localDivisor.real;
        this.imaginary /= localDivisor.real;
    }

    @Override
    public String toString() {
        String result = String.format("%.2f", this.real);

        if (this.imaginary < 0)
            result += " - " + String.format("%.2f", -this.imaginary) + "*i";
        else if (this.imaginary > 0)
            result += " + " + String.format("%.2f", this.imaginary) + "*i";
        return result;
    }

    public Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }

    private static double parseFactor(String expr) {
        //if (expr.indexOf('i') > 0)
        if (expr.charAt(0) == '*')
            return Double.parseDouble(expr.substring(1));
        else if (expr.charAt(0) == '/')
            return Math.pow(Double.parseDouble(expr.substring(1)), -1);
        else
            return Double.parseDouble(expr);
    }

    public static Complex parseComplex(String number) {
        double real = 0;
        double imaginary = 0;

        for (String coefficient : number.split("(?=\\b[+-])")) {

            boolean isImaginary = false;
            double sign = 1;
            double product = 1;
            for (String factor : coefficient.split("(?=\\b[*])")) {
                if (factor.indexOf('i') < 0) {
                    product *= parseFactor(factor);
                } else {
                    // case where we have the '-i' factor
                    if (factor.charAt(0) == '-')
                        sign = -1;
                    isImaginary = true;
                }
            }

            if (isImaginary) {
                imaginary += sign*product;
            } else {
                real += product;
            }
        }

        return new Complex(real, imaginary);
    }
}
