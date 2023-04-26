import java.util.Scanner;

public class RationalNumber {
    private int numerator;
    private int denominator;

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public RationalNumber add(RationalNumber other) {
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);
        int newNumerator = numerator * lcd / denominator + other.numerator * lcd / other.denominator;
        return new RationalNumber(newNumerator, lcd);
    }

    public RationalNumber subtract(RationalNumber other) {
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);
        int newNumerator = numerator * lcd / denominator - other.numerator * lcd / other.denominator;
        return new RationalNumber(newNumerator, lcd);
    }

    public RationalNumber multiply(RationalNumber other) {
        int newNumerator = numerator * other.numerator;
        int newDenominator = denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    public RationalNumber divide(RationalNumber other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        int newNumerator = numerator * other.denominator;
        int newDenominator = denominator * other.numerator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }

    public RationalNumber abs() {
        return new RationalNumber(Math.abs(numerator), denominator);
    }

    public int compareTo(RationalNumber other) {
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);
        int thisNumerator = numerator * lcd / denominator;
        int otherNumerator = other.numerator * lcd / other.denominator;
        return Integer.compare(thisNumerator, otherNumerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.print("Enter first rational number (in the format a/b): ");
        String[] firstParts = scanner.nextLine().split("/");
        int firstNumerator, firstDenominator;
        try {
            firstNumerator = Integer.parseInt(firstParts[0]);
            firstDenominator = Integer.parseInt(firstParts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid input format");
            return;
        }
        RationalNumber first = new RationalNumber(firstNumerator, firstDenominator);

        System.out.print("Enter second rational number (in the format a/b): ");
        String[] secondParts = scanner.nextLine().split("/");
        int secondNumerator, secondDenominator;
        try {
            secondNumerator = Integer.parseInt(secondParts[0]);
            secondDenominator = Integer.parseInt(secondParts[1]);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid input format");
            return;
        }
        RationalNumber second = new RationalNumber(secondNumerator, secondDenominator);
        // Perform operations and display results
        System.out.println("First rational number: " + first);
        System.out.println("Second rational number: " + second);
        System.out.println("Sum: " + first.add(second));
        System.out.println("Difference: " + first.subtract(second));
        System.out.println("Product: " + first.multiply(second));
        try {
            System.out.println("Quotient: " + first.divide(second));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + " hello");
        }
        System.out.println("Absolute value of first: " + first.abs());
        System.out.println("Absolute value of second: " + second.abs());
        System.out.println("First as a floating point number: " + first.toDouble());
        System.out.println("Second as a floating point number: " + second.toDouble());
        System.out.println("Comparison result: " + first.compareTo(second));
    }
}