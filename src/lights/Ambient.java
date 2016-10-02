package lights;

import utilities.RgbColor;
import utilities.ShadeRec;
import utilities.Vector3D;

/**
 * @author Yuanqi Li
 */
public class Ambient extends Light {

    private double scaleFactor;
    private RgbColor color;

    public Ambient() {
        super();
        this.scaleFactor = 1.0;
        this.color = RgbColor.WHITE;
    }

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
