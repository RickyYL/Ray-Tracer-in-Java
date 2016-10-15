package brdfs;

import samplers.Sampler;
import utilities.Normal;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;


public abstract class BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    Sampler sampler;
    Normal  normal;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public BRDF() {
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
     * Is used to compute the direction of reflected rays for simulating reflective materials and diffuse-diffuse light
     * transport. The directions are computed by sampling the BRDF.
     */
    public abstract RgbColor fSample(ShadeRec sr, Vector3D wi, Vector3D wo);

    /**
     * Returns the bihemispherical reflectance rho<sub>hh</sub>.
     */
    public abstract RgbColor rho(ShadeRec sr, Vector3D wo);
}
