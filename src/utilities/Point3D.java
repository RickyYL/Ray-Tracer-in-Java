package utilities;

/**
 * @author Yuanqi Li
 */
public class Point3D {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    public double x;
    public double y;
    public double z;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3D(double a) {
        this.x = a;
        this.y = a;
        this.z = a;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Point3D(Vector3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Point3D(Normal n) {
        this.x = n.x;
        this.y = n.y;
        this.z = n.z;
    }

    public Point3D copy() {
        return new Point3D(x, y, z);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------------------------------------------------------------*/

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

    public double normal(Point3D p) {
        return this.sub(p).normal();
    }

    public double normalSquare(Point3D p) {
        return this.sub(p).normalSquare();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

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
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
