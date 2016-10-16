package geometrics;

import materials.Material;
import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;

public abstract class GeometricObject {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    Material material = null;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public GeometricObject() {
    }

    public GeometricObject(Material material) {
        this.material = material;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public abstract boolean hit(final Ray ray, double tmin, ShadeRec sr);

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
