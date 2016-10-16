package geometrics;

import materials.Material;
import utilities.*;

public class Plane extends GeometricObject {

    Point3D point;
    Normal normal;

    public Plane(Point3D point, Normal normal, Material material) {
        super(material);
        this.point = point;
        this.normal = normal;
    }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec sr) {
        double t = point.sub(ray.getOrigin()).mul(normal) / (ray.getDirection().mul(normal));
        if (t > Constants.EPSILON) {
            tmin = t;
            sr.hitNormal = normal;
            sr.hitPoint = ray.getOrigin().add(ray.getDirection().mul(t));
            return true;
        } else
            return false;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "point=" + point +
                ", length=" + normal +
                '}';
    }
}
