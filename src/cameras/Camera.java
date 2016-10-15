package cameras;

import utilities.Point3D;
import utilities.Vector3D;
import world.World;

/**
 * Camera class is responsible to render the scene. After setting the camera, it will shot rays through each pixel on a
 * ViewPlane. The rendering process is up to BRDFs and lights. Finally it collects radiance information and draw them on
 * the screen.
 */
abstract public class Camera {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** Eye points, i.e., the position of the camera. */
    Point3D eye;

    /** To which point the eye is looking, usually set to <code>(1,0,0)</code> */
    Point3D lookat = new Point3D(1, 0, 0);

    /** Up vector, usually set to <code>(0,1,0)</code> */
    Vector3D up = new Vector3D(0, 1, 0);

    /** Local x-, y- and z-coordinate system */
    Vector3D u, v, w;

    /** Exposure time used to control how much light can pass through its aperture. */
    double exposureTime = 1.0;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Camera(Point3D eye) {
        this.eye = eye;
        this.computeUVW();
    }

    public Camera(Point3D eye, Point3D lookat) {
        this.eye = eye;
        this.lookat = lookat;
        this.computeUVW();
    }

    public Camera(Point3D eye, Point3D lookat, Vector3D up) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
        this.computeUVW();
    }

    /**
     * An OpenGL style camera constructor, <code>gluLookat()</code>.
     *
     * @param eyeX    x-coordinate of the eye position
     * @param eyeY    y-coordinate of the eye position
     * @param eyeZ    z-coordinate of the eye position
     * @param lookatX x-coordinate of the lookat position
     * @param lookatY y-coordinate of the lookat position
     * @param lookatZ z-coordinate of the lookat position
     * @param upX     x-coordinate of the up vector
     * @param upY     y-coordinate of the up vector
     * @param upZ     z-coordinate of the up vector
     */
    public Camera(double eyeX, double eyeY, double eyeZ,
                  double lookatX, double lookatY, double lookatZ,
                  double upX, double upY, double upZ) {
        this.eye = new Point3D(eyeX, eyeY, eyeZ);
        this.lookat = new Point3D(lookatX, lookatY, lookatZ);
        this.up = new Vector3D(upX, upY, upZ);
        this.computeUVW();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Abstract methods
\*--------------------------------------------------------------------------------------------------------------------*/

    abstract public void renderScene(World w);

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Helper methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Computes the computeUVW-coordinate, aka, the camera coordinate.
     */
    void computeUVW() {
        w = eye.sub(lookat).normalVector();
        u = up.cross(w).normalVector();
        v = w.cross(u).normalVector();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

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

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point3D getEye() {
        return eye;
    }

    public Point3D getLookat() {
        return lookat;
    }

    public Vector3D getUp() {
        return up;
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

}
