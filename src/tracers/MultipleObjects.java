package tracers;

import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class MultipleObjects extends Tracer {

    public MultipleObjects(World w) {
        super(w);
    }

    @Override
    public RgbColor trace(Ray ray) {
        ShadeRec sr = world.hitBareBonesObjects(ray);
        if (sr.isHit)
            return sr.color;
        else
            return world.getBackgroundColor();
    }

    @Override
    public RgbColor trace(Ray ray, int depth) {
        return null;
    }

    @Override
    public RgbColor trace(Ray ray, Double tmin, int depth) {
        return null;
    }
}
