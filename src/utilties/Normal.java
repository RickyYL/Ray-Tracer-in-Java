package utilties;

/**
 * @author Yuanqi Li
 * @version 0.1
 */
public class Normal {

    double x = 0, y = 0, z = 0;

    public Normal(double a) { x = a; y = a; z = a; }
    public Normal(double a, double b, double c) { x = a; y = b; z = c; }
    public Normal(final Normal n) {x = n.x; y = n.y; z = n.z; }
    public Normal(final Vector3D v) {x = v.x; y = v.y; z = v.z; }

    public Normal   neg() {
        return new Normal(-x, -y, -z);
    }
    public Normal   add(final Normal n) {
        return new Normal(x + n.x, y + n.y, z + n.z);
    }
    public Vector3D add(final Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }
    public double   dotProduct(final Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }
    public Normal   scalarProduct(double a) {
        return new Normal(x * a, y * a, z * a);
    }
    public Normal   normalize() {
        double norm = Math.pow(x * x + y * y + z * z, 0.5);
        x = x / norm;
        y = y / norm;
        z = z / norm;
        return this;
    }

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
