package world;

/**
 * @author Yuanqi Li
 */
public class ViewPlane {

    int hres;
    int vres;
    float pixelSize;
    float gamma;
    float invGamma;

    public ViewPlane(int hres, int vres, float pixelSize, float gamma) {
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

    public float getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(float pixelSize) {
        this.pixelSize = pixelSize;
    }

    public float getGamma() {
        return gamma;
    }

    public void setGamma(float gamma) {
        this.gamma = gamma;
    }

    public float getInvGamma() {
        return invGamma;
    }

    public void setInvGamma(float invGamma) {
        this.invGamma = invGamma;
    }
}
