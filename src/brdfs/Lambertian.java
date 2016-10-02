package brdfs;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;
import utilities.Constants;

/**
 * @author Yuanqi Li
 */
public class Lambertian extends BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private double diffuseReflectionCoefficient;    // kd
    private RgbColor diffuseColor;                  // cd

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Lambertian(double diffuseReflectionCoefficient, RgbColor diffuseColor) {
        this.diffuseReflectionCoefficient = diffuseReflectionCoefficient;
        this.diffuseColor = diffuseColor;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return diffuseColor.mul(diffuseReflectionCoefficient).mul(Constants.invPI);
    }

    /**
     * For the Lambertian BRDF, <code>wi</code> is determined by a cosine-weighted sample point on the hemisphere above
     * the hit point p.
     */
    @Override
    public RgbColor fSample(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return null;
    }

    @Override
    public RgbColor rho(ShadeRec sr, Vector3D wo) {
        return diffuseColor.mul(diffuseReflectionCoefficient);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public double getDiffuseReflectionCoefficient() {
        return diffuseReflectionCoefficient;
    }

    public void setDiffuseReflectionCoefficient(double diffuseReflectionCoefficient) {
        this.diffuseReflectionCoefficient = diffuseReflectionCoefficient;
    }

    public RgbColor getDiffuseColor() {
        return diffuseColor;
    }

    public void setDiffuseColor(RgbColor diffuseColor) {
        this.diffuseColor = diffuseColor;
    }
}
