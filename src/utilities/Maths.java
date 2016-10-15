package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This is an idiot math library.
 */
public class Maths {

    public static final double EQN_EPS = 1e-90;

    private static Random randGen = new Random();

    public static int randInt() {
        return randGen.nextInt();
    }

    public static int randInt(int upperBound) {
        return randGen.nextInt(upperBound);
    }

    public static double randDouble() {
        return randGen.nextFloat();
    }

    public static double randDouble(double upperBound) {
        double randValue = randGen.nextFloat();
        while (randValue > upperBound)
            randValue = randGen.nextFloat();
        return randValue;
    }



    public boolean isZero(double x) {
        return x > -EQN_EPS && x < EQN_EPS;
    }

    // TODO
    public int solveQuadric(double[] c, double[] s) {
        return 0;
    }

    // TODO
    public int solveCubic(double[] c, double[] s) {
        return 0;
    }

    // TODO
    public int solveQuartic(double[] c, double[] s) {
        return 0;
    }

}