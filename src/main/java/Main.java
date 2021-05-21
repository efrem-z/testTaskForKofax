
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("enter arithmetic expression");
        System.out.println("use only: 0-9,+-*/,x,y()");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arithmeticExpression;
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
        System.out.println(Evaluate.eval(arithmeticExpression));


    }


}
