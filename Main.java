public class Main {
    public static void main(String[] args) {
        double a = 0;
        double b = 1;
        double c = b - (functionOne(b)/functionOneDer(b)); //debug code
        double fOfC = functionOne(c);
        System.out.printf("C value = %f: \nf(c) = %f: \n", c , fOfC);
        //bisectionMethod(a,b); //WORKS!!!!!!
        //falsePositionMethod(a, b); //WORKS!!!!!
        //newtonRaphsonMethod(a,b); //WORKS!!!!!
        //secantMethod(a,b); //WORKS!!!!!
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
                return relativePercentError;
            }
            b = c;
            c = b - (functionOne(b)/functionOneDer(b));
        }
        if (errorGoalReached == false) System.out.println("Sorry solution did not converge to desired relative " +
                "percent error within 100 iterations. ");

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

        return relativePercentError;
    }

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
}

/*
* *********************************************************************CHAT GPT CODE
* public class BisectionMethod {

    // Define the function for which roots need to be found
    public static double function(double x) {
        // Replace this with your actual function
        return x * x - 4;
    }

    // Bisection method implementation
    public static double bisectionMethod(double a, double b, double tolerance) {
        if (function(a) * function(b) >= 0) {
            throw new IllegalArgumentException("The function values at the provided points must have different signs.");
        }

        double c;

        do {
            // Find the midpoint
            c = (a + b) / 2;

            // Check if the root is found or if the interval is small enough
            if (Math.abs(function(c)) < tolerance) {
                break;
            }

            // Update the interval based on the sign of the function at the midpoint
            if (function(c) * function(a) < 0) {
                b = c;
            } else {
                a = c;
            }

        } while (b - a > tolerance);

        return c;
    }

    public static void main(String[] args) {
        // Example usage
        double a = 0; // Left endpoint of the interval
        double b = 2; // Right endpoint of the interval
        double tolerance = 0.0001; // Tolerance or acceptable error

        double root = bisectionMethod(a, b, tolerance);

        System.out.println("Approximate root: " + root);
    }
}

* */


