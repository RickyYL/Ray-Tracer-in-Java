package samplers;

import utilities.Maths;
import utilities.Point2D;

/**
 * This sampler is more like a combination of Jittered and NRooks sampler. It has good 1D and 2D distribution. Should be
 * the first choice.
 */
public class MultiJittered extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public MultiJittered(int numSamples) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2));
    }

    public MultiJittered(int numSamples, int numSets) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2), numSets);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {

        int n = (int) Math.sqrt(numSamples);

        double subcellWidth = 1.0 / numSamples;
        for (int i = 0; i < numSamples * numSets; i++) {
            squareSamples.add(new Point2D());
        }

        for (int p = 0; p < numSets; p++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    squareSamples.set(i * n + j + p * numSamples, new Point2D(
                            (i * n + j) * subcellWidth + Maths.randDouble(subcellWidth),
                            (j * n + i) * subcellWidth + Maths.randDouble(subcellWidth)
                    ));
                }

        // TODO shuffle x and y coordinates
    }
}