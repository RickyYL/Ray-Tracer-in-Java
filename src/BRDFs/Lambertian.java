package BRDFs;

import utilties.RgbColor;
import utilties.ShadeRec;
import utilties.Vector3D;
import utilties.Constants;

/**
 * @author Yuanqi Li
 */
public class Lambertian extends BRDF {

    double   diffuseCoeff;
    RgbColor diffuseColor;

    public Lambertian(double diffuseCoeff, RgbColor diffuseColor) {
        this.diffuseCoeff = diffuseCoeff;
        this.diffuseColor = diffuseColor;
    }

    @Override
    public RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return diffuseColor.mul(diffuseCoeff).mul(Constants.invPI);
    }

    @Override
    public RgbColor sampleF(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return null;
    }

    @Override
    public RgbColor rho(ShadeRec sr, Vector3D wo) {
        return diffuseColor.mul(diffuseCoeff);
    }
}
