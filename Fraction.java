/*
 * COSC 1173 Programming Lab - Lab 12: Immutable Fraction Class
 * Textbook reference: Liang, Chapters 9-10 (Class Abstraction, Immutable Objects)
 *
 * Student name: [TYPE YOUR NAME HERE]
 * Date:         [TYPE TODAY'S DATE HERE]
 *
 * DO NOT change any member name, parameter list, or return type.
 * REQUIREMENT: every executable statement you write must carry a line comment.
 *
 * DESIGN NOTE - this class is IMMUTABLE. Both fields are final, there are no
 * setters, and every arithmetic method returns a NEW Fraction instead of
 * modifying this one. Immutable objects cannot be corrupted by other code.
 */
public class Fraction {

    private final int numerator;   // top of the fraction, carries the sign
    private final int denominator; // bottom of the fraction, always kept positive

    /**
     * STEP 1 - Constructor.
     * Requirements, in order:
     *   a) throw new IllegalArgumentException("Denominator cannot be zero") when denominator is 0.
     *   b) move a negative sign from the denominator to the numerator, so that
     *      1/-2 is stored as -1/2.
     *   c) reduce to lowest terms by dividing both parts by their greatest common divisor.
     *      6/8 must be stored as 3/4, and 0/5 must be stored as 0/1.
     * TODO: implement a), b), and c), then assign this.numerator and this.denominator.
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = 0;   // TODO: replace with the normalized numerator
        this.denominator = 1; // TODO: replace with the normalized denominator
    }

    /**
     * STEP 2 - A private helper that returns the greatest common divisor of |a| and |b|.
     * Helper methods are private because they are an implementation detail, not
     * part of the service this class offers to the outside world.
     */
    private static int gcd(int a, int b) {
        return 1; // placeholder
    }

    // STEP 3 - Accessors. TODO: return the matching field.
    public int getNumerator() { return 0; }
    public int getDenominator() { return 1; }

    /**
     * STEP 4 - Returns a NEW Fraction equal to this + other.
     * a/b + c/d = (a*d + c*b) / (b*d). The constructor reduces the result for you.
     */
    public Fraction add(Fraction other) {
        return null; // placeholder
    }

    /** STEP 5 - Returns a NEW Fraction equal to this - other:  (a*d - c*b) / (b*d). */
    public Fraction subtract(Fraction other) {
        return null; // placeholder
    }

    /** STEP 6 - Returns a NEW Fraction equal to this * other:  (a*c) / (b*d). */
    public Fraction multiply(Fraction other) {
        return null; // placeholder
    }

    /**
     * STEP 7 - Returns a NEW Fraction equal to this / other:  (a*d) / (b*c).
     * Dividing by a fraction whose numerator is 0 must throw
     * new IllegalArgumentException("Cannot divide by zero").
     */
    public Fraction divide(Fraction other) {
        return null; // placeholder
    }

    /** STEP 8 - Returns the value as a double: numerator divided by denominator. */
    public double doubleValue() {
        return 0.0; // placeholder
    }

    /**
     * STEP 9 - Returns "3/4" for three quarters, but just "5" when the denominator
     * is 1, because "5/1" is not how people write whole numbers.
     */
    @Override
    public String toString() {
        return ""; // placeholder
    }

    /**
     * STEP 10 - Two fractions are equal when both normalized parts match.
     * Return false when the argument is null or is not a Fraction.
     * TODO: use the instanceof operator, then cast, then compare both fields.
     */
    @Override
    public boolean equals(Object obj) {
        return false; // placeholder
    }

    /**
     * STEP 11 - A static factory method that builds a Fraction from text such as "3/4".
     * Use split("/") to divide the text, and Integer.parseInt to convert each piece.
     * Text with no slash, such as "5", represents 5/1.
     */
    public static Fraction parse(String text) {
        return null; // placeholder
    }
}
