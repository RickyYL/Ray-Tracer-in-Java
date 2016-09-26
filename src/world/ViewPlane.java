package world;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class ViewPlane {

    private int    hres;
    private int    vres;
    private double pixelSize;
    private double gamma;
    private double invGamma;
    private int    numSamples;

    public ViewPlane(int hres, int vres, double pixelSize, double gamma, int numSamples) {
        this.hres = hres;
        this.vres = vres;
        this.pixelSize = pixelSize;
        this.gamma = gamma;
        this.invGamma = 1 / gamma;
    }

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
    }

    public double getInvGamma() {
        return invGamma;
    }

    public void setInvGamma(float invGamma) {
        this.invGamma = invGamma;
    }
}
