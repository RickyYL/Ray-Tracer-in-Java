package samplers;

import utilities.Point2D;

/**
 * The regular sampler distributes all sample points evenly over a pixel.
 */
public class Regular extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * The regular sampler requires the number of sample points is a square number. The method below ensures the number
     * is round to the nearest square number. In addition, in regular sampler, the number of sample sets is always 1,
     * which would be meaningless to have multiple regular sample sets.
     *
     * @param numSamples the number of samples the sampler will generate, should be a square number
     */
    public Regular(int numSamples) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2), 1);
    }

    public Regular(int numSamples, int numSets) {
        super((int)Math.pow((int)Math.sqrt(numSamples), 2), 1);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {
        int n = (int)Math.sqrt(numSamples);
        for (int j = 0; j < n; j++)
            for (int k = 0; k < n; k++)
                squareSamples.add(new Point2D((k + 0.5) / n, (j + 0.5) / n));
    }
}
