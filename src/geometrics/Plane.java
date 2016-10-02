package geometrics;

import utilities.*;

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
        double t = point.sub(ray.getOrigin()).mul(normal) / (ray.getDirection().mul(normal));
        if (t > Constants.kEpsilon) {
            tmin = t;
            sr.setNormal(normal);
            sr.setHitPoint(ray.getOrigin().add(ray.getDirection().mul(t)));
            return true;
        } else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (point != null ? !point.equals(plane.point) : plane.point != null) return false;
        return normal != null ? normal.equals(plane.normal) : plane.normal == null;

    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (normal != null ? normal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "point=" + point +
                ", normal=" + normal +
                '}';
    }
}
