package samplers;

import utilities.Maths;
import utilities.Point2D;

public class Jittered extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Jittered(int numSamples) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2));
    }

    public Jittered(int numSamples, int numSets) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2), numSets);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {
        int n = (int)Math.sqrt(numSamples);
        for (int p = 0; p < numSets; p++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    squareSamples.add(
                            new Point2D((k + Maths.randDouble()) / n, (j + Maths.randDouble()) / n));
    }
}
