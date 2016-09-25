package tracers;

import utilties.Ray;
import utilties.RgbColor;
import utilties.ShadeRec;
import world.World;

/**
 * @author Yuanqi Li
 */
public class MultipleObjects {

    World world;

    public RgbColor trace(final Ray ray) {
        ShadeRec sr = world.hitBareBonesObjects(ray);
        return sr.isHit() ? sr.getColor() : world.getBackgroundColor();
    }
}
