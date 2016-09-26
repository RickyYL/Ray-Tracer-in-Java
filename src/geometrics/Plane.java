package geometrics;

import utilties.*;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Plane extends GeometricObject {

    Point3D point;
    Normal normal;

    public Plane(Point3D point, Normal normal) {
        this.point = point;
        this.normal = normal;
    }

    public Plane(Point3D point, Normal normal, RgbColor color) {
        this.point = point;
        this.normal = normal;
        super.color = color;
    }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec sr) {
        double t = point.sub(ray.getOrigin()).dotProduct(normal) / (ray.getDirection().dotProduct(normal));
        if (t > Constants.kEpsilon) {
            tmin = t;
            sr.setNormal(normal);
            sr.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().scalarProduct(t)));
            return true;
        } else
            return false;
    }
}
