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

    public int      hres;
    public int      vres;
    public double   pixelSize  = 1.0;

    public double   gamma      = 1.0;
    public double   invGamma   = 1.0;

    private int     numSamples = 1;
    private Sampler sampler    = new Regular(1);

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public ViewPlane(int hres, int vres) {
        this.hres = hres;
        this.vres = vres;
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

    public int getHres() {
        return hres;
    }

    public void setHres(int hres) {
        this.hres = hres;
    }

    public int getVres() {
        return vres;
    }

    public void setVres(int vres) {
        this.vres = vres;
    }

    public double getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(float pixelSize) {
        this.pixelSize = pixelSize;
    }

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
