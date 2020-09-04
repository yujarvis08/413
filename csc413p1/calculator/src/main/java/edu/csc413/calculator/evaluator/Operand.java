package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    /**
     * construct operand from string token.
     */
    private int conversion;

    public Operand(String token) {
        for (int i = 0; i < token.length(); i++) {
            conversion = Integer.parseInt(token);
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        if (value == (int) value) {
            conversion = value;
        }
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return this.conversion;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        return token.matches("\\d+");
    }
}
