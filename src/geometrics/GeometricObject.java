package geometrics;

import utilties.Ray;
import utilties.RgbColor;
import utilties.ShadeRec;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public abstract class GeometricObject {

    RgbColor color;

    abstract public boolean hit(final Ray ray, double tmin, ShadeRec sr);

    public RgbColor getColor() {
        return color;
    }
    public void setColor(RgbColor color) {
        this.color = color;
    }
}
