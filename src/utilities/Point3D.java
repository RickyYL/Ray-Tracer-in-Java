package utilities;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Point3D {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    public double x = 0;
    public double y = 0;
    public double z = 0;

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public Point3D() {
    }

    public Point3D(double a) {
        x = a; y = a; z = a;
    }

    public Point3D(double a, double b, double c) {
        x = a; y = b; z = c;
    }

    public Point3D(final Point3D p) {
        x = p.x; y = p.y; z = p.z;
    }

/*--------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------*/

    public Point3D neg() {
        return new Point3D(-x, -y, -z);
    }

    public Point3D add(Vector3D p) {
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public Point3D sub(Vector3D v) {
        return new Point3D(x - v.x, y - v.y, z - v.y);
    }

    public Vector3D sub(Point3D p) {
        return new Vector3D(x - p.x, y - p.y, z - p.y);
    }

    public Point3D mul(double a) {
        return new Point3D(x * a, y * a, z * a);
    }

    // TODO
    public double distance(Point3D p) {
        return this.sub(p).norm();
    }

    public double distanceSquare(Point3D p) {
        return this.sub(p).normSquare();
    }

/*--------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------*/

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
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

}