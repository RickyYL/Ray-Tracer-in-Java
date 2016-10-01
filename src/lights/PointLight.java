package lights;

import utilties.Point3D;
import utilties.RgbColor;
import utilties.ShadeRec;
import utilties.Vector3D;

/**
 * @author Yuanqi Li
 */
public class PointLight extends Light {

    private double   scaleFactor;
    private RgbColor color;
    private Point3D  location;

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return location.sub(sr.getHitPoint()).normalize();
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
