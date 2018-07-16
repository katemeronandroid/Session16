package com.emarkova.session16;


import java.util.Collections;
import java.util.Stack;

/**
 * Class computes the expression specified in the String format.
 * Provides only +,-,*,/ operations to be performed.
 * Does not support expressions with brackets.
 */
public class Calculator {
    Stack<Double> stack;
    Stack<Character> stackop;

    /**
     * Class constructor.
     */
    Calculator(){
        stack = new Stack<>();
        stackop = new Stack<>();
    }

    /**
     * Compute input expression.
     * @param command      the input expression
     * @return             Final result of computation in the String format.
     */
    String compute(String command) {
        String[] operands = command.split("[\\+\\-\\*\\/]");
        char[] operators = command.replaceAll("[0-9]", "").toCharArray();
        int i = 0;
        if(operands[i].equals(""))
            stack.push(0.0);
        else
            stack.push(Double.valueOf(operands[i]));
        while (i<operators.length) {
            Double tmp;
            char c = operators[i];
            switch(c) {
                case'+':
                    stack.push(Double.valueOf(operands[i+1]));
                    stackop.push(c);
                    break;
                case'-':
                    stack.push(Double.valueOf(operands[i+1]));
                    stackop.push(c);
                    break;
                case'*':
                    tmp = mul(stack.pop(), Double.valueOf(operands[i+1]));
                    stack.push(tmp);
                    break;
                case'/':
                    tmp = div(stack.pop(),Double.valueOf(operands[i+1]));
                    stack.push(tmp);
                    break;
                default:
                    break;
            }
            i++;
        }
        return getString(getSum().toString());
    }

    /**
     * Auxiliary function to compute sum/difference expression stored in the stack.
     * @return double result for sum/difference expression
     */
    private Double getSum() {
        Double result = 0.0;
        Collections.reverse(stack);
        Collections.reverse(stackop);
        while (!stackop.empty()) {
            Character c = stackop.pop();
            Double tmp = stack.pop();
            if(c.equals('+'))
                result = add(tmp,stack.pop());
            if(c.equals('-'))
                result = sub(tmp,stack.pop());
            stack.push(result);
        }
        result = stack.pop();
        return result;
    }

    /**
     * Compute sum of two numbers
     * @param op1    first operand
     * @param op2    second operand
     * @return      double sum of op1 and op2
     */
    Double add(Double op1, Double op2) {
        return op1 + op2;
    }

    /**
     * Compute difference between two numbers
     * @param op1    first operand
     * @param op2    second operand
     * @return      double difference between op1 and op2
     */
    Double sub(Double op1, Double op2) {
        return op1 - op2;
    }

    /**
     * Compute product of two numbers
     * @param op1    first operand
     * @param op2    second operand
     * @return      double product of op1 and op2
     */
    Double mul(Double op1, Double op2) {
        return op1*op2;
    }

    /**
     * Compute quotient of two numbers
     * @param op1    first operand
     * @param op2    second operand
     * @return      double quotient of op1 and op2
     */
    Double div (Double op1, Double op2) {
        if(op2.equals(0.0))
            throw new ArithmeticException();
        return op1/op2;
    }

    /**
     * Auxiliary function to present final string in a proper format for a viewer.
     * @param str String for formation.
     * @return    String without '.0' to present ingeter on the screen.
     */
    private String getString(String str){
        String[] tmp = str.split("\\.");
        if(tmp[1].equals("0"))
            return tmp[0];
        else
            return str;
    }
}
