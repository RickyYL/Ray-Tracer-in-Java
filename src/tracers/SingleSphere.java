package tracers;

import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class SingleSphere extends Tracer {

    public SingleSphere(World w) {
        super(w);
    }

    @Override
    public RgbColor trace(Ray ray) {
        ShadeRec sr = new ShadeRec(world);
        double   t = 0;
        if (world.getSphere().hit(ray, t, sr))
            return world.getSphere().getColor();
        else
            return RgbColor.BLACK;
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
