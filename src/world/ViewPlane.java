package world;

import samplers.*;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class ViewPlane {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    public int       hres;
    public int       vres;
    public double    pixelSize;

    private double   gamma;
    private double   invGamma;
    private int      numSamples;
    private Sampler  sampler;

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public ViewPlane(int hres, int vres) {
        this.hres = hres;
        this.vres = vres;
        this.pixelSize = 1.0;
        this.gamma = 1.0;
        this.invGamma = 1.0;
        this.numSamples = 1;
        this.sampler = new Regular(1);
    }

    public ViewPlane(int hres, int vres, double pixelSize, double gamma, int numSamples, Sampler sampler) {
        this.hres = hres;
        this.vres = vres;
        this.pixelSize = pixelSize;
        this.gamma = gamma;
        this.invGamma = 1 / gamma;
        this.numSamples = numSamples;
        this.sampler = sampler;
    }

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

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
        numSamples = n;
        if (numSamples > 1)
            sampler = new MultiJittered(numSamples);
        else
            sampler = new Regular(1);
    }

    public Sampler getSampler() {
        return sampler;
    }

    void setSampler(Sampler sp) {
        numSamples = sp.getNumSamples();
        sampler = sp;
    }
}
