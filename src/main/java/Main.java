
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("enter arithmetic expression");
        System.out.println("use only: 0-9,+-*/,x,y()");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arithmeticExpression = null;
        String x;
        String y;
        List<String> list = new ArrayList<>();

        //read from console arithmetic expression
        while (true) {
            try {
                arithmeticExpression = reader.readLine().replaceAll(" ", "");
                for (int i = 0; i < arithmeticExpression.length(); i++) {
                    if (String.valueOf(arithmeticExpression.charAt(i)).matches("[^0-9+*/()xy-]")) {
                        throw new IllegalArgumentException();
                    }
                }

                System.out.println("OK, Enter x");
                break;
            } catch (Exception e) {
                System.out.println("wrong arithmetic expression");
                System.out.println("use only: 0-9,+-*/,x,y()");
            }
        }


        // add symbols to list for find x and y
        for (int i = 0; i < arithmeticExpression.length(); i++) {
            list.add(String.valueOf(arithmeticExpression.charAt(i)));
        }

        //read x from console
        while (true) {
            try {
                x = reader.readLine();
                if (x.matches("[^0-9]")) {
                    throw new IllegalArgumentException();
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("x")) {
                            list.set(i, x);
                        }
                    }
                    System.out.println("OK, Enter y");
                    break;
                }
            } catch (Exception e) {
                System.out.println("wrong number");
                System.out.println("use only: 0-9");
            }
        }

        //read y from console
        while (true) {
            try {
                y = reader.readLine();
                if (y.matches("[^0-9]")) {
                    throw new IllegalArgumentException();
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("y")) {
                            list.set(i, y);
                        }
                    }
                    System.out.println("OK");
                    break;
                }
            } catch (Exception e) {
                System.out.println("wrong number");
                System.out.println("use only: 0-9");
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        arithmeticExpression=sb.toString();



        System.out.println(arithmeticExpression);
        System.out.println(eval(arithmeticExpression));


    }

    // evaluate a math expression given in string form
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
