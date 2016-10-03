package geometrics;

import utilities.*;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Sphere extends GeometricObject {

    Point3D center;
    double  radius;

    public Sphere(Point3D c, double r) {
        center = c;
        radius = r;
    }

    public Sphere(Point3D c, double r, RgbColor co) {
        center = c;
        radius = r;
        color = co;
    }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec sr) {

        double   t;
        Vector3D temp = ray.getOrigin().sub(center);
        double   a    = ray.getDirection().mul(ray.getDirection());
        double   b    = temp.mul(2.0).mul(ray.getDirection());
        double   c    = temp.mul(temp) - radius * radius;
        double   disc = b * b - 4.0 * a * c;

        if (disc < 0.0)
            return false;

        double e = Math.sqrt(disc);
        double denom = 2.0 * a;
        t = (-b - e) / denom;
        if (t > Constants.EPSILON) {
            tmin = t;
            sr.setNormal(new Normal(temp.add(ray.getDirection().mul(t)).div(radius)).normalVector());
            sr.setHitPoint(ray.getOrigin().add(ray.getDirection().mul(t)));
            return true;
        }

        t = (-b + e) / denom;
        if (t > Constants.EPSILON) {
            tmin = t;
            sr.setNormal(new Normal(temp.add(ray.getDirection().mul(t)).div(radius)).normalVector());
            sr.setHitPoint(ray.getOrigin().add(ray.getDirection().mul(t)));
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.getRadius(), getRadius()) != 0) return false;
        return getCenter() != null ? getCenter().equals(sphere.getCenter()) : sphere.getCenter() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCenter() != null ? getCenter().hashCode() : 0;
        temp = Double.doubleToLongBits(getRadius());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
