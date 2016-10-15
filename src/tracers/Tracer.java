package tracers;

import utilities.Ray;
import utilities.RgbColor;
import world.World;

public abstract class Tracer {

    World world;

    public Tracer() {
        world = null;
    }

    public Tracer(World w) {
        world = w;
    }

    public abstract RgbColor trace(Ray ray);

    public abstract RgbColor trace(Ray ray, int depth);

    public abstract RgbColor trace(Ray ray, Double tmin, int depth);
}
