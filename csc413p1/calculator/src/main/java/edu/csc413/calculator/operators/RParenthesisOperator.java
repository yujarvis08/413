package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class RParenthesisOperator extends Operator {
    @Override
    public int priority() {
        return 4;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return null;
    }
}
