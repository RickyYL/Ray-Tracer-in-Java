package lights;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public class Ambient extends Light {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private double scaleFactor = 1.0;
    private RgbColor color = RgbColor.WHITE;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Ambient() {
        super();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * For ambient light, there is no direction involved, hence it is not called anywhere.
     */
    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return new Vector3D(0);
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
