package utilties;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Vector3D {

    double x = 0, y = 0, z = 0;

    public Vector3D() { }
    public Vector3D(double a) { x = a; y = a; z = a; }
    public Vector3D(double a, double b, double c) { x = a; y = b; z = c; }
    public Vector3D(final Vector3D v) { x = v.x; y = v.y; z = v.z; }
    public Vector3D(final Normal n) { x = n.x; y = n.y; z = n.z; }
    public Vector3D(final Point3D p) { x = p.x; y = p.y; z = p.z; }

    public Vector3D negate() {
        return new Vector3D(-x, -y, -z);
    }
    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }
    public Vector3D sub(Vector3D v) {
        return new Vector3D(x - v.x, y - v.y, z - v.z);
    }
    public Vector3D scalarProduct(double a) {
        return new Vector3D(x * a, y * a, z * a);
    }
    public double   dotProduct(Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }
    public double   dotProduct(Normal n)  { return x * n.x + y * n.y + z * n.z; }
    public Vector3D crossProduct(Vector3D v) { return new Vector3D(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);}
    public Vector3D crossProduct(Normal n) { return new Vector3D(y*n.z - z*n.y, z*n.x - x*n.z, x*n.y - y*n.x);}
    public Vector3D divide(double a) {
        return new Vector3D(x / a, y / a, z / a);
    }

    public double norm() {
        return Math.pow(x * x + y * y + z * z, 0.5);
    }
    public double normSquare() {
        return x * x + y * y + z * z;
    }
    public Vector3D normalize() {
        double norm = norm();
        x = x / norm;
        y = y / norm;
        z = z / norm;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3D vector3D = (Vector3D) o;

        if (Double.compare(vector3D.x, x) != 0) return false;
        if (Double.compare(vector3D.y, y) != 0) return false;
        return Double.compare(vector3D.z, z) == 0;
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
