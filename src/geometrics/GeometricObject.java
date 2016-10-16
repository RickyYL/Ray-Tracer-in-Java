package geometrics;

import materials.Material;
import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;

public abstract class GeometricObject {

    Material material = null;

    public GeometricObject() {
    }

    public GeometricObject(Material material) {
        this.material = material;
    }

    public abstract boolean hit(final Ray ray, double tmin, ShadeRec sr);

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
