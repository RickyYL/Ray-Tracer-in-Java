package utilities;

/**
 * @author Yuanqi Li
 */
public class Normal {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    public double x;
    public double y;
    public double z;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Normal() {
    }

    public Normal(double a) {
        this.x = a;
        this.y = a;
        this.z = a;
    }
    public Normal(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Normal(Vector3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Normal(Normal n) {
        this.x = n.x;
        this.y = n.y;
        this.z = n.z;
    }

    public Normal copy() {
        return new Normal(x, y, z);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  General methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public Normal neg() {
        return new Normal(-x, -y, -z);
    }

    public Normal add(Normal n) {
        return new Normal(x + n.x, y + n.y, z + n.z);
    }

    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    public Normal mul(double a) {
        return new Normal(x * a, y * a, z * a);
    }

    public double mul(Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Normal normalVector() {
        double norm = Math.pow(x * x + y * y + z * z, 0.5);
        return new Normal(x / norm, y / norm, z / norm);
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

        Normal normal = (Normal) o;

        if (Double.compare(normal.x, x) != 0) return false;
        if (Double.compare(normal.y, y) != 0) return false;
        return Double.compare(normal.z, z) == 0;
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
        return "Normal{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
