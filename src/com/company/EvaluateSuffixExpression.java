package com.company;
import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateSuffixExpression {
    public static void main(String[] args) {
        EvaluateSuffixExpression s = new EvaluateSuffixExpression();
        String[] suffix = new String[]{"2","12","4","+","*"};
        System.out.println(s.evaluate(suffix));
    }

    public int evaluate(String[] suffix) {
        // Write your solution here
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : suffix) {
            if ("+-*/".indexOf(token) >= 0) {
                int nRight = stack.pollFirst();
                int nLeft = stack.pollFirst();
                int tmp = calculate(nLeft, nRight, token);
                stack.push(tmp);
            } else {
                stack.offerFirst(Integer.valueOf(token));
            }
        }
        return stack.peekFirst();
    }

    private int calculate(int nLeft, int nRight, String operator) {
        if (operator == "+") {
            return nLeft + nRight;
        } else if (operator == "-") {
            return nLeft - nRight;
        } else if (operator == "*") {
            return nLeft * nRight;
        } else {
            return nLeft / nRight;
        }
    }

    public int evaluateI(String[] suffix) {
        // Write your solution here
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : suffix) {
            if ("+-*/".indexOf(token) >= 0) {
                int nRight = stack.pollFirst();
                int nLeft = stack.pollFirst();
                switch (token) {
                    case "+": {
                        stack.push(nLeft + nRight);
                        break;
                    }
                    case "-": {
                        stack.push(nLeft - nRight);
                        break;
                    }
                    case "*": {
                        stack.push(nLeft * nRight);
                        break;
                    }
                    case "/": {
                        stack.push(nLeft / nRight);
                        break;
                    }
                }
                int tmp = calculate(nLeft, nRight, token);
                stack.push(tmp);
            } else {
                stack.offerFirst(Integer.valueOf(token));
            }
        }
        return stack.peekFirst();
    }

    public int evaluateII(String[] s) {
        // Write your solution here
        if (s == null || s.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length; i++) {
            if (isNumeric(s[i])) {
                stack.push(toNumber(s[i]));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int tmp = compute(a, b, s[i]);
                stack.push(tmp);
            }
        }
        return stack.peek();
    }

    private int toNumber(String str) {
        int times = 1;
        int result = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            result += times * (str.charAt(i) - '0');
            times *= 10;
        }
        return result;
    }

    private boolean isNumeric(String string) {
        if (string == null) return false;
        for (char c : string.toCharArray()) {
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    private int compute(int a, int b, String m) {
        if (m == "+") {
            return a + b;
        } else if (m == "-") {
            return a - b;
        } else if (m == "*") {
            return a * b;
        } else {
            return a / b;
        }
    }
}
