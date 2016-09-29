package samplers;

import utilties.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Regular extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Regular(int numSamples) {
        super(numSamples);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {
        numSets = 1;    // no difference in regular sampler
        int n = (int)Math.sqrt(numSamples);
        for (int j = 0; j < n; j++)
            for (int k = 0; k < n; k++)
                squareSamples.add(new Point2D(k / n, j / n));
    }
}
