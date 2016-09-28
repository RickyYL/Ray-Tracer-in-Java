package samplers;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Hammersley extends Sampler {

    public Hammersley(int numSamples) {
        super(numSamples);
    }

    @Override
    public void generateSamples() {

    }

    private double phi(int j) {
        double x = 0.0, f = 0.5;
        while (j != 0) {
            x += f * (double) (!j & 1);
            j /= 2;
            f *= 0.5;
        }
        return x;
    }
}
