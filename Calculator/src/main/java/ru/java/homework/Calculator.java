package ru.java.homework;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Calculator {

    /**
     * translate string to ArrayList contained expression in polish notation
     *
     * @param expression string contained expression for translation
     * @param operands   adjuvant Stack
     * @return ArrayList with expression in polish notation
     * @throws CalculatorWrongExpressionException if expression was wrong
     */
    public static ArrayList<Integer> toReversePolishNotation(@NotNull String expression,
                                                             @NotNull Stack<Character> operands)
            throws CalculatorWrongExpressionException {
        ArrayList<Integer> st = new ArrayList<>();
        try {
            int i = 0;
            while (i < expression.length()) {
                char c = expression.charAt(i);
                if (c == '+' || c == '-' || c == '*' || c == '\\' || c == '(' || c == ')') {
                    if (c == '(') {
                        operands.push('(');
                        i++;
                        continue;
                    }
                    if (c == ')') {
                        char onTheTop = operands.top();
                        while (onTheTop != '(' && !operands.empty()) {
                            st.add((int) operands.top());
                            operands.pop();
                        }
                    } else {
                        if (!operands.empty()) {
                            char onTheTop = operands.top();
                            while (priority(onTheTop) >= priority(c) && !operands.empty()) {
                                onTheTop = operands.top();
                                if (onTheTop != '(')
                                    st.add((int) onTheTop);
                                operands.pop();
                            }
                            if (!operands.empty() && onTheTop == '(')
                                operands.pop();
                        }
                        operands.push(c);
                    }
                } else {
                    int val = c - '0';
                    while (i + 1 < expression.length()
                            && expression.charAt(i + 1) <= '9' && expression.charAt(i + 1) >= '0')
                        val = val * 10 + expression.charAt(++i) - '0';
                    st.add(val);
                }
                i++;
            }
            while (!operands.empty()) {

                st.add((int) operands.top());

                operands.pop();
            }
        } catch (Stack.EmptyStackException e) {
            throw new CalculatorWrongExpressionException("Wrong expression parse!");
        }
        return st;
    }

    /**
     * Evaluate expression in polish notation
     *
     * @param expr     ArrayList contained expression in polish notation
     * @param operands adjuvant Stack
     * @return result of evaluation
     * @throws CalculatorWrongExpressionException if expr was wrong
     */
    public static int EvaluateExpression(@NotNull ArrayList<Integer> expr,
                                         @NotNull Stack<Integer> operands)
            throws CalculatorWrongExpressionException {
        try {
            for (int i = 0; i < expr.size(); i++) {
                int op = expr.get(i);
                if (op != '+' && op != '-' && op != '/' && op != '*')
                    operands.push(expr.get(i));
                else {
                    int arg1 = operands.top();
                    operands.pop();
                    int arg2 = operands.top();
                    operands.pop();
                    operands.push(eval(arg1, arg2, expr.get(i)));
                }
            }
            return operands.top();
        } catch (Stack.EmptyStackException e) {
            throw new CalculatorWrongExpressionException("Wrong evaluation!");
        }
    }

    /**
     * take string and evaluate expression with use of polish notation
     *
     * @param characterStack adjuvant Stack
     * @param integerStack   adjuvant Stack
     * @param expr           expression for evaluate
     * @return result of evaluation
     * @throws CalculatorWrongExpressionException if something wrong is contained in input expr
     */
    public static int EvaluateAll(@NotNull Stack<Character> characterStack,
                                  @NotNull Stack<Integer> integerStack, @NotNull String expr)
            throws CalculatorWrongExpressionException {
        return EvaluateExpression(toReversePolishNotation(expr, characterStack), integerStack);
    }

    private static int priority(char c) {
        if (c == '+' || c == '-')
            return 2;
        if (c == '*' || c == '\\')
            return 3;
        if (c == '(')
            return 0;
        if (c == ')')
            return 0;
        return 4;
    }

    private static int eval(int arg1, int arg2, int op) {
        if (op == '+')
            return arg1 + arg2;
        if (op == '-')
            return arg2 - arg1;
        if (op == '*')
            return arg1 * arg2;
        if (op == '/')
            return arg2 / arg1;
        return 0;
    }

    public static class CalculatorWrongExpressionException extends Exception {
        CalculatorWrongExpressionException(String m) {
            super(m);
        }
    }
}
