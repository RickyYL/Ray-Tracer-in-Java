package materials;

import utilities.RgbColor;
import utilities.ShadeRec;

/**
 * @author Yuanqi Li
 */
public abstract class Material {

    public abstract RgbColor shade(ShadeRec sr);
    public abstract RgbColor shadeAreaLight(ShadeRec sr);
    public abstract RgbColor shadePath(ShadeRec st);
}
