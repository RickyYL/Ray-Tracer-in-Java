package geometrics;

import utilties.*;

/**
 * @author Yuanqi Li
 * @version 0.1
 */
public class Sphere extends GeometricObject {

    Point3D center;
    double  radius;

    public Sphere(Point3D c, double r) { center = c; radius = r; }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec sr) {
        double   t;
        Vector3D temp = ray.getOrigin().sub(center);
        double   a    = ray.getDirection().dotProduct(ray.getDirection());
        double   b    = temp.scalarProduct(2.0).dotProduct(ray.getDirection());
        double   c    = temp.dotProduct(temp) - radius * radius;
        double   disc = b * b - 4.0 * a * c;

        if (disc < 0.0)
            return false;

        double e = Math.sqrt(disc);
        double denom = 2.0 * a;
        t = (-b - e) / denom;
        if (t > Constants.kEpsilon) {
            tmin = t;
            sr.setNormal(new Normal(temp.add(ray.getDirection().scalarProduct(t)).divide(radius)).normalize());
            sr.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().scalarProduct(t)));
            return true;
        }

        t = (-b + e) / denom;
        if (t > Constants.kEpsilon) {
            tmin = t;
            sr.setNormal(new Normal(temp.add(ray.getDirection().scalarProduct(t)).divide(radius)).normalize());
            sr.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().scalarProduct(t)));
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
