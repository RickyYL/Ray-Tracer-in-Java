package tracers;

import utilties.Ray;
import utilties.RgbColor;
import utilties.ShadeRec;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.1
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
            return RgbColor.RED;
        else
            return RgbColor.BLACK;
    }
}
