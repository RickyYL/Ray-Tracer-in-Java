package tracers;

import utilties.Ray;
import utilties.RgbColor;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.1
 */
public abstract class Tracer {

    World world = null;

    public Tracer() {}

    public Tracer(World w) {
        world = w;
    }

    public RgbColor trace(final Ray ray) {
        return RgbColor.BLACK;
    }
}
