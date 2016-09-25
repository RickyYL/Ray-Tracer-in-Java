package utilties;

/**
 * @author Yuanqi Li
 */
public class Ray {

    Point3D  origin;
    Vector3D direction;

    public Ray(Point3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(Ray r) {
        origin = r.origin;
        direction = r.direction;
    }

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

    public Point3D getOrigin() {
        return origin;
    }

    public void setOrigin(Point3D origin) {
        this.origin = origin;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
