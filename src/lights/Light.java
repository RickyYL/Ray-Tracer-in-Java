package lights;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public abstract class Light {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    boolean shadows;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Light() {
        shadows = false;
    }

    public Light(boolean shadows) {
        this.shadows = shadows;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Abstract methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Returns the direction of incoming light at a hit point.
     *
     * @param sr the shading record
     * @return   the direction of incoming light at the hit point
     */
    public abstract Vector3D getDirection(ShadeRec sr);

    /**
     * Returns the incident radiance at a hit point.
     *
     * @param sr the shading record
     * @return   the incident radiance at a hit point
     */
    public abstract RgbColor irradiance(ShadeRec sr);

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Light{" +
                "shadows=" + shadows +
                '}';
    }
}
