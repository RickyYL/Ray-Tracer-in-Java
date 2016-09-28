package samplers;

import utilties.Maths;
import utilties.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class NRooks extends Sampler {

    public NRooks(int numSamples) {
        super(numSamples);
    }

    @Override
    public void generateSamples() {
        for (int p = 0; p < numSets; p++)
            for (int j = 0; j < numSamples; j++) {
                Point2D pv = new Point2D(j + Math.random(), j + Math.random());
                squareSamples.add(pv);
            }
        shuffleXCoordinates();
        shuffleYCoordinates();
    }

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
