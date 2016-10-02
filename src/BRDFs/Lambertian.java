package BRDFs;

import samplers.Sampler;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;
import utilities.Constants;

/**
 * @author Yuanqi Li
 */
public class Lambertian extends BRDF {

    private double   diffuseCoeff;
    private RgbColor diffuseColor;

    public Lambertian(Sampler sampler, double diffuseCoeff, RgbColor diffuseColor) {
        super(sampler);
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

    public double getDiffuseCoeff() {
        return diffuseCoeff;
    }

    public void setDiffuseCoeff(double diffuseCoeff) {
        this.diffuseCoeff = diffuseCoeff;
    }

    public RgbColor getDiffuseColor() {
        return diffuseColor;
    }

    public void setDiffuseColor(RgbColor diffuseColor) {
        this.diffuseColor = diffuseColor;
    }
}
