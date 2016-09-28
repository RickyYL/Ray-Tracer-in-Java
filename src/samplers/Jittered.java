package samplers;

import utilties.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Jittered extends Sampler {

    public Jittered(int numSamples) {
        super(numSamples);
    }

    @Override
    public void generateSamples() {
        int n = (int)Math.sqrt(numSamples);
        for (int p = 0; p < numSets; p++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++) {
                    Point2D sp = new Point2D((k + Math.random()) / n, (j + Math.random()) / n);
                    squareSamples.add(sp);
                }
    }
}
