package utilties;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Maths {

    public static final double EQN_EPS    = 1e-90;

    boolean isZero(double x) {
        return x > -EQN_EPS && x < EQN_EPS;
    }

    // TODO
    int solveQuadric(double[] c, double[] s) {
        return 0;
    }

    // TODO
    int solveCubic(double[] c, double[] s) {
        return 0;
    }

    // TODO
    int solveQuartic(double[] c, double[] s) {
        return 0;
    }
}
