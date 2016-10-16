package lights;

import utilities.Point3D;
import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public class PointLight extends Light {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private Point3D  location;
    private RgbColor color = RgbColor.WHITE;
    private double   scaleFactor = 1.0;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public PointLight(Point3D location) {
        this.location = location;
    }

    public PointLight(Point3D location, RgbColor color, double scaleFactor) {
        this.location = location;
        this.color = color;
        this.scaleFactor = scaleFactor;
    }

    public PointLight(boolean shadows, Point3D location, RgbColor color, double scaleFactor) {
        super(shadows);
        this.location = location;
        this.color = color;
        this.scaleFactor = scaleFactor;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented Methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return location.sub(sr.hitPoint).normalVector();
//        return sr.hitPoint.sub(location).normalVector();
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
