package lights;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

public class Directional extends Light {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private double   scaleFactor = 1.0;
    private RgbColor color = RgbColor.WHITE;
    private Vector3D direction;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Directional(Vector3D direction) {
        this.direction = direction;
    }

    public Directional(double scaleFactor, RgbColor color, Vector3D direction) {
        this.scaleFactor = scaleFactor;
        this.color = color;
        this.direction = direction;
    }

    public Directional(boolean shadows, double scaleFactor, RgbColor color, Vector3D direction) {
        super(shadows);
        this.scaleFactor = scaleFactor;
        this.color = color;
        this.direction = direction;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public Vector3D getDirection(ShadeRec sr) {
        return direction;
    }

    @Override
    public RgbColor irradiance(ShadeRec sr) {
        return color.mul(scaleFactor);
    }
}
