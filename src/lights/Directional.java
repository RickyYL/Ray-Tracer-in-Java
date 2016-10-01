package lights;

import utilties.RgbColor;
import utilties.ShadeRec;
import utilties.Vector3D;

/**
 * @author Yuanqi Li
 */
public class Directional extends Light {

    private double   scaleFactor;
    private RgbColor color;
    private Vector3D direction;

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return direction;
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
