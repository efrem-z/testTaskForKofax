import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpressionReader {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String arithmeticExpression;
    private String x;
    private String y;
    private List<String> list = new ArrayList<>();

    public void read() {
        System.out.println("enter arithmetic expression");
        System.out.println("use only: 0-9,+-*/,x,y()");
        readArithmeticExpression();
        readX();
        readY();

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        arithmeticExpression=sb.toString();
//        System.out.println(arithmeticExpression);
        System.out.println(Evaluate.eval(arithmeticExpression));
    }

    private void readArithmeticExpression() {
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
    }


    private void readX() {
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
    }

    private void readY() {
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
    }



}
