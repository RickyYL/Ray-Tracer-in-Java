package brdfs;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * Created by Yuanqi Li on 10/1/16.
 */
public class PerfectSpecular extends BRDF {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public RgbColor f(ShadeRec sr, Vector3D wi, Vector3D wo) {
        return RgbColor.BLACK;
    }

    /**
     * For the perfect specular BRDF, <code>wi</code> is the direction of mirror reflection at hit point p.
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
