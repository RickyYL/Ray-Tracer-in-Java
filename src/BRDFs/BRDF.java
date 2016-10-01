package BRDFs;

import samplers.Sampler;
import utilties.RgbColor;
import utilties.ShadeRec;
import utilties.Vector3D;

/**
 * @author Yuanqi Li
 * @version 0.7
 */
public abstract class BRDF {

    Sampler sampler;

    /**
     * Returns the BRDF itself, unless it contains a delta function.
     */
    public abstract RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo);

    /**
     * Is used to compute the direction of reflected rays for simulating reflective materials
     * and diffuse-diffuse light transport.
     */
    public abstract RgbColor sampleF(ShadeRec sr, Vector3D wi, Vector3D wo);

    /**
     * Returns the bihemispherical reflectance rho_hh.
     */
    public abstract RgbColor rho(ShadeRec sr, Vector3D wo);
}
