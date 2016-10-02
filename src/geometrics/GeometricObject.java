package geometrics;

import materials.Material;
import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public abstract class GeometricObject {

    RgbColor color = RgbColor.RED;
    Material material = null;

    public abstract boolean hit(final Ray ray, double tmin, ShadeRec sr);

    public RgbColor getColor() {
        return color;
    }

    public void setColor(RgbColor color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
