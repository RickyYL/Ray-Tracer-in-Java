package materials;

import brdfs.Lambertian;
import lights.Light;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public class Matte extends Material {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private Lambertian ambientBRDF;
    private Lambertian diffuseBRDF;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Matte(double ambientKd, double diffuseKd, RgbColor color) {
        super();
        this.ambientBRDF = new Lambertian(ambientKd, color);
        this.diffuseBRDF = new Lambertian(diffuseKd, color);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public RgbColor shade(ShadeRec sr) {

        Vector3D wo = sr.ray.getDirection().neg();
        RgbColor L = ambientBRDF.rho(sr, wo).mul(sr.world.getAmbient().irradiance(sr));

        for (Light l : sr.world.getLights()) {
            Vector3D wi = l.getDirection(sr);
            if (sr.hitNormal.mul(wi) > 0.0)
                L = L.add(diffuseBRDF.f(sr, wo, wi).mul(l.irradiance(sr)).mul(sr.hitNormal.mul(wi)));
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

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public void setKa(double ka) {
        ambientBRDF.setDiffuseReflectionCoefficient(ka);
    }

    public void setKd(double kd) {
        diffuseBRDF.setDiffuseReflectionCoefficient(kd);
    }

    public void setCd(RgbColor k) {
        ambientBRDF.setDiffuseColor(k);
        diffuseBRDF.setDiffuseColor(k);
    }

    public void setCd(double r, double g, double b) {
        ambientBRDF.setDiffuseColor(new RgbColor(r, g, b));
        diffuseBRDF.setDiffuseColor(new RgbColor(r, g, b));
    }
}
