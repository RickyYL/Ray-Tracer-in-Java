package brdfs;

import samplers.Sampler;
import utilities.*;

/**
 * @author Yuanqi Li
 */
public class Lambertian extends BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** The diffuse reflection coefficient in range [0, 1]. */
    private double kd;

    /** The diffuse color. */
    private RgbColor cd;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Lambertian(double kd, RgbColor cd) {
        this.kd = kd;
        this.cd = cd;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Returns rho<sub>d</sub>/pi.
     *
     * @param sr a shade record
     * @param wi incident radiance direction
     * @param wo outgoing radiance direction
     * @return   rho<sub>d</sub>/pi.
     */
    @Override
    public RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return cd.mul(kd).mul(Constants.invPI);
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
        return cd.mul(kd);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public double getDiffuseReflectionCoefficient() {
        return kd;
    }

    public void setDiffuseReflectionCoefficient(double kd) {
        this.kd = kd;
    }

    public RgbColor getDiffuseColor() {
        return cd;
    }

    public void setDiffuseColor(RgbColor cd) {
        this.cd = cd;
    }
}
