package utilities;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Ray {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private Point3D  origin;
    private Vector3D direction;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Ray() {
        this.origin = new Point3D(0, 0, 0);
        this.direction = new Vector3D(0, 0, -1);
    }

    public Ray(Point3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    public Ray(Ray r) {
        this.origin = r.origin;
        this.direction = r.direction.normalize();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (origin != null ? !origin.equals(ray.origin) : ray.origin != null) return false;
        return direction != null ? direction.equals(ray.direction) : ray.direction == null;
    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ray{" + "origin=" + origin + ", direction=" + direction + '}';
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point3D getOrigin() {
        return origin;
    }

    public Ray setOrigin(Point3D origin) {
        this.origin = origin;
        return this;
    }

    public Ray setOrigin(double x, double y, double z) {
        this.origin = new Point3D(x, y, z);
        return this;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public Ray setDirection(Vector3D direction) {
        this.direction = direction.normalize();
        return this;
    }

    public Ray setDirection(double x, double y, double z) {
        this.direction = new Vector3D(x, y, z).normalize();
        return this;
    }
}
