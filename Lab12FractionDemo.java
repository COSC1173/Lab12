/*
 * COSC 1173 Programming Lab - Lab 12 driver program.
 * Student name: [TYPE YOUR NAME HERE]
 *
 * STEP 12 - TODO: exercise the Fraction class. Every statement needs a comment.
 */
public class Lab12FractionDemo {

    public static void main(String[] args) {

        Fraction half = new Fraction(1, 2);   // creates one half
        Fraction third = new Fraction(1, 3);  // creates one third

        System.out.println(half + " + " + third + " = " + half.add(third)); // expects 5/6

        // TODO: demonstrate subtract, multiply, divide, doubleValue, equals, and parse.
        // TODO: show that reduction works by printing new Fraction(6, 8).
    }
}
