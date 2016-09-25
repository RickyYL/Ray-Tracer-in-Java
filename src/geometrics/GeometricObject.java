package geometrics;

import utilties.Ray;
import utilties.RgbColor;
import utilties.ShadeRec;

/**
 * Created by yuanqi on 9/24/16.
 */
public abstract class GeometricObject {

    abstract public boolean hit(final Ray ray, double tmin, ShadeRec sr);

    RgbColor color;

    public RgbColor getColor() {
        return color;
    }

    public void setColor(RgbColor color) {
        this.color = color;
    }
}
