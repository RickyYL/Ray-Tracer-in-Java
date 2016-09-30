package cameras;

import utilties.Point3D;
import utilties.Vector3D;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.9
 */
abstract public class Camera {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    Point3D  eye;
    Point3D  lookat;
    Vector3D up;
    Vector3D u, v, w;
    double   exposureTime = 1.0;

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public Camera(Point3D eye, Point3D lookat) {
        this.eye = eye;
        this.lookat = lookat;
    }

    public Camera(Point3D eye, Point3D lookat, Vector3D up) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
    }

    public Camera(Point3D eye, Point3D lookat, Vector3D up, Vector3D u, Vector3D v, Vector3D w) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Camera(Point3D eye, Point3D lookat, Vector3D up, Vector3D u, Vector3D v, Vector3D w, double exposureTime) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
        this.u = u;
        this.v = v;
        this.w = w;
        this.exposureTime = exposureTime;
    }

/*--------------------------------------------------------------*\
 *  Abstract methods
\*--------------------------------------------------------------*/

    abstract public void renderScene(World w);

/*--------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------*/

    /**
     * Computes the uvw-coordinate, aka, the camera coordinate.
     */
    public void uvw() {
        w = eye.sub(lookat).normalize();
        u = up.crossProduct(w).normalize();
        v = w.crossProduct(u).normalize();
    }

/*--------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (Double.compare(camera.exposureTime, exposureTime) != 0) return false;
        if (eye != null ? !eye.equals(camera.eye) : camera.eye != null) return false;
        if (lookat != null ? !lookat.equals(camera.lookat) : camera.lookat != null) return false;
        if (up != null ? !up.equals(camera.up) : camera.up != null) return false;
        if (u != null ? !u.equals(camera.u) : camera.u != null) return false;
        if (v != null ? !v.equals(camera.v) : camera.v != null) return false;
        return w != null ? w.equals(camera.w) : camera.w == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = eye != null ? eye.hashCode() : 0;
        result = 31 * result + (lookat != null ? lookat.hashCode() : 0);
        result = 31 * result + (up != null ? up.hashCode() : 0);
        result = 31 * result + (u != null ? u.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (w != null ? w.hashCode() : 0);
        temp = Double.doubleToLongBits(exposureTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "eye=" + eye +
                ", lookat=" + lookat +
                ", up=" + up +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                ", exposureTime=" + exposureTime +
                '}';
    }

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

    public Point3D getEye() {
        return eye;
    }

    public void setEye(Point3D eye) {
        this.eye = eye;
    }

    public Point3D getLookat() {
        return lookat;
    }

    public void setLookat(Point3D lookat) {
        this.lookat = lookat;
    }

    public Vector3D getUp() {
        return up;
    }

    public void setUp(Vector3D up) {
        this.up = up;
    }

    public Vector3D getU() {
        return u;
    }

    public Vector3D getV() {
        return v;
    }

    public Vector3D getW() {
        return w;
    }

    public double getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(double exposureTime) {
        this.exposureTime = exposureTime;
    }
}