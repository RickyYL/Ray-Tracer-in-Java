package materials;

import BRDFs.Lambertian;
import lights.Light;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * @author Yuanqi Li
 */
public class Matte extends Material {

    private Lambertian ambientBRDF;
    private Lambertian diffuseBRDF;

    public Matte() {
        super();
        this.ambientBRDF = new Lambertian();
        this.diffuseBRDF = new Lambertian();
    }

    public void setKa(double k) {
        ambientBRDF.setDiffuseCoeff(k);
    }

    public void setKd(double k) {
        diffuseBRDF.setDiffuseCoeff(k);
    }

    public void setCd(RgbColor k) {
        ambientBRDF.setDiffuseColor(k);
        diffuseBRDF.setDiffuseColor(k);
    }

    public void setCd(double r, double g, double b) {
        ambientBRDF.setDiffuseColor(new RgbColor(r, g, b));
        diffuseBRDF.setDiffuseColor(new RgbColor(r, g, b));
    }

    @Override
    public RgbColor shade(ShadeRec sr) {

        Vector3D wo = sr.ray.getDirection().neg();
        RgbColor L  = ambientBRDF.rho(sr, wo).mul(sr.world.getAmbient().irradiance(sr));
        int      numLights = sr.world.getLights().size();

        for (Light l : sr.world.getLights()) {
            Vector3D wi = l.getDirection(sr);
            double ndotwi = sr.normal.dotProduct(wi);
            if (ndotwi > 0.0)
                L = L.add(diffuseBRDF.f(sr, wo, wi).mul(l.irradiance(sr)).mul(ndotwi));
        }

        return L;
    }

    @Override
    public RgbColor shadeAreaLight(ShadeRec sr) {
        return null;
    }

    @Override
    public RgbColor shadePath(ShadeRec st) {
        return null;
    }
}
