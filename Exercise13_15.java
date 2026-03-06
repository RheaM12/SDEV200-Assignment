import java.math.BigInteger;
import java.util.Scanner;

public class Exercise13_15 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
        BigInteger n1 = input.nextBigInteger();
        BigInteger d1 = input.nextBigInteger();

        System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
        BigInteger n2 = input.nextBigInteger();
        BigInteger d2 = input.nextBigInteger();

        Rational r1 = new Rational(n1, d1);
        Rational r2 = new Rational(n2, d2);

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));

        System.out.println(r2 + " is " + r2.doubleValue());

        input.close();
    }
}

class Rational extends Number implements Comparable<Rational> {

    private BigInteger numerator;
    private BigInteger denominator;

    public Rational(BigInteger numerator, BigInteger denominator) {

        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        BigInteger gcd = numerator.gcd(denominator);

        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);

        if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
            this.numerator = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    public Rational add(Rational r2) {
        BigInteger n = numerator.multiply(r2.denominator)
                .add(denominator.multiply(r2.numerator));
        BigInteger d = denominator.multiply(r2.denominator);
        return new Rational(n, d);
    }

    public Rational subtract(Rational r2) {
        BigInteger n = numerator.multiply(r2.denominator)
                .subtract(denominator.multiply(r2.numerator));
        BigInteger d = denominator.multiply(r2.denominator);
        return new Rational(n, d);
    }

    public Rational multiply(Rational r2) {
        BigInteger n = numerator.multiply(r2.numerator);
        BigInteger d = denominator.multiply(r2.denominator);
        return new Rational(n, d);
    }

    public Rational divide(Rational r2) {
        BigInteger n = numerator.multiply(r2.denominator);
        BigInteger d = denominator.multiply(r2.numerator);
        return new Rational(n, d);
    }

    public int compareTo(Rational r2) {
        BigInteger first = numerator.multiply(r2.denominator);
        BigInteger second = denominator.multiply(r2.numerator);
        return first.compareTo(second);
    }

    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int intValue() {
        return (int) doubleValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }
}