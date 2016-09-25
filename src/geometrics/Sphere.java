package geometrics;

import utilties.Ray;
import utilties.ShadeRec;

/**
 * Created by yuanqi on 9/24/16.
 */
public class Sphere extends GeometricObject {
    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec sr) {
        return false;
    }
}
