package world;

import samplers.*;

/**
 * A ViewPlane is a rectangle screen on which pixels are colored by ray-tracing.
 */
public class ViewPlane {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** Horizontal resolution. */
    public int hres;

    /** Vertical resolution. */
    public int vres;

    /** Pixel size. */
    public double s = 1.0;

    /** Gamma value, default set to 1. */
    private double gamma = 1.0;

    /** Inverse of gamma value. */
    private double invGamma = 1.0;

    /** Number of sample points on each pixel. Usually set by the value provided by a sampler. */
    private int numSamples;

    /** The sampler used to generate sample points. */
    private Sampler sampler;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public ViewPlane(int hres, int vres) {
        this.hres = hres;
        this.vres = vres;
        this.sampler = new Regular(1);
        this.numSamples = 1;
    }

    public ViewPlane(int hres, int vres, double s, Sampler sampler) {
        this.hres = hres;
        this.vres = vres;
        this.s = s;
        this.sampler = sampler;
        this.numSamples = sampler.getNumSamples();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public double getGamma() {
        return gamma;
    }

    public void setGamma(float gamma) {
        this.gamma = gamma;
        this.invGamma = 1 / gamma;
    }

    public double getInvGamma() {
        return invGamma;
    }

    public int getNumSamples() {
        return numSamples;
    }

    /**
     * Set the number of sample points. Note this method specifies multi-jittered sampling
     * by default, and when the number of samples is one, it specifies regular sampling,
     * which puts one sample point in the center of each pixel. This method will be used
     * more frequently than <code>setSampler</code>.
     * @param n number of sample points
     */
    void setNumSamples(int n) {
        this.numSamples = n;
        if (numSamples > 1)
            this.sampler = new MultiJittered(numSamples);
        else
            this.sampler = new Regular(1);
    }

    public Sampler getSampler() {
        return sampler;
    }

    void setSampler(Sampler sp) {
        this.sampler = sp;
        this.numSamples = sp.getNumSamples();
    }
}
