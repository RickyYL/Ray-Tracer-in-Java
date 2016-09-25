package utilties;

import org.omg.PortableServer.POA;

/**
 * @author Yuanqi Li
 * @version 0.1
 */
public class Point3D {

    double x, y, z;

    public Point3D(double a) { x = a; y = a; z = a; }
    public Point3D(double a, double b, double c) { x = a; y = b; z = c; }
    public Point3D(final Point3D p) { x = p.x; y = p.y; z = p.z; }

    public Point3D negate() {
        return new Point3D(-x, -y, -z);
    }

    public Point3D add(final Point3D p) {
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public Point3D sub(final Vector3D v) {
        return new Point3D(x - v.x, y - v.y, z - v.y);
    }

    public Vector3D sub(final Point3D p) {
        return new Vector3D(x - p.x, y - p.y, z - p.y);
    }

    public Point3D mul(double a) {
        return new Point3D(x * a, y * a, z * a);
    }

    public double distance(final Point3D p) {
        return this.sub(p).norm();
    }

    public double distanceSquare(final Point3D p) {
        return this.sub(p).normSquare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3D point3D = (Point3D) o;

        if (Double.compare(point3D.x, x) != 0) return false;
        if (Double.compare(point3D.y, y) != 0) return false;
        return Double.compare(point3D.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
