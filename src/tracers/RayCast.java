package tracers;

import utilities.Ray;
import utilities.RgbColor;
import utilities.ShadeRec;
import world.World;

public class RayCast extends Tracer {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public RayCast(World w) {
        super(w);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public RgbColor trace(Ray ray) {

        ShadeRec sr = world.hitObjects(ray);

        if (sr.isHit) {
            sr.ray = ray;
            return sr.material.shade(sr);
        }

        return world.getBackgroundColor();
    }

    @Override
    public RgbColor trace(Ray ray, int depth) {
        return trace(ray);
    }

    @Override
    public RgbColor trace(Ray ray, Double tmin, int depth) {
        return null;
    }
}
