package utilities;

/**
 * @author Yuanqi Li
 */
public class Vector3D {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    public double x;
    public double y;
    public double z;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3D(double a) {
        this.x = a;
        this.y = a;
        this.z = a;
    }
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Vector3D(Vector3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3D(Normal n) {
        this.x = n.x;
        this.y = n.y;
        this.z = n.z;
    }

    public Vector3D copy() {
        return new Vector3D(x, y, z);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  General methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public Vector3D neg() {
        return new Vector3D(-x, -y, -z);
    }

    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    public Vector3D sub(Vector3D v) {
        return new Vector3D(x - v.x, y - v.y, z - v.z);
    }

    public Vector3D mul(double a) {
        return new Vector3D(x * a, y * a, z * a);
    }

    public double mul(Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public double mul(Normal n)  {
        return x * n.x + y * n.y + z * n.z;
    }

    public Vector3D div(double a) {
        return new Vector3D(x / a, y / a, z / a);
    }

    public Vector3D cross(Vector3D v) {
        return new Vector3D(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
    }

    public Vector3D cross(Normal n) {
        return new Vector3D(y*n.z - z*n.y, z*n.x - x*n.z, x*n.y - y*n.x);
    }

    public double normal() {
        return Math.pow(x * x + y * y + z * z, 0.5);
    }

    public double normalSquare() {
        return x * x + y * y + z * z;
    }

    public Vector3D normalVector() {
        double norm = Math.pow(x * x + y * y + z * z, 0.5);
        return new Vector3D(x / norm, y / norm, z / norm);
    }

    public void normalize() {
        double norm = Math.pow(x * x + y * y + z * z, 0.5);
        x = x / norm;
        y = y / norm;
        z = z / norm;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

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
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
