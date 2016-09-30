package samplers;

import utilties.Maths;
import utilties.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Hammersley extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Hammersley(int numSamples) {
        super(numSamples);
    }

    public Hammersley(int numSamples, int numSets) {
        super(numSamples, numSets);
    }

    /*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {
        for (int p = 0; p < numSets; p++) {
            for (int j = 0; j < numSamples; j++) {
                squareSamples.add(new Point2D(1 / j, phi(j)));
            }
        }
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Private methods
\*--------------------------------------------------------------------------------------------------------------------*/

    private static double phi(int j) {
        double x = 0.0, f = 0.5;
        while (j != 0) {
            x += f * (double) ((j & 1));
            j /= 2;
            f *= 0.5;
        }
        return x;
    }
}
