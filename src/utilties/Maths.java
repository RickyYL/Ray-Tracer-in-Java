package utilties;

import java.util.Random;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Maths {

    private static final double EQN_EPS = 1e-90;

    public static int randInt() {
        return new Random().nextInt();
    }

    public static float randFloat() {
        return new Random().nextFloat();
    }

    public static double randDouble() {
        return new Random().nextDouble();
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
