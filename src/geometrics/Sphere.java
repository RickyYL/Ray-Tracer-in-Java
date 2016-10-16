package geometrics;

import materials.Material;
import utilities.*;

public class Sphere extends GeometricObject {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private Point3D center;
    private double  radius;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
        this.material = null;
    }

    public Sphere withMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Sphere(Point3D center, double radius, Material material) {
        super(material);
        this.center = center;
        this.radius = radius;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

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

        // Smaller root
        if (t > Constants.EPSILON) {
//            tmin = t;
            sr.t = t;
            sr.hitNormal = new Normal(temp.add(ray.getDirection().mul(t)).div(radius)).normalVector();
            sr.localHitPoint = ray.getOrigin().add(ray.getDirection().mul(t));
            return true;
        }

        t = (-b + e) / denom;

        // Larger root
        if (t > Constants.EPSILON) {
//            tmin = t;
            sr.t = t;
            sr.hitNormal = new Normal(temp.add(ray.getDirection().mul(t)).div(radius)).normalVector();
            sr.localHitPoint = ray.getOrigin().add(ray.getDirection().mul(t));
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

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
