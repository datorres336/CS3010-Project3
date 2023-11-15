public class Main {
    public static void main(String[] args) {
        /*double value = 2; //DEBUG CODE
        System.out.println("Function one value: " + functionOne(value));
        double a = .36328125;
        double b = 0.36508344055813474;
        System.out.println("C value: " + (a+b)/2);
        System.out.println("Function C value: " + functionOne((a+b)/2));
        System.out.println("Relative percent error: " + relErrorPercent(a, b));

        double d = 160;
        System.out.println("Function two value: " + functionTwo(d));

        double debug = b - (functionOne(b)/functionOneDer(b));
        System.out.println("Newton Raphson c value: " + debug);*/

        
    }

    public static double[] rootMethod(double a, double b, int methodVal) {
        boolean errorGoalReached = false;
        double [] relativePercentError = new double[100];
        double fOfA = functionOne(a);
        double fOfB = functionOne(b);
        double c;
        if (methodVal == 0) {
            c = (a+b)/2; //bisection method c value;
        }
        else if (methodVal == 1) {
            c = (a * functionOne(b) - b * functionOne(a)) / (functionOne(b) - functionOne(a)); //false position
            // method c value;
        }
        else if (methodVal == 2) {
            c = b - (functionOne(b)/functionOneDer(b)); //Newton Raphson method, you only need the b value, TAKE CARE OF THIS METHOD ALL IN THIS
            // BLOCK********
            for (int i = 0; i < 100 ; i++) {
                relativePercentError[i] = relErrorPercent(c, b);
                if (relativePercentError[i] < 1) {
                    errorGoalReached = true;
                    return relativePercentError;
                }
                c = b - (functionOne(b)/functionOneDer(b));
            }
        }
        else {
            c = b - functionOne(b) * ((b - a)/(functionOne(b) - functionOne(a))); //Secant method
        }
        double cOldVal;
        double fOfC = functionOne(c);

        for (int i = 0; i < 100; i++) {
            if ((fOfC < 0 && fOfA < 0) || (fOfC > 0 && fOfA > 0)) a = c;
            else b = c;
            cOldVal = c;
            if (methodVal == 0) {
                c = (a+b)/2; //bisection method c value;
            }
            else if (methodVal == 1) {
                c = (a * functionOne(b) - b * functionOne(a)) / (functionOne(b) - functionOne(a)); //false position
                // method c value;
            }
            else if (methodVal == 3) {
                c = b - functionOne(b) * ((b - a)/(functionOne(b) - functionOne(a))); //Secant method
            }

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

    /*public static double cValue (double a , double b) {
        return (a+b)/2;
    }*/

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


