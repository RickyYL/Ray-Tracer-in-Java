package brdfs;

import samplers.Sampler;
import utilities.Normal;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * @author Yuanqi Li
 */
public abstract class BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private Sampler sampler;
    private Normal  normal;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public BRDF() {
        sampler = null;
        normal = null;
    }

    public BRDF(Sampler sampler, Normal normal) {
        this.sampler = sampler;
        this.normal = normal;
    }

    public BRDF(BRDF f) {
        this.sampler = f.sampler;
        this.normal = f.normal;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  General methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Returns the BRDF itself, unless it contains a delta function.
     */
    public abstract RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo);

    /**
     * Is used to compute the direction of reflected rays for simulating reflective materials
     * and diffuse-diffuse light transport.
     */
    public abstract RgbColor fSample(ShadeRec sr, Vector3D wi, Vector3D wo);

    /**
     * Returns the bihemispherical reflectance rho_hh.
     */
    public abstract RgbColor rho(ShadeRec sr, Vector3D wo);
}
