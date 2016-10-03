package lights;

import utilities.Point3D;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * @author Yuanqi Li
 */
public class PointLight extends Light {

    private double   scaleFactor;
    private RgbColor color;
    private Point3D  location;

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return location.sub(sr.hitPoint).normalVector();
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
