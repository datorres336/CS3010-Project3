import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the first starting value for the methods to use on function 1: ");
        double a = Double.parseDouble(scanner.nextLine());
        System.out.println("Please choose the second starting value for the methods to use on function 1: ");
        double b = Double.parseDouble(scanner.nextLine());

        bisectionMethod(a,b);
        falsePositionMethod(a, b);
        newtonRaphsonMethod(a,b);
        secantMethod(a,b);

        System.out.println("Please choose the first starting value for the methods to use on function 2: ");
        double c = Double.parseDouble(scanner.nextLine());
        System.out.println("Please choose the second starting value for the methods to use on function 2: ");
        double d = Double.parseDouble(scanner.nextLine());

        bisectionMethodF2(c,d);
        falsePositionMethodF2(c, d);
        newtonRaphsonMethodF2(c,d);
        secantMethodF2(c,d);
    }

    public static double[] bisectionMethod(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionOne(a);
        double fOfB = functionOne(b);
        double c = (a+b)/2;
        double cOldVal;
        double fOfC = functionOne(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = (a+b)/2;
            fOfC = functionOne(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "bisectFunction1_R34.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }

    public static double[] falsePositionMethod(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionOne(a);
        double fOfB = functionOne(b);
        double c = (a * functionOne(b) - b * functionOne(a)) / (functionOne(b) - functionOne(a));
        double cOldVal;
        double fOfC = functionOne(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = (a * functionOne(b) - b * functionOne(a)) / (functionOne(b) - functionOne(a));
            fOfC = functionOne(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "falsePosFunction1_R34.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();
            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }

    public static double[] newtonRaphsonMethod(double a, double b) { //still need to debug this method
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double c = b - (functionOne(b)/functionOneDer(b));
        for (int i = 0; i < 100 ; i++) {
            relativePercentError[i] = relErrorPercent(c, b);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
            b = c;
            c = b - (functionOne(b)/functionOneDer(b));
        }
        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "newtonRaFunction1_R34.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }
    public static double[] secantMethod(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionOne(a);
        double fOfB = functionOne(b);
        double c = b - functionOne(b) * ((b - a)/(functionOne(b) - functionOne(a))); //Secant method
        double cOldVal;
        double fOfC = functionOne(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = b - functionOne(b) * ((b - a)/(functionOne(b) - functionOne(a))); //Secant method
            fOfC = functionOne(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "secantFunction1_R34.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }

    //**************************************************** CODE FOR FUNCTION 2 **************************************
    //***************************************************************************************************************
    public static double[] bisectionMethodF2(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionTwo(a);
        double fOfB = functionTwo(b);
        double c = (a+b)/2;
        double cOldVal;
        double fOfC = functionTwo(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = (a+b)/2;
            fOfC = functionTwo(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "bisectFunction2.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }

    public static double[] falsePositionMethodF2(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionTwo(a);
        double fOfB = functionTwo(b);
        double c = (a * functionTwo(b) - b * functionTwo(a)) / (functionTwo(b) - functionTwo(a));
        double cOldVal;
        double fOfC = functionTwo(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = (a * functionTwo(b) - b * functionTwo(a)) / (functionTwo(b) - functionTwo(a));
            fOfC = functionTwo(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "falsePosFunction2.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }

    public static double[] newtonRaphsonMethodF2(double a, double b) { //still need to debug this method
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double c = b - (functionTwo(b)/functionTwoDer(b));
        for (int i = 0; i < 100 ; i++) {
            relativePercentError[i] = relErrorPercent(c, b);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
            b = c;
            c = b - (functionTwo(b)/functionTwoDer(b));
        }
        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "newtonRaFunction2.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }
    public static double[] secantMethodF2(double a, double b) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionTwo(a);
        double fOfB = functionTwo(b);
        double c = b - functionTwo(b) * ((b - a)/(functionTwo(b) - functionTwo(a))); //Secant method
        double cOldVal;
        double fOfC = functionTwo(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            c = b - functionTwo(b) * ((b - a)/(functionTwo(b) - functionTwo(a))); //Secant method
            fOfC = functionTwo(c);

            relativePercentError[i] = relErrorPercent(c, cOldVal);
            if (relativePercentError[i] < 1) {
                errorGoalReached = true;
                break;
            }
        }

        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

        // export code
        String filePath = "secantFunction2.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            exportArrayToFile(relativePercentError, printWriter);
            printWriter.close();
            fileWriter.close();

            System.out.println("Relative error array exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePercentError;
    }
    //***************************************************************************************************************
    //***************************************************************************************************************

    public static double functionOne (double a) {
        return 2*Math.pow(a, 3) - 11.7*Math.pow(a,2) + 17.7*a -5;
    }
    public static double functionOneDer (double a) {
        return 6*Math.pow(a, 2) - 23.4*a + 17.7;
    }
    public static double functionTwo (double a) {
        return a + 10 - a * Math.cosh(50/a);
    }
    public static double functionTwoDer (double a) {
        return (50*Math.sinh(50/a))/a - Math.cosh(50/a) + 1;
    }

    public static double relErrorPercent(double a, double b) {
        return Math.abs(a-b)/Math.abs(a) * 100;
    }

    public static void exportArrayToFile(double[] array, PrintWriter printWriter) {
        for (double value : array) {
            printWriter.println(value);
        }
    }
}