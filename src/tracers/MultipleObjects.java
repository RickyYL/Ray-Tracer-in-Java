package tracers;

import utilties.Ray;
import utilties.RgbColor;
import utilties.ShadeRec;
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
        if (sr.isHit())
            return sr.getColor();
        else
            return world.getBackgroundColor();
    }
}
