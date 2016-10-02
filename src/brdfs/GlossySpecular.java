package brdfs;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * Created by Yuanqi Li on 10/1/16.
 */
public class GlossySpecular extends BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return null;
    }

    /**
     * For the glossy specular BRDF, <code>wi</code> is determined by a cosine-power-weighted sample point on the
     * hemisphere that's oriented around the direction r of mirror reflection at the hit point p.
     */
    @Override
    public RgbColor fSample(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return null;
    }

    @Override
    public RgbColor rho(ShadeRec sr, Vector3D wo) {
        return RgbColor.BLACK;
    }
}
