package samplers;

import utilities.Maths;
import utilities.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class NRooks extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public NRooks(int numSamples) {
        super(numSamples);
    }

    public NRooks(int numSamples, int numSets) {
        super(numSamples, numSets);
    }

    /*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Note that in <code>NRooks</code> sampler, <code>numSamples</code> doesn't have to be
     * a perfect square because on an nxn grid, it only has n samples, instead of n^2.
     */
    @Override
    public void generateSamples() {
        for (int p = 0; p < numSets; p++) {
            for (int j = 0; j < numSamples; j++) {
                squareSamples.add(
                        new Point2D(j + Maths.randDouble(), j + Maths.randDouble()));
            }
        }
        shuffleXCoordinates();
        shuffleYCoordinates();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Private methods
\*--------------------------------------------------------------------------------------------------------------------*/

    // TODO there is going to be a better way to implement shuffle using JDK
    private void shuffleXCoordinates() {
        for (int p = 0; p < numSets; p++)
            for (int i = 0; i < numSamples - 1; i++) {
                int target = Maths.randInt() % numSamples + p * numSamples;
                double temp = squareSamples.get(i + p * numSamples + 1).x;
                squareSamples.get(i + p * numSamples + 1).x = squareSamples.get(target).x;
                squareSamples.get(target).x = temp;
            }
    }

    // TODO there is going to be a better way to implement shuffle using JDK
    private void shuffleYCoordinates() {
        for (int p = 0; p < numSets; p++)
            for (int i = 0; i < numSamples - 1; i++) {
                int target = Maths.randInt() % numSamples + p * numSamples;
                double temp = squareSamples.get(i + p * numSamples + 1).y;
                squareSamples.get(i + p * numSamples + 1).y = squareSamples.get(target).y;
                squareSamples.get(target).y = temp;
            }
    }
}
