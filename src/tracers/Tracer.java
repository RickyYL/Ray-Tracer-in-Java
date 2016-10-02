package tracers;

import utilities.Ray;
import utilities.RgbColor;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public abstract class Tracer {

    World world = null;

    public Tracer() {
    }

    public Tracer(World w) {
        world = w;
    }

    public abstract RgbColor trace(final Ray ray);

    public abstract RgbColor trace(final Ray ray, final int depth);

    public abstract RgbColor trace(final Ray ray, Double tmin, final int depth);
}
