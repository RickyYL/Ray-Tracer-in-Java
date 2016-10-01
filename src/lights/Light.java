package lights;

import utilties.RgbColor;
import utilties.ShadeRec;
import utilties.Vector3D;

/**
 * @author Yuanqi Li
 */
public abstract class Light {

    boolean shadows;

    public Light() {
        shadows = false;
    }

    public Light(boolean shadows) {
        this.shadows = shadows;
    }

    public abstract Vector3D getDirection(ShadeRec sr);
    public abstract RgbColor irradiance(ShadeRec sr);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        return shadows == light.shadows;
    }

    @Override
    public int hashCode() {
        return (shadows ? 1 : 0);
    }

    @Override
    public String toString() {
        return "Light{" +
                "shadows=" + shadows +
                '}';
    }
}
