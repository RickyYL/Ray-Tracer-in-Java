package lights;

import utilities.Point3D;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public class PointLight extends Light {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private double   scaleFactor = 1.0;
    private RgbColor color = RgbColor.WHITE;
    private Point3D  location;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public PointLight(Point3D location) {
        this.location = location;
    }

    public PointLight(double scaleFactor, RgbColor color, Point3D location) {
        this.scaleFactor = scaleFactor;
        this.color = color;
        this.location = location;
    }

    public PointLight(boolean shadows, double scaleFactor, RgbColor color, Point3D location) {
        super(shadows);
        this.scaleFactor = scaleFactor;
        this.color = color;
        this.location = location;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented Methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return location.sub(sr.hitPoint).normalVector();
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
